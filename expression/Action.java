package expression;

public abstract class Action implements TripleExpression{
    private final TripleExpression expr1;
    private final TripleExpression expr2;

    public Action(TripleExpression expr1, TripleExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    @Override
    public String toString() { return "(" + expr1.toString() + " " + getSign() + " " + expr2.toString() + ")";}
    protected abstract int evaluate(int expr1, int expr2);
    public int evaluate(int x, int y, int z){
        return evaluate(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }
    protected abstract String getSign();
}
