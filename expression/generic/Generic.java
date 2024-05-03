package expression.generic;

public interface Generic<E extends Number>{
    E add(E expr1, E expr2);
    E multiply(E expr1, E expr2);
    E subtract(E expr1, E expr2);
    E divide(E expr1, E expr2);
}
