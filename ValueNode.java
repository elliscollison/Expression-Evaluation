import java.util.Map;

/**
 * Value node of the expression
 * Does not have child nodes
 */
public class ValueNode implements Evaluable
{
    double value; //value of the node
    
    public ValueNode(double val){
        value = val;
    }
    
    public double evaluate(Map<String, Double> env){
        return value;
    }
    
    public String toString(){
        String s = "" + value;
        return s;
    }
}
