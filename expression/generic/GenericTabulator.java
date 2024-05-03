package expression.generic;

import expression.generic.operations.*;

public class GenericTabulator implements Tabulator{
    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        GenericExpression<?> gen = switch (mode) {
            case "i" -> new Int(true);
            case "d" -> new Doub();
            case "bi" -> new BigInt();
            case "u" -> new Int(false);
            case "f" -> new Flo();
            case "s" -> new Sho();
            default -> null;
        };
        return tabulate1(gen, expression, x1, x2, y1, y2, z1, z2);
    }
    public <E extends Number> Object[][][] tabulate1(GenericExpression<E> gen, String expression, int x1, int x2, int y1, int y2, int z1, int z2)throws Exception{
        GenericEvaluate<E> expr = new GenericExpressionParser<E>(gen).parse(expression);
        Object[][][] ob = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for(int i = 0; i < x2 - x1 + 1; i++){
            for(int j = 0; j < y2 - y1 + 1; j++){
                for(int k = 0; k < z2 - z1 + 1; k++){
                    try {
                        ob[i][j][k] = expr.evaluate(x1 + i, y1 + j, z1 + k);
                    }catch(Exception e){
                        ob[i][j][k] = null;
                    }
                }
            }
        }
        return ob;
    }
}
