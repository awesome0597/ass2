import java.util.ArrayList;
import java.util.List;

public abstract class UnaryExpression extends BaseExpression{
    public UnaryExpression(Expression expression1){
        super(expression1);
    }

    public List<String> getVariables(){

        List<String> vars = new ArrayList<>();
        if (getE1().getVariables()!=null) {
            vars.addAll(getE1().getVariables());
        }
        return vars;
    }
}
