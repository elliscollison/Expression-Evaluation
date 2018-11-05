import java.util.Map;

/**
 * Operator node of the expression.
 * Has a left right child node that is evaluable.
 */
public class BinOpNode implements Evaluable
{
    Evaluable left;  //reference to the left child node
    Evaluable right; //reference to the right child node
    String operator; //String representation of the operation to completed

    public BinOpNode(String operator){
        this.operator = operator;
    }

    /**
     * Sets the right child node of the operator node
     */
    public void setRight(Evaluable node){
        right = node;
    }

    /**
     * Sets the left child node of the operator node 
     */
    public void setLeft(Evaluable node){
        left = node;
    }

    /**
     * Evaluates the operator node by applying the operator to the left and right child nodes
     */
    public double evaluate(Map<String, Double> env){
        if(operator.equals("+")){
            //operator is + so add left and right
            return left.evaluate(env) + right.evaluate(env);
        }
        else if(operator.equals("-")){
            //operator is - so subtract right from left
            return left.evaluate(env) - right.evaluate(env);
        }
        else if(operator.equals("/")){
            //operator is / so divide left by right
            if (right.evaluate(env) == 0){
                //can't divide by zero
                throw new IllegalArgumentException("Divide by zero error");
            }
            return left.evaluate(env) / right.evaluate(env);         
        }
        else if(operator.equals("*")){
            //operator is * so multiply left by right
            return left.evaluate(env) * right.evaluate(env);
        }
        throw new IllegalArgumentException();
    }

    /**
     * @return a String representation in the form of left.toString + operator + right.toString
     */
    public String toString(){
        //Add parenth before and after each variable/value pair
        String s = "( " + left.toString() + " " + operator + " " + right.toString() + " )";
        return s;
    }
}
