package expression.generic.operations;

import expression.generic.GenericExpression;

public class Doub implements GenericExpression<Double> {
    public Double add(Double expr1, Double expr2) {
        return expr1 + expr2;
    }
    public Double multiply(Double expr1, Double expr2){
        return expr1 * expr2;
    }
    public Double subtract(Double expr1, Double expr2){
        return expr1 - expr2;
    }
    public Double divide(Double expr1, Double expr2){
        return expr1 / expr2;
    }

    @Override
    public Double constant(int constant) {
        return (double) constant;
    }
    @Override
    public Double unarysubtract(Double expr) {
        return -expr;
    }
}
