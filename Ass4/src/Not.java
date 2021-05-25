//322094111

import java.util.Map;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that deals with Not Expressions,  inherits from UnaryExpression.
 * <p>
 **/

public class Not extends UnaryExpression {
    /**
     * Constructor.
     *
     * @param expression1 type Expression.
     */
    public Not(Expression expression1) {
        super(expression1);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return (!this.getE1().evaluate(assignment));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return ("~" + "(" + this.getE1().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(getE1().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(this.getE1().nandify(), this.getE1().nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(this.getE1().norify(), this.getE1().norify());
    }

    @Override
    public Expression simplify() {

        Expression one = this.getE1().simplify();
        try {
            if (one.getVariables().isEmpty()) {
                if (one.evaluate()) {
                    return new Val(false);
                } else {
                    return new Val(true);
                }
            }
            return new Not(one);
        } catch (Exception e) {
            System.out.println("Not Exception Thrown");
            return null;
        }
    }

}
