import java.util.Map;

public interface Evaluable {
   double evaluate(Map<String, Double> env);
   String toString();
}