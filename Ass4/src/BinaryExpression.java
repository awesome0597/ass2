//322904111

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that defines a BinaryExpression, inherits from BaseExpression.
 * <p>
 **/

public abstract class BinaryExpression extends BaseExpression {

    /**
     * Constructor.
     *
     * @param expression1 type Expression.
     * @param expression2 type Expression.
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public List<String> getVariables() {

        List<String> vars = new ArrayList<>();
        if (getE1().getVariables() != null) {
            vars.addAll(getE1().getVariables());
        }
        if (!getE2().toString().equals(getE1().toString()) && getE2().getVariables() != null) {
            vars.addAll(getE2().getVariables());
        }

        return vars.stream().distinct().collect(Collectors.toList());
    }
}
