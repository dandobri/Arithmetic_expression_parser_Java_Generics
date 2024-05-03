package expression.generic;

public class GenericMultiply<E extends Number> extends GenericChecked<E> {
    public GenericMultiply(GenericEvaluate<E> expr1, GenericEvaluate<E> expr2, Generic<E> expr3) {
        super(expr1, expr2, expr3);
    }

    @Override
    protected String getSign() {
        return "*";
    }


    @Override
    protected E evaluate(E expr1, E expr2) {
        return multiply(expr1, expr2);
    }
}