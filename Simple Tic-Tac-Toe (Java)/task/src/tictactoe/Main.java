package tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] input = emptyTable();
        checkMove(input);
    }

    private static String[][] emptyTable() {
        String[][] ticTac = new String[3][3];

        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (counter < 9) {
                    ticTac[i][j] = " ";
                    counter++;
                }
            }
        }

        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("|" + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTac[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");

        return ticTac;

    }

    private static void displayInput(String[][] ticTac) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("|" + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(ticTac[i][j] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        System.out.println("---------");
    }

    private static void checkMove(String[][] ticTac) {
        boolean putX = true, putO = false;

        while (true) {
            Scanner input = new Scanner(System.in);
            String[] coordinates = input.nextLine().split(" ");

            if (!coordinates[0].matches("[0-9]+") || !coordinates[1].matches("[0-9]+")) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (Integer.parseInt(coordinates[0]) > 3 || Integer.parseInt(coordinates[1]) > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (Objects.equals(ticTac[Integer.parseInt(coordinates[0]) - 1][Integer.parseInt(coordinates[1]) - 1], "X") ||
                    Objects.equals(ticTac[Integer.parseInt(coordinates[0]) - 1][Integer.parseInt(coordinates[1]) - 1], "O")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            if (putX) {
                ticTac[Integer.parseInt(coordinates[0]) - 1][Integer.parseInt(coordinates[1]) - 1] = "X";
                putX = false;
                putO = true;
                displayInput(ticTac);
            } else if (putO) {
                ticTac[Integer.parseInt(coordinates[0]) - 1][Integer.parseInt(coordinates[1]) - 1] = "O";
                putO = false;
                putX = true;
                displayInput(ticTac);
            }

            checkScenario(ticTac);

        }
    }

    private static void checkScenario(String[][] ticTac) {

        boolean xWins = false, oWins = false, emptyCells = false;
        int principalDiagonalX = 0, principalDiagonalO = 0, secondaryDiagonalX = 0, secondaryDiagonalO = 0,
                counterOfXs = 0, counterOfOs = 0;


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTac[i][0].equals(ticTac[i][1]) && ticTac[i][1].equals(ticTac[i][2])
                        && ticTac[i][2].equals("X")) {
                    xWins = true;
                } else if (ticTac[i][0].equals(ticTac[i][1]) && ticTac[i][1].equals(ticTac[i][2])
                        && ticTac[i][2].equals("O")) {
                    oWins = true;
                }

                if (ticTac[0][i].equals(ticTac[1][i]) && ticTac[1][i].equals(ticTac[2][i])
                        && ticTac[2][i].equals("X")) {
                    xWins = true;
                }

                if (ticTac[0][i].equals(ticTac[1][i]) && ticTac[1][i].equals(ticTac[2][i])
                        && ticTac[2][i].equals("O")) {
                    oWins = true;
                }

                if (i == j) {
                    if (ticTac[i][j].equals("X")) {
                        principalDiagonalX++;
                    } else if (ticTac[i][j].equals("O")) {
                        principalDiagonalO++;
                    }
                }

                if (i + j == 2) {
                    if (ticTac[i][j].equals("X")) {
                        secondaryDiagonalX++;
                    } else if (ticTac[i][j].equals("O")) {
                        secondaryDiagonalO++;
                    }
                }

                if (ticTac[i][j].equals(" ")) {
                    emptyCells = true;
                }

                if (ticTac[i][j].equals("X")) {
                    counterOfXs++;
                } else if (ticTac[i][j].equals("O")) {
                    counterOfOs++;
                }
            }
        }

        if (xWins || principalDiagonalX == 3 || secondaryDiagonalX == 3) {
            System.out.println("X wins");
            System.exit(0);
        } else if (oWins || principalDiagonalO == 3 || secondaryDiagonalO == 3) {
            System.out.println("O wins");
            System.exit(0);
        }

        if (!xWins && !oWins && !emptyCells && principalDiagonalX != 3 && secondaryDiagonalX != 3
                && principalDiagonalO != 3 && secondaryDiagonalO != 3) {
            System.out.println("Draw");
            System.exit(0);
        }

        if (xWins && oWins || counterOfXs - counterOfOs >= 2 || counterOfOs - counterOfXs >= 2) {
            System.out.println("Impossible");
        }

    }
}
