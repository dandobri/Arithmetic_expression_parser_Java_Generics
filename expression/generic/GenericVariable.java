package expression.generic;

public class GenericVariable<E extends Number> implements GenericEvaluate<E>, GenericUnary<E> {
    private final String variable;
    private final GenericUnary<E> expr1;
    public GenericVariable(String variable, GenericUnary<E> expr1) {
        this.variable = variable;
        this.expr1 = expr1;
    }
    @Override
    public String toString() {
        return variable;
    }
    @Override
    public E evaluate(int x, int y, int z) {
        if(variable.equals("x")) {
            return constant(x);
        } else if(variable.equals("y")) {
            return constant(y);
        }else {
            return constant(z);
        }
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
