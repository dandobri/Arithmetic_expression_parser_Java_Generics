package expression;

public class Add extends Action{
    public Add(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }
    public int evaluate(int expr1, int expr2) {
        return expr1 + expr2;
    }
    protected String getSign(){
        return "+";
    }
}
