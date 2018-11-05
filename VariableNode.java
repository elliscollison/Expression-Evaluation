import java.util.Map;

/**
 * Variable node of the expression.
 * Does not have child nodes.
 */
public class VariableNode implements Evaluable
{
    String name; //name of the variable

    public VariableNode(String name){
        this.name = name;
    }

    /**
     * @return name the name of the variable
     */
    public String getName(){
        return name;
    }

    public double evaluate(Map<String, Double> env){
        if (env.get(name) != null){
            return env.get(name);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        return name;
    }
}
