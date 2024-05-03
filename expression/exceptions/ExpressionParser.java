package expression.exceptions;
import expression.TripleExpression;
import expression.exceptions.TripleParser;

import java.text.ParseException;
import java.util.List;
import static expression.exceptions.Parser.operanalyze;
import static expression.exceptions.Parser.plusminus;

public class ExpressionParser implements TripleParser {
    @Override
    public TripleExpression parse(String expression) throws ParseException {
        List<DivideParser> lex = operanalyze(expression);
        OpertypeBuffer operbuffer = new OpertypeBuffer(lex);
        return plusminus(operbuffer);
    }
}
