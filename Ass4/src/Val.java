//322094111

import java.util.*;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that creates a value.
 * <p>
 **/

public class Val implements Expression {

    private boolean value;

    /**
     * constructor.
     *
     * @param val type boolean.
     */
    public Val(boolean val) {
        this.value = val;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            return this.value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean evaluate() throws Exception {

        return this.value;

    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        if (this.value) {
            return ("T");
        } else {
            return ("F");
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
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
    public Expression simplify(){
        return this;
    }
}
