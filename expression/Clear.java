package expression;

import expression.TripleExpression;
import expression.exceptions.Checked;

public class Clear extends Checked {
    public Clear(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "clear";
    }

    @Override
    protected int evaluate(int expr1, int expr2) {
        expr1 &= ~(1 << expr2);
        return expr1;
    }
}
