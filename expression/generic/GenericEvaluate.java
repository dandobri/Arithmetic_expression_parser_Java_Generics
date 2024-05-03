package expression.generic;

public interface GenericEvaluate<E extends Number> {
    E evaluate(int x, int y, int z);
}
