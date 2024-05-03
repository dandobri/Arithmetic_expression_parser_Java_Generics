package expression.exceptions;

import expression.TripleExpression;

public abstract class Checked implements TripleExpression {
    private final TripleExpression expr1;
    private final TripleExpression expr2;
    public Checked(TripleExpression expr1, TripleExpression expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    protected abstract String getSign();
    @Override
    public String toString() { return "(" + expr1.toString() + " " + getSign() + " " + expr2.toString() + ")";}
    protected abstract int evaluate(int expr1, int expr2);
    public int evaluate(int x, int y, int z){
        return evaluate(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }
}
