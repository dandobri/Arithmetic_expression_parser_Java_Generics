package expression.generic.operations;

import expression.generic.GenericExpression;

import java.math.BigInteger;

public class BigInt implements GenericExpression<BigInteger> {
    public BigInteger add(BigInteger expr1, BigInteger expr2) {
        return expr1.add(expr2);
    }
    public BigInteger multiply(BigInteger expr1, BigInteger expr2){
        return expr1.multiply(expr2);
    }
    public BigInteger subtract(BigInteger expr1, BigInteger expr2){
        return expr1.subtract(expr2);
    }
    public BigInteger divide(BigInteger expr1, BigInteger expr2){
        return expr1.divide(expr2);
    }

    @Override
    public BigInteger constant(int constant) {
        return BigInteger.valueOf(constant);
    }

    @Override
    public BigInteger unarysubtract(BigInteger expr) {
        return expr.negate();
    }
}
