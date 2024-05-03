package expression;

import expression.TripleExpression;
import expression.exceptions.Checked;

public class Set extends Checked {
    public Set(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "set";
    }

    @Override
    protected int evaluate(int expr1, int expr2) {
        expr1 |= 1 << expr2;
        return expr1;
    }
}
