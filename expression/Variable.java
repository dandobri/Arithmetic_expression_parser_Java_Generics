package expression;

import expression.TripleExpression;

public class Variable implements TripleExpression {
    private final String variable;
    public Variable(String variable) {
        this.variable = variable;
    }
    @Override
    public String toString() {
        return variable;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        if(variable.equals("x")) {
            return x;
        } else if(variable.equals("-x")) {
            return -x;
        } else if(variable.equals("y")) {
            return y;
        } else if(variable.equals("-y")) {
            return -y;
        } else if(variable.equals("z")){
            return z;
        } else {
            return -z;
        }
    }
    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable var1 = (Variable) o;
        return variable.equals(var1.variable);
    }
    @Override
    public int hashCode() {
        return variable.charAt(0);
    }
}
