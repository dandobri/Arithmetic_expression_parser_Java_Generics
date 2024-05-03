package expression.generic.operations;

import expression.generic.GenericExpression;

public class Sho implements GenericExpression<Short> {
    @Override
    public Short add(Short expr1, Short expr2) {
        return (short) (expr1 + expr2);
    }

    @Override
    public Short multiply(Short expr1, Short expr2) {
        return (short) (expr1 * expr2);
    }

    @Override
    public Short subtract(Short expr1, Short expr2) {
        return (short) (expr1 - expr2);
    }

    @Override
    public Short divide(Short expr1, Short expr2) {
        return (short) (expr1 / expr2);
    }

    @Override
    public Short constant(int constant) {
        return (short) constant;
    }

    @Override
    public Short unarysubtract(Short expr) {
        return (short) -expr;
    }
}
