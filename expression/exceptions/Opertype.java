package expression.exceptions;

public enum Opertype {
    LEFT_BRACKET(true), RIGTH_BRACKET(false), OP_PLUS(true),
    OP_MINUS(true), OP_MULT(true), OP_DIV(true),
    NUMBER(false), VARIABLE(false),
    OP_UNARY_MINUS(true),
    OP_COUNT(true), OP_SET(true), OP_CLEAR(true), EOF(false);

    public boolean isUnaryMinusAllowed;

    Opertype(boolean isUnaryMinusAllowed) {
        this.isUnaryMinusAllowed = isUnaryMinusAllowed;
    }
}
