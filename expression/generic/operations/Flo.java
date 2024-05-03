package expression.generic.operations;

import expression.generic.GenericExpression;

public class Flo implements GenericExpression<Float> {
    @Override
    public Float add(Float expr1, Float expr2) {
        return expr1 + expr2;
    }

    @Override
    public Float multiply(Float expr1, Float expr2) {
        return expr1 * expr2;
    }

    @Override
    public Float subtract(Float expr1, Float expr2) {
        return expr1 - expr2;
    }

    @Override
    public Float divide(Float expr1, Float expr2) {
        return expr1 / expr2;
    }

    @Override
    public Float constant(int constant) {
        return (float) constant;
    }

    @Override
    public Float unarysubtract(Float expr) {
        return -expr;
    }
}
