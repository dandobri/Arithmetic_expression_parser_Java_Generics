package expression.generic.operations;

import expression.generic.GenericExpression;

public class Int implements GenericExpression<Integer> {
    private final boolean b;
    public Int(boolean b){
        this.b = b;
    }
    @Override
    public Integer add(Integer expr1, Integer expr2) {
        if(b){
            if (expr1 < 0 && expr2 < 0 && Integer.MIN_VALUE - expr2 > expr1 ||
                    expr1 > 0 && expr2 > 0 && Integer.MAX_VALUE - expr2 < expr1) {
                throw new ArithmeticException("int overflows in add");
            }
        }
        return expr1 + expr2;
    }

    @Override
    public Integer multiply(Integer expr1, Integer expr2) {
        if(b) {
            if (expr1 > 0 && expr2 > 0 && Integer.MAX_VALUE / expr2 < expr1 ||
                    expr1 < 0 && expr2 < 0 && Integer.MAX_VALUE / expr2 > expr1 ||
                    expr1 < 0 && expr2 > 0 && Integer.MIN_VALUE / expr2 > expr1 ||
                    expr1 > 0 && expr2 < 0 && Integer.MIN_VALUE / expr1 > expr2) {
                throw new ArithmeticException("int overflows in multiply");
            }
        }
        return expr1 * expr2;
    }

    @Override
    public Integer subtract(Integer expr1, Integer expr2) {
        if(b) {
            if (expr1 < 0 && expr2 > 0 && Integer.MIN_VALUE + expr2 > expr1 ||
                    expr1 > 0 && expr2 < 0 && Integer.MAX_VALUE + expr2 < expr1 || expr1 == 0 && expr2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("int overflows in subtract");
            }
        }
        return expr1 - expr2;
    }

    @Override
    public Integer divide(Integer expr1, Integer expr2) {
        if(expr2 == 0){
            throw new ArithmeticException("/ by zero");
        }
        return expr1/expr2;
    }

    @Override
    public Integer constant(int constant) {
        return constant;
    }

    @Override
    public Integer unarysubtract(Integer expr) {
        if(b){
            if(expr == Integer.MIN_VALUE) {
                throw new ArithmeticException("negate overflow");
            }
        }
        return -expr;
    }
}
