import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a mathematical expression (e.g., 15 + 23 * 2 - 10):");
        String expression = scanner.nextLine();

        if (!validateExpression(expression)) {
            System.out.println("Invalid expression format.");
        } else {
            evaluate(expression);
        }
        scanner.close();
    }
    
    public static boolean validateExpression(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // Check if character is a digit, operator, or space
            if (!((c >= '0' && c <= '9') || c == '+' || c == '-' || c == '*' || c == '/' || c == ' ')) {
                return false;
            }
        }
        return true;
    }

    public static void evaluate(String expression) {
        // Parse numbers and operators
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        StringBuilder currentNumber = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c >= '0' && c <= '9' || c == '.') {
                currentNumber.append(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                if (currentNumber.length() > 0) {
                    numbers.add(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                operators.add(c);
            }
        }
        if (currentNumber.length() > 0) {
            numbers.add(Double.parseDouble(currentNumber.toString()));
        }

        System.out.println("\n--- Calculation Steps ---");
        System.out.println("Original Expression: " + expression);

        // Step 1: Handle Multiplication and Division
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*' || operators.get(i) == '/') {
                double result = 0;
                if (operators.get(i) == '*') {
                    result = numbers.get(i) * numbers.get(i + 1);
                } else {
                    result = numbers.get(i) / numbers.get(i + 1);
                }
                System.out.printf("Step: %s %c %s = %s%n", numbers.get(i), operators.get(i), numbers.get(i+1), result);
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; // Adjust index after removal
            }
        }

        // Step 2: Handle Addition and Subtraction
        while (!operators.isEmpty()) {
            double result = 0;
            if (operators.get(0) == '+') {
                result = numbers.get(0) + numbers.get(1);
            } else {
                result = numbers.get(0) - numbers.get(1);
            }
            System.out.printf("Step: %s %c %s = %s%n", numbers.get(0), operators.get(0), numbers.get(1), result);
            numbers.set(0, result);
            numbers.remove(1);
            operators.remove(0);
        }

        System.out.println("-------------------------");
        System.out.println("Final Result: " + numbers.get(0));
    }
}