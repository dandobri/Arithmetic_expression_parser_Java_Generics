package expression.generic;

public class GenericNegate<E extends Number> implements GenericUnary<E>, GenericEvaluate<E> {
    private final GenericEvaluate<E> expr1;
    private final GenericUnary<E> expr2;
    public GenericNegate(GenericEvaluate<E> expr1, GenericUnary<E> expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public E evaluate(int x, int y, int z) {
        return unarysubtract(expr1.evaluate(x, y, z));
    }
    @Override
    public E constant(int constant) {
        return expr2.constant(constant);
    }

    @Override
    public E unarysubtract(E expr) {
        return expr2.unarysubtract(expr);
    }
}
