package expression.generic;

import expression.exceptions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static expression.exceptions.Opertype.*;

public class GenericExpressionParser<E extends Number> {
    private final GenericExpression<E> gen;
    public GenericExpressionParser(GenericExpression<E> gen){
        this.gen = gen;
    }
    public GenericEvaluate<E> parse(String expression) throws ParseException {
        List<DivideParser> lex = operanalyze(expression);
        OpertypeBuffer operbuffer = new OpertypeBuffer(lex);
        return plusminus(operbuffer);
    }
    public void unaryminus(Opertype oper, List<DivideParser> list, String sb) {
        if (list.size() != 0 && list.get(list.size() - 1).type() == OP_UNARY_MINUS) {
            list.remove(list.size() - 1);
            list.add(new DivideParser(oper, '-' + sb));
        } else {
            list.add(new DivideParser(oper, sb));
        }
    }

    public List<DivideParser> operanalyze(String expression) throws AssertionError, ParseException {
        List<DivideParser> list = new ArrayList<>(0);
        int pos = 0;
        for(int i = 0; i < expression.length(); i++){
            char c = expression.charAt(i);
            switch (c) {
                case '(' -> list.add(new DivideParser(LEFT_BRACKET, String.valueOf(c)));
                case ')' -> list.add(new DivideParser(RIGTH_BRACKET, String.valueOf(c)));
                case '-' -> {
                    if (list.size() == 0) {
                        list.add(new DivideParser(OP_UNARY_MINUS, String.valueOf(c)));
                    } else {
                        DivideParser parse = list.get(list.size() - 1);
                        if (parse.type().isUnaryMinusAllowed) {
                            list.add(new DivideParser(OP_UNARY_MINUS, String.valueOf(c)));
                        } else
                            list.add(new DivideParser(OP_MINUS, String.valueOf(c)));
                    }
                }
                case '+' -> list.add(new DivideParser(OP_PLUS, String.valueOf(c)));
                case 'c' -> {
                    if (expression.startsWith("count", i)) {
                        list.add(new DivideParser(OP_COUNT, "count"));
                        i += 4;
                        if (i < expression.length() - 1) {
                            if (Character.isWhitespace(expression.charAt(i + 1)) || expression.charAt(i + 1) == '(') {

                            } else throw new ParseException("unexpected token after count", 1);
                        }
                    } else if (expression.startsWith("clear", i)) {
                        list.add(new DivideParser(OP_CLEAR, "clear"));
                        i += 4;
                        if (i - 5 >= 0) {
                            if (Character.isWhitespace(expression.charAt(i - 5)) || expression.charAt(i - 5) == ')') {

                            } else throw new ParseException("unexpected token before clear", 1);
                        }
                    }
                }
                case 's' -> {
                    if (expression.startsWith("set", i)) {
                        list.add(new DivideParser(OP_SET, "set"));
                    } else throw new ParseException("Unexpected token", 1);
                    i += 2;
                    if (i - 3 >= 0) {
                        if (Character.isWhitespace(expression.charAt(i - 3)) || expression.charAt(i - 3) == ')') {

                        } else throw new ParseException("unexpected token before set", 1);
                    }
                }
                case '/' -> list.add(new DivideParser(OP_DIV, String.valueOf(c)));
                case '*' -> list.add(new DivideParser(OP_MULT, String.valueOf(c)));
                case 'x', 'y', 'z' -> unaryminus(VARIABLE, list, String.valueOf(c));
                default -> {
                    if (Character.isWhitespace(c)) {
                        continue;
                    }
                    if (!Character.isDigit(c)) {
                        throw new RuntimeException(String.format("Unexpected token in pos %d", i));
                    }
                    if (c >= '0' && c <= '9') {
                        if (list.size() != 0) {
                            if (list.get(list.size() - 1).type() == NUMBER) {
                                throw new ParseException("no operant between numbers", 1);
                            }
                        }
                        StringBuilder sb = new StringBuilder();
                        pos = i;
                        while (c >= '0' && c <= '9') {
                            sb.append(c);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(pos);
                        }
                        i = pos - 1;
                        unaryminus(NUMBER, list, sb.toString());
                    }
                }
            }
        }
        int par = 0;
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).type() == LEFT_BRACKET){
                par++;
            }else if(list.get(i).type() == RIGTH_BRACKET){
                par--;
            }
        }
        if(par != 0){
            throw new ParseException("not opening or closing brakcet", 1);
        }
        list.add(new DivideParser(EOF, ""));
        return list;
    }
    public GenericEvaluate<E> plusminus(OpertypeBuffer buffer) throws ParseException {
        GenericEvaluate<E> act = multdiv(buffer);
        while(buffer.getPos() <= buffer.getList().size()) {
            DivideParser parser = buffer.next();
            switch (parser.type()) {
                case OP_PLUS -> act = new GenericAdd<E>(act, multdiv(buffer), gen);
                case OP_MINUS -> act = new GenericSubtract<E>(act, multdiv(buffer), gen);
                case LEFT_BRACKET -> {
                    buffer.previous();
                    buffer.previous();
                    if(buffer.getPos() != -1){
                        if(buffer.getList().get(buffer.getPos()).type() == NUMBER || buffer.getList().get(buffer.getPos()).type() == VARIABLE){
                            throw new ParseException("no operant before left bracket", 1);
                        }
                    }
                    buffer.next();
                }
                default -> {
                    buffer.previous();
                    return act;
                }
            }
        }
        return act;
    }
    public GenericEvaluate<E> multdiv(OpertypeBuffer buffer) throws ParseException {
        GenericEvaluate<E> act = unaryOperation(buffer);
        while(buffer.getPos() <= buffer.getList().size()) {
            DivideParser parser = buffer.next();
            switch (parser.type()) {
                case OP_MULT -> act = new GenericMultiply<E>(act, unaryOperation(buffer), gen);
                case OP_DIV -> act = new GenericDivide<E>(act, unaryOperation(buffer), gen);
                default -> {
                    buffer.previous();
                    return act;
                }
            }
        }
        return act;
    }
    public GenericEvaluate<E> unaryOperation(OpertypeBuffer buffer) throws ParseException {
        DivideParser parse = buffer.next();
        switch (parse.type()) {
            case OP_UNARY_MINUS -> {
                return new GenericNegate<E>(unaryOperation(buffer), gen);
            }
            case NUMBER -> {
                return new GenericConst<E>(Integer.parseInt(parse.ch()), gen);
            }
            case VARIABLE -> {
                return new GenericVariable<E>(parse.ch(), gen);
            }
            case LEFT_BRACKET -> {
                GenericEvaluate<E> act = plusminus(buffer);
                parse = buffer.next();
                if(parse.type() != RIGTH_BRACKET){
                    throw new ParseException("no right bracket for left bracket", 1);
                }
                return act;
            }
            default -> throw new RuntimeException(String.format("Unexpected token in pos %d", buffer.getPos()));
        }
    }
}
