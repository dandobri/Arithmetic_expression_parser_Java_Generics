package expression;

public class Const implements TripleExpression {
    private final int constant;
    public Const(int constant) {
        this.constant = constant;
    }
    @Override
    public String toString() {
        return Integer.toString(constant);
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return constant;
    }
}
