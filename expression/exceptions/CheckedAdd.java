package expression.exceptions;

import expression.TripleExpression;

public class CheckedAdd extends Checked {
    public CheckedAdd(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "+";
    }

    @Override
    public int evaluate(int expr1, int expr2) {
        if (expr1 < 0 && expr2 < 0 && Integer.MIN_VALUE - expr2 > expr1 ||
                expr1 > 0 && expr2 > 0 && Integer.MAX_VALUE - expr2 < expr1) {
            throw new ArithmeticException("int overflows in add");
        }
        return expr1 + expr2;
    }
}
