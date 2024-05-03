package expression.exceptions;

import expression.TripleExpression;

public class CheckedNegate implements TripleExpression {
    private final TripleExpression expr1;
    public CheckedNegate(TripleExpression expr1) {
        this.expr1 = expr1;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if(-expr1.evaluate(x, y, z) == Integer.MIN_VALUE) {
            throw new ArithmeticException("negate overflow");
        }
        return -expr1.evaluate(x, y, z);
    }
}
