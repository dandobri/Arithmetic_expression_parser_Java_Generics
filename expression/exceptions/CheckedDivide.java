package expression.exceptions;

import expression.TripleExpression;

public class CheckedDivide extends Checked {
    public CheckedDivide(TripleExpression expr1, TripleExpression expr2) {
        super(expr1, expr2);
    }

    @Override
    protected String getSign() {
        return "/";
    }

    @Override
    public int evaluate(int expr1, int expr2) {
        if(expr2 == 0){
            throw new ArithmeticException("/ by zero");
        }
        int r = expr1 / expr2;
        if((expr1 & expr2 & r) < 0){
            throw new ArithmeticException("int overflows in divide");
        }
        return r;
    }
}
