import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Converts a String formula in infix notation to RPN notation. To be used for the expression tree. 
 */
public class Infix2RPN
{
    /**
     * Converts infix to postfix (RPN)
     * @param infix An infix expression with space separating every symbol. For instance
     *              "( ( 4 + 4 ) / ( 0 - 3 ) )"
     */
    public static String convert(String infix) {
        //throw new NotImplementedException();
        Queue<String> expStack = new LinkedList(); //queue of numbers
        Stack<String> opStack = new Stack<>();
        String[] split = infix.split(" ");

        String expression = "";
        for(int i = 0; i < split.length; i++){
            try{
                double test = Double.parseDouble(split[i]);
                expStack.add(split[i]);
            }
            catch(Exception e){ 
                //split[i] is not a number
                if((split[i].equals("+") || split[i].equals("-") || split[i].equals("/") || split[i].equals("*"))){
                    //character is an operator
                    opStack.add(split[i]);
                }
                else if ((!(split[i].equals("("))) && (!(split[i].equals(")")))){
                    expStack.add(split[i]);
                }

                if (split[i].equals(")")){
                    //closing parenthesis comes so operator must be added
                    expStack.add(opStack.pop());
                }
            }
        }

        //add expressions to the String and then the remaining operators

        while(!(expStack.isEmpty())){
            expression += expStack.poll();
            expression += " ";
        }

        while(!(opStack.isEmpty())){
            expression += opStack.pop();
            expression += " ";
        }
        return expression;
    }
    
}