package expression;

import expression.TripleExpression;

public class Count implements TripleExpression {
    private final TripleExpression count1;
    public Count(TripleExpression count1) {
        this.count1 = count1;
    }
    public String toString() {
        return "count(" + count1.toString() + ")";
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return Integer.bitCount(count1.evaluate(x, y, z));
    }
}
