package expression;

import expression.TripleExpression;

public class UnarySubtract implements TripleExpression {
    private final TripleExpression expr1;
    public UnarySubtract(TripleExpression expr1) {
        this.expr1 = expr1;
    }
    @Override
    public String toString() {
        return "-" + "(" + expr1.toString() + ")";
    }
    @Override
    public int evaluate(int x, int y, int z) {
        if(expr1.evaluate(x, y, z) == Integer.MIN_VALUE){
            throw new ArithmeticException("int pverflows");
        }
        return -expr1.evaluate(x, y, z);
    }
}
