import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rows = 3;
        int columns = 3;
        int counter = 1;
        String[][] array = new String[rows][columns];
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        while (true) {
            try {
                System.out.println("*****************************************");
                System.out.println("Welcome to the tic tac toe console game !");
                System.out.println("*****************************************");

                System.out.println("Choose game mode (press the number of the mode): ");
                System.out.println("1 - Player vs Computer ");
                System.out.println("2 - Player vs Player ");
                int game_mode = input.nextInt();

                if (game_mode != 1 && game_mode != 2) {
                    throw new IllegalArgumentException("Invalid game mode selection. Please select 1 or 2.");
                }

                System.out.print("Choose Round mode (1 or 3): ");
                int round = input.nextInt();

                if (round == 1 || round == 3) {
                    System.out.print("Choose O or X: ");
                    input.nextLine();
                    String user_choice = input.nextLine();
                    if (!user_choice.equalsIgnoreCase("x")&&!user_choice.equalsIgnoreCase("o")) {
                        throw new IllegalArgumentException("Invalid Selection , Only X or O ");
                    }

                    int position = 0;
                    ArrayList<String> placed_num = new ArrayList<>();
                    for (int k = 1; k <= round; k++) {
                        System.out.println("************************************");
                        System.out.println("Round " + k);
                        System.out.println("Start!!!");
                        System.out.println("************************************");
                        initialize(array, placed_num);
                        printGame(array, rows, columns, counter);

                        String user_choice2 = user_choice.equalsIgnoreCase("X") ? "O" : "X";

                        if (game_mode == 1) {
                            vsComputer(array, rows, columns, counter, input, position, rand, placed_num, user_choice);
                        } else {
                            vsPlayer(array, rows, columns, counter, input, position, user_choice, 0, user_choice2, placed_num);
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Invalid game mode selection. Two Round modes available, 1 or 3.");

                }
                break;

            } catch (InputMismatchException e) {
                System.out.println("************************************");
                System.out.println("Invalid selection: InputMismatch");
                System.out.println("************************************");
                input.nextLine(); // Consume the invalid input
            } catch (IllegalArgumentException e) {
                System.out.println("************************************");
                System.out.println(e.getMessage());
                System.out.println("************************************");
            } catch (Exception e) {
                System.out.println("************************************");
                System.out.println("Error happened, try again.");
                System.out.println("************************************");
            }
        }
    }

    public static void vsPlayer(String[][] array, int rows, int columns, int counter, Scanner input,
                                int position1, String user_choice1, int position2, String user_choice2, ArrayList<String> placed_num) {
        do {
            System.out.println("************************************");
            System.out.println("If you want to Quit press type -1 ");
            if (placed_num.size() %2==0) {

                System.out.print("Player1 enter the position you want to play (number from 1 - 9): ");
            position1 = input.nextInt();
                if (position1 <= 0 || position1 >= 10) {
                    System.out.println("Player1 Enter a number between 1-9");
                    continue;
                }
                if (placed_num.contains(Integer.toString(position1))) {
                    System.out.println("This number has already been placed.");
                    continue;
                }

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (array[i][j].equals(Integer.toString(position1))) {
                            array[i][j] = user_choice1;
                            placed_num.add(Integer.toString(position1));
                        }
                        System.out.print(array[i][j] + " ");

                        if (counter < 3) {
                            System.out.print("| ");
                            counter++;
                        }
                    }
                    counter = 1;
                    System.out.println();
                }

                if (checkWin(array, user_choice1)) {
                    System.out.println("************************************");
                    System.out.println("Player one wins!!");
                    System.out.println("Thank you for playing our game.");
                    break;
                }
                if (placed_num.size() == 9) {
                    System.out.println("************************************");
                    System.out.println("Draw!!");
                    System.out.println("Thank you for playing our game.");
                    break;
                }
            }
            System.out.println("*****************************************************");
            System.out.print("Player2 enter the position you want to play (number from 1 - 9): ");
            position2 = input.nextInt();
            if (position2 <= 0 || position2 >= 10) {
                System.out.println("Player2 Enter a number between 1-9");
                continue;
            }

            if (placed_num.contains(Integer.toString(position2))) {
                System.out.println("This number has already been placed.");
                continue;
            }
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (array[i][j].equals(Integer.toString(position2))) {
                        array[i][j] = user_choice2;
                        placed_num.add(Integer.toString(position2));
                    }
                    System.out.print(array[i][j] + " ");

                    if (counter < 3) {
                        System.out.print("| ");
                        counter++;
                    }
                }
                counter = 1;
                System.out.println();
            }

            if (checkWin(array, user_choice2)) {
                System.out.println("************************************");
                System.out.println("Player two wins!!");
                System.out.println("Thank you for playing our game.");
                break;
            }
            if (placed_num.size() == 9) {
                System.out.println("************************************");
                System.out.println("Draw!!");
                System.out.println("Thank you for playing our game.");
                break;
            }
        } while (position1 != -1 && position2 != -1);
    }

    public static void vsComputer(String[][] array, int rows, int columns, int counter, Scanner input,
                                  int position, Random rand, ArrayList<String> placed_num, String user_choice) {
        do {
            System.out.println("************************************");
            System.out.println("If you want to Quit press type -1 ");
            System.out.print("Enter the position you want to play (number from 1 - 9): ");



            position = input.nextInt();
            if (position<=0 ||position>=10){
                System.out.println("Enter a number between 1-9");
                continue;
            }

            int randomNum;
            do {
                randomNum = rand.nextInt(9) + 1;
                if (placed_num.size() == 8) {
                    break;
                }
            } while (placed_num.contains(Integer.toString(randomNum)) || randomNum == position);

            if (placed_num.contains(Integer.toString(position))) {
                System.out.println("This number has already been placed.");
                continue;
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    if (array[i][j].equals(Integer.toString(position))) {
                        array[i][j] = user_choice;
                        placed_num.add(Integer.toString(position));
                    }
                    if (array[i][j].equals(Integer.toString(randomNum))) {
                        placed_num.add(Integer.toString(randomNum));
                        array[i][j] = user_choice.equalsIgnoreCase("X") ? "O" : "X";
                    }
                    System.out.print(array[i][j] + " ");

                    if (counter < 3) {
                        System.out.print("| ");
                        counter++;
                    }
                }
                counter = 1;
                System.out.println();
            }

            if (checkWin(array, user_choice)) {
                System.out.println("************************************");
                System.out.println("You Win!!");
                System.out.println("Thank you for playing our game.");
                break;
            }
            if (checkWin(array, user_choice.equalsIgnoreCase("X") ? "O" : "X")) {
                System.out.println("************************************");
                System.out.println("You Lose!!");
                System.out.println("Thank you for playing our game.");
                break;
            }
            if (placed_num.size() == 9) {
                System.out.println("************************************");
                System.out.println("Draw!!");
                System.out.println("Thank you for playing our game.");
                break;
            }
        } while (position != -1);
    }

    public static void printGame(String[][] array, int rows, int columns, int counter) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + " ");

                if (counter < 3) {
                    System.out.print("| ");
                    counter++;
                }
            }
            counter = 1;
            System.out.println();
        }
    }

    public static void initialize(String[][] array, ArrayList<String> placed_num) {
        array[0][0] = "1";
        array[0][1] = "2";
        array[0][2] = "3";
        array[1][0] = "4";
        array[1][1] = "5";
        array[1][2] = "6";
        array[2][0] = "7";
        array[2][1] = "8";
        array[2][2] = "9";
        placed_num.clear();
    }

    public static boolean checkWin(String[][] array, String user_choice) {
        return array[0][0].equalsIgnoreCase(user_choice) && array[1][0].equalsIgnoreCase(user_choice) && array[2][0].equalsIgnoreCase(user_choice) ||
                array[0][1].equalsIgnoreCase(user_choice) && array[1][1].equalsIgnoreCase(user_choice) && array[2][1].equalsIgnoreCase(user_choice) ||
                array[0][2].equalsIgnoreCase(user_choice) && array[1][2].equalsIgnoreCase(user_choice) && array[2][2].equalsIgnoreCase(user_choice) ||
                array[0][0].equalsIgnoreCase(user_choice) && array[0][1].equalsIgnoreCase(user_choice) && array[0][2].equalsIgnoreCase(user_choice) ||
                array[1][0].equalsIgnoreCase(user_choice) && array[1][1].equalsIgnoreCase(user_choice) && array[1][2].equalsIgnoreCase(user_choice) ||
                array[2][0].equalsIgnoreCase(user_choice) && array[2][1].equalsIgnoreCase(user_choice) && array[2][2].equalsIgnoreCase(user_choice) ||
                array[0][0].equalsIgnoreCase(user_choice) && array[1][1].equalsIgnoreCase(user_choice) && array[2][2].equalsIgnoreCase(user_choice) ||
                array[0][2].equalsIgnoreCase(user_choice) && array[1][1].equalsIgnoreCase(user_choice) && array[2][0].equalsIgnoreCase(user_choice);
    }
}
