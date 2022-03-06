import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение с дробями: ");
        String expr = scanner.nextLine().replace(" ", "");
        Fraction result = Fraction.SolveExpression(expr);
        System.out.println("Result: " + result.toString());

    }
}