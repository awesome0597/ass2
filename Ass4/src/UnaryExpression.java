//322094111

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that defines a UnaryExpression, inherits from BaseExpression.
 * <p>
 **/

public abstract class UnaryExpression extends BaseExpression {
    public UnaryExpression(Expression expression1) {
        super(expression1);
    }

    public List<String> getVariables() {

        List<String> vars = new ArrayList<>();
        if (getE1().getVariables() != null) {
            vars.addAll(getE1().getVariables());
        }
        return vars;
    }
}
