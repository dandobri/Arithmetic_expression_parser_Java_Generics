package expression.generic;

public class GenericConst<E extends Number> implements GenericEvaluate<E>, GenericUnary<E> {
    private final int constant;
    private final GenericUnary<E> expr1;
    public GenericConst(int constant, GenericUnary<E> expr1) {
        this.constant = constant;
        this.expr1 = expr1;
    }
    @Override
    public String toString() {
        return Integer.toString(constant);
    }
    //@Override
    //public int evaluate(int x, int y, int z) {
    //  return constant;
    //}

    @Override
    public E evaluate(int x, int y, int z) {
        return constant(constant);
    }
    @Override
    public E constant(int constant) {
        return expr1.constant(constant);
    }
    @Override
    public E unarysubtract(E expr) {
        return expr1.unarysubtract(expr);
    }
}
