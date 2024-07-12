import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int num1 = 0;
        int num2 = 0;
        ArrayList<String> list = new ArrayList<>();

        try {
            System.out.print("Enter the first number 1: ");
            num1 = input.nextInt();
            System.out.print("Enter the first number 2: ");
            num2 = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers.");
            input.nextLine();
            return;
        }

        do {
            System.out.println("*************************************************");
            System.out.println("Enter 1 to addition the numbers\n" +
                    "Enter 2 to subtraction the numbers\n" +
                    "Enter 3 to multiplication the numbers\n" +
                    "Enter 4 to division the numbers\n" +
                    "Enter 5 to modulus the numbers\n" +
                    "Enter 6 to find minimum number\n" +
                    "Enter 7 to find maximum number\n" +
                    "Enter 8 to find the average of numbers\n" +
                    "Enter 9 to print the last result in calculator\n" +
                    "Enter 10 to print the list of all results in calculator\n" +
                    "Enter 11 to exit");
            try {
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        addition(num1, num2, list);
                        break;
                    case 2:
                        subtraction(num1, num2, list);
                        break;
                    case 3:
                        multiplication(num1, num2, list);
                        break;
                    case 4:
                        division(num1, num2, list);
                        break;
                    case 5:
                        modulus(num1, num2, list);
                        break;
                    case 6:
                        minimum(num1, num2, list);
                        break;
                    case 7:
                        maximum(num1, num2, list);
                        break;
                    case 8:
                        average(num1, num2, list);
                        break;
                    case 9:
                        last(list);
                        break;
                    case 10:
                        printAll(list);
                        break;
                    case 11:
                        System.out.println("************************************");
                        System.out.println("Thank you for using my calculator");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic error: " + e.getMessage());
            }
        } while (choice != 11);
    }

    public static void addition(int num1, int num2, ArrayList<String> list) {
        int result = num1 + num2;
        System.out.println("************************************");
        System.out.println("The addition of numbers: " + result);
        list.add("Addition: " + result);
    }

    public static void subtraction(int num1, int num2, ArrayList<String> list) {
        int result = num1 - num2;
        System.out.println("************************************");
        System.out.println("The subtraction of numbers: " + result);
        list.add("Subtraction: " + result);
    }

    public static void multiplication(int num1, int num2, ArrayList<String> list) {
        int result = num1 * num2;
        System.out.println("************************************");
        System.out.println("The multiplication of numbers: " + result);
        list.add("Multiplication: " + result);
    }

    public static void division(int num1, int num2, ArrayList<String> list) {
        try {
            int result = num1 / num2;
            System.out.println("************************************");
            System.out.println("The division of numbers: " + result);
            list.add("Division: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        }
    }

    public static void modulus(int num1, int num2, ArrayList<String> list) {
        try {
            int result = num1 % num2;
            System.out.println("************************************");
            System.out.println("The modulus of numbers: " + result);
            list.add("Modulus: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Modulus by zero is not allowed.");
        }
    }

    public static void minimum(int num1, int num2, ArrayList<String> list) {
        int result = Math.min(num1, num2);
        System.out.println("************************************");
        System.out.println("The minimum number is: " + result);
        list.add("Minimum: " + result);
    }

    public static void maximum(int num1, int num2, ArrayList<String> list) {
        int result = Math.max(num1, num2);
        System.out.println("************************************");
        System.out.println("The maximum number is: " + result);
        list.add("Maximum: " + result);
    }

    public static void average(int num1, int num2, ArrayList<String> list) {
        int result = (num1 + num2) / 2;
        System.out.println("************************************");
        System.out.println("The average is: " + result);
        list.add("Average: " + result);
    }

    public static void last(ArrayList<String> list) {
        if (!list.isEmpty()) {
            String last = list.get(list.size() - 1);
            System.out.println("************************************");
            System.out.println("The last result is: " + last);
        } else {
            System.out.println("No results in the list.");
        }
    }

    public static void printAll(ArrayList<String> list) {
        System.out.println("************************************");
        System.out.println("All results:");
        for (String result : list) {
            System.out.println(result);
        }
    }
}
