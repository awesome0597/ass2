//322094111

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.LinkedList;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that creates a base expression.
 * <p>
 **/
public abstract class BaseExpression implements Expression {
    private Expression e1;
    private Expression e2;

    /**
     * constructor.
     *
     * @param expression1 type Expression.
     * @param expression2 type Expression.
     */
    public BaseExpression(Expression expression1, Expression expression2) {
        this.e1 = expression1;
        this.e2 = expression2;
    }

    /**
     * constructor.
     *
     * @param expression type Expression.
     */
    public BaseExpression(Expression expression) {
        this.e1 = expression;
    }

    public Expression getE1() {
        return e1;
    }

    public Expression getE2() {
        return e2;
    }

    @Override
    public Boolean evaluate() throws Exception {
        Map<String, Boolean> assignment = new TreeMap<>();

        return this.evaluate(assignment);

    }
}
