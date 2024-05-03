package expression.exceptions;

import expression.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static expression.exceptions.Opertype.*;

public class Parser extends ExpressionParser{
    public static List<DivideParser> unaryminus(Opertype oper, List<DivideParser> list, String sb) {
        if (list.size() != 0 && list.get(list.size() - 1).type() == OP_UNARY_MINUS) {
            list.remove(list.size() - 1);
            list.add(new DivideParser(oper, '-' + sb));
        } else {
            list.add(new DivideParser(oper, sb));
        }
        return list;
    }

    public static List<DivideParser> operanalyze(String expression) throws AssertionError, ParseException {
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
    public static TripleExpression plusminus(OpertypeBuffer buffer) throws ParseException {
        TripleExpression act = multdiv(buffer);
        while(buffer.getPos() <= buffer.getList().size()) {
            DivideParser parser = buffer.next();
            switch (parser.type()) {
                case OP_PLUS -> act = new CheckedAdd(act, multdiv(buffer));
                case OP_MINUS -> act = new CheckedSubtract(act, multdiv(buffer));
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
    public static TripleExpression multdiv(OpertypeBuffer buffer) throws ParseException {
        TripleExpression act = unaryOperation(buffer);
        while(buffer.getPos() <= buffer.getList().size()) {
            DivideParser parser = buffer.next();
            switch (parser.type()) {
                case OP_MULT -> act = new CheckedMultiply(act, unaryOperation(buffer));
                case OP_DIV -> act = new CheckedDivide(act, unaryOperation(buffer));
                case OP_SET -> act = new Set(act, unaryOperation(buffer));
                case OP_CLEAR -> act = new Clear(act, unaryOperation(buffer));
                default -> {
                    buffer.previous();
                    return act;
                }
            }
        }
        return act;
    }
    public static TripleExpression unaryOperation(OpertypeBuffer buffer) throws ParseException {
        DivideParser parse = buffer.next();
        switch (parse.type()) {
            case OP_UNARY_MINUS -> {
                return new UnarySubtract(unaryOperation(buffer));
            }
            case NUMBER -> {
                return new Const(Integer.parseInt(parse.ch()));
            }
            case VARIABLE -> {
                return new Variable(parse.ch());
            }
            case OP_COUNT -> {
                return new Count(unaryOperation(buffer));
            }
            case LEFT_BRACKET -> {
                TripleExpression act = plusminus(buffer);
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