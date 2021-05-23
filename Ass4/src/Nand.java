//322094111
import java.util.Map;
/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that creates a value.
 * <p>
 **/

public class Nand extends BinaryExpression {
    /**
     * Constructor.
     *
     * @param expression1 type Expression.
     * @param expression2 type Expression.
     */
    public Nand(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        return (!(this.getE1().evaluate(assignment) && this.getE2().evaluate(assignment)));

    }

    @Override
    public String toString() {
        return ("(" + getE1().toString() + "A" + getE2().toString() + ")");
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(this.getE1().assign(var, expression), this.getE2().assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
     // [ ( A NOR A ) NOR ( B NOR B ) ] NOR [ ( A NOR A ) NOR ( B NOR B ) ]
        return new Nor(new Nor(new Nor(this.getE1().norify(),this.getE1().norify()),
                new Nor(this.getE2().norify(),this.getE2().norify())),
                new Nor(new Nor(this.getE1().norify(),this.getE1().norify()),
                        new Nor(this.getE2().norify(),this.getE2().norify())));
    }
}
