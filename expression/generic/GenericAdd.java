package expression.generic;

public class GenericAdd<E extends Number> extends GenericChecked<E> {
    public GenericAdd(GenericEvaluate<E> expr1, GenericEvaluate<E> expr2, Generic<E> expr3) {
        super(expr1, expr2, expr3);
    }

    @Override
    protected String getSign() {
        return "+";
    }


    @Override
    protected E evaluate(E expr1, E expr2) {
        return add(expr1, expr2);
    }
}
