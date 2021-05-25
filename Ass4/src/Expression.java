//3222094111

import java.util.Map;
import java.util.List;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Interface that defines an Expression.
 * <p>
 **/

public interface Expression {


    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment type, Map<String, Boolean>
     * @return type Boolean
     * @throws Exception possibly...
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above,
     * but uses an empty assignment.
     *
     * @return type Boolean
     * @throws Exception possibly...
     */
    Boolean evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     *
     * @return type List<String>
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return type String.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        type String
     * @param expression type Expression
     * @return type Expression
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     *
     * @return type Expression
     */
    Expression nandify();

    /**
     * Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     *
     * @return type Expression
     */
    Expression norify();

    /**
     * Returned a simplified version of the current expression.
     *
     * @return type Expression
     */
    Expression simplify();
}
