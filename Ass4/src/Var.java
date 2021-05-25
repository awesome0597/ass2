//322094111

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that creates a variable, implements Expression interface.
 * <p>
 **/

public class Var implements Expression {
    private String variable;

    /**
     * Constructor.
     *
     * @param var type String.
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * Access method.
     *
     * @return type String.
     */
    public String getVariable() {
        return variable;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        return assignment.get(this.variable);


    }

    @Override
    public Boolean evaluate() throws Exception {
        return null;
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new ArrayList<>();
        vars.add(this.toString());
        return vars;
    }

    @Override
    public String toString() {
        return this.variable;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    @Override
    public Expression nandify() {
        return this;
    }

    @Override
    public Expression norify() {
        return this;
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
