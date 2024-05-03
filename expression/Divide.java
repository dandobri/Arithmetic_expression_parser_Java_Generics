package expression;

public class Divide extends Action{
    public Divide(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }
    @Override
    public int evaluate(int expr1, int expr2) {
        return expr1 / expr2;
    }

    @Override
    protected String getSign() {
        return "/";
    }
}
