package expression.generic;

public abstract class GenericChecked<E extends Number> implements Generic<E>, GenericEvaluate<E> {
    private final GenericEvaluate<E> expr1;
    private final GenericEvaluate<E> expr2;
    private final Generic<E> expr3;
    public GenericChecked(GenericEvaluate<E> expr1, GenericEvaluate<E> expr2, Generic<E> expr3){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
    }
    protected abstract String getSign();
    @Override
    public String toString() { return "(" + expr1.toString() + " " + getSign() + " " + expr2.toString() + ")";}
    protected abstract E evaluate(E expr1, E expr2);
    public E evaluate(int x, int y, int z){
        return evaluate(expr1.evaluate(x, y, z), expr2.evaluate(x, y, z));
    }
    public E add(E expr1, E expr2){
        return expr3.add(expr1, expr2);
    }
    public E subtract(E expr1, E expr2){
        return expr3.subtract(expr1, expr2);
    }
    public E multiply(E expr1, E expr2){
        return expr3.multiply(expr1, expr2);
    }
    public E divide(E expr1, E expr2){
        return expr3.divide(expr1, expr2);
    }
}
