//322094111

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Adira Weiss.
 * @version 1.0.0
 * @since: 23/5/21
 * Class that runs project.
 * <p>
 **/
public class ExpressionsTest {

    /**
     * Main.
     * @param args, type String. not used in run.
     */
    public static void main(String[] args) {
        Var var1 = new Var("f");
        Var var2 = new Var("m");
        Var var3 = new Var("l");

        Map<String, Boolean> map = new TreeMap<>();
        map.put("f", false);
        map.put("l", true);

        //( f ^ m ) & ( l | ~f )
        Expression expression = new And(new Xor(var1, var2), new Or(var3, new Not(var1)));

        System.out.println(expression);

        //Assign new var/val to existing expression
        expression = expression.assign("f", new Val(true));
        expression = expression.assign("m", new Val(false));
        expression = expression.assign("l", new Var("u"));
        System.out.println(expression);
        System.out.println(expression.nandify());
        System.out.println(expression.norify());
        System.out.println(expression.simplify());

    }
}
