import java.util.ArrayList;
import java.util.List;

public abstract class BinaryExpression extends BaseExpression{
    public BinaryExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }
    @Override
    public List<String> getVariables(){

        List<String> vars = new ArrayList<>();
        if (getE1().getVariables()!=null){
            vars.addAll(getE1().getVariables());
        }
        if (getE2().getVariables()!=null){
            vars.addAll(getE2().getVariables());
        }

        return vars;
    }
}
