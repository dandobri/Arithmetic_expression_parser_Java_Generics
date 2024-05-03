package expression.generic;

public interface GenericUnary<E extends Number>{
    E constant(int constant);
    E unarysubtract(E expr);
}
