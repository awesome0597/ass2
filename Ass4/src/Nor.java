//322094111

import java.util.Map;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that deals with Nor Expressions,  inherits from BinaryExpression.
 * <p>
 **/
public class Nor extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param expression1 type Expression.
     * @param expression2 type Expression.
     */
    public Nor(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        return (!(this.getE1().evaluate(assignment) || this.getE2().evaluate(assignment)));

    }

    @Override
    public String toString() {
        return ("(" + getE1().toString() + " V " + getE2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        //[ ( A NAND A ) NAND ( B NAND B ) ] NAND [ ( A NAND A ) NAND ( B NAND B ) ]
        return new Nand(new Nand(new Nand(this.getE1().nandify(), this.getE1().nandify()),
                new Nand(this.getE2().nandify(), this.getE2().nandify())),
                new Nand(new Nand(this.getE1().nandify(), this.getE1().nandify()),
                        new Nand(this.getE2().nandify(), this.getE2().nandify())));
    }

    @Override
    public Expression norify() {
        return new Nor(this.getE1().norify(), this.getE2().norify());
    }

    @Override
    public Expression simplify() {
        Expression one = getE1().simplify();
        Expression two = getE2().simplify();

        try {
            if (one.getVariables().isEmpty() && two.getVariables().isEmpty()) {
                return new Val(this.evaluate());
            }
            if (one.getVariables().isEmpty()) {
                if (one.evaluate()) {
                    return new Val(false);
                } else {
                    return new Not(two);
                }
            }
            if (two.getVariables().isEmpty()) {
                if (two.evaluate()) {
                    return new Val(false);
                } else {
                    return new Not(one);
                }
            }
            if (one.toString().equals(two.toString())) {
                return new Not(one);
            } else {
                return new Nor(one, two);
            }
        } catch (Exception e) {
            System.out.println("Nor Exception Thrown!");
            return null;
        }
    }
}
