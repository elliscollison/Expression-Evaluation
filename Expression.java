import java.util.Set;
import java.util.Map;
import java.util.Stack;
import java.util.HashSet;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Expression
{
    private Evaluable root; //root node of the expression tree
    private Set<String> vars; //a set of variable names in the expression

    /**
     * Creates an expression from a String in infix notation. Will be converted to RPN to store. 
     * Each operator, value, variable and parenthesis should be seperated by a space.
     * Stores a set of all variables present in the expression.
     */
    public Expression(String infix){
        String RPN = Infix2RPN.convert(infix); //converts the infix String to RPN
        String[] split = RPN.split(" "); //splits the conversion by each space (each value is now a seperate String)
        vars = new HashSet<>();
        Stack<Evaluable> stack = new Stack<>();

        /** Add variable names to the set of variables*/
        for (int i = 0; i < split.length; i++){
            try{
                double test = Double.parseDouble(split[i]);
            }
            catch(Exception e){
                if (!(split[i].equals("(")) && !(split[i].equals(")")) && !(split[i].equals("+")) 
                && !(split[i].equals("-")) && !(split[i].equals("/")) && !(split[i].equals("*"))){
                    vars.add(split[i]);
                }
            }
        }

        /**Build expression tree */
        for (int i = 0; i < split.length; i++){ /** Might need to stop one before the end to create the head node */
            try{
                double test = Double.parseDouble(split[i]);

                /** If this is reached, the String is a Value */
                ValueNode value = new ValueNode(Double.parseDouble(split[i]));

                /** Expression is only the value, so the root should be the value */
                if (i == split.length - 1 && split.length == 1){
                    root = value;
                }

                stack.add(value);
            }
            catch(Exception e){
                if(split[i].equals("+") || split[i].equals("-") || split[i].equals("/") || split[i].equals("*")){
                    /** If this statement is entered, the String is an operator */
                    BinOpNode operator = new BinOpNode(split[i]);

                    /** Set left and right nodes of the operator node by popping off of the top two items on a stack of nodes */
                    operator.right = stack.pop();
                    operator.left = stack.pop();

                    stack.add(operator);
                    if (i == split.length - 1){
                        root = operator;
                    }
                }
                else{
                    /** If this statement is entered, the String is a variable */
                   
                    VariableNode variable = new VariableNode(split[i]);

                    /** Expression is only the variable, so the root should be the variable */
                    if(i == split.length - 1 && split.length == 1){
                        root = variable;
                    }
                    
                    stack.add(variable);
                }
            }      
        }
    }

    /**
     * Evaluates the current expression based on the root node of the expression
     * @param env The map of variables and values to be used to evaluate the expression. If it is null, and there are variables present in the expression an
     * IllegalArgumentException will be thrown.
     */
    public double evaluate(Map<String, Double> env){
        return root.evaluate(env);
    }

    /**
     * @return vars The set of variables in the expression
     */
    public Set<String> getVariables(){
        return vars;
    }

    /**
     * Returns an infix summary of the expression.
     * The expression is not stored so this is done by traversing the tree and returning the toString of each node. 
     */
    public String toString(){
        /** This needs to be done by traversing the tree */
        //The root toString will summarize the whole expression
        return root.toString();
    }
}
