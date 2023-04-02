import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[] password = {'b', 'o', 'b'};
        char[] playerGuesses = {};
        int lives = 6;

        System.out.println("~~~~~~~~~~~~");
        System.out.println("HANGMAN GAME");
        System.out.println("~~~~~~~~~~~~");

        while (lives > 0) {
            displayBlankedPassword(password, playerGuesses);

            System.out.print("Podaj literę: ");
            Scanner scanner = new Scanner(System.in);
            char userInput = scanner.nextLine().charAt(0);

            boolean isLetterInPassword = false;
            for (int i = 0; i < password.length; i++) {
                if (userInput == password[i]) {
                    isLetterInPassword = true;
                }
            }
            if (!isLetterInPassword) {
                lives--;
            }

            char[] newPlayerGuesses = new char[playerGuesses.length + 1];
            for (int i = 0; i < newPlayerGuesses.length; i++) {
                if (i < newPlayerGuesses.length - 1) {
                    newPlayerGuesses[i] = playerGuesses[i];
                } else {
                    newPlayerGuesses[newPlayerGuesses.length - 1] = userInput;
                }
            }
            playerGuesses = newPlayerGuesses;

            displayUsedLetters(playerGuesses);
            displayBlankedPassword(password,playerGuesses);

            if (passwordGuessed(password, playerGuesses)) {
                break;
            }

            drawHangman(lives);
        }

        if (lives > 0) {
            System.out.println("\nGOOD JOB!\n");
        } else {
            System.out.println("\nYOU LOST!\n");
        }
    }

    public static void drawHangman(int lives) {
        char[][] emptyBoard = {
                {' ',' ',' ',' '},
                {' ',' ',' ',' '},
                {' ',' ',' ',' '},
                {' ',' ',' ',' '},
                {' ',' ',' ',' '}
        };

        switch (lives) {
            case 0:
                emptyBoard[4][3] = '^';
            case 1:
                emptyBoard[3][3] = '+';
            case 2:
                emptyBoard[2][3] = 'O';
            case 3:
                emptyBoard[1][3] = '|';
            case 4:
                emptyBoard[0][0] = '_';
                emptyBoard[0][1] = '_';
                emptyBoard[0][2] = '_';
                emptyBoard[0][3] = '_';
            case 5:
                emptyBoard[1][0] = '|';
                emptyBoard[2][0] = '|';
                emptyBoard[3][0] = '|';
                emptyBoard[4][0] = '|';
        }

        for (int i = 0; i < emptyBoard.length; i++) {
            System.out.println(emptyBoard[i]);
        }
    }

    public static void displayUsedLetters(char[] letters) {
        System.out.print("Złużyte literki: ");
        for (int i = 0; i < letters.length; i++) {
            System.out.print(letters[i]);
            if (i != letters.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void displayBlankedPassword(char[] password, char[] gueses) {
        char[] passwordBlancked = new char[2 * password.length - 1];
        for (int i = 0; i < passwordBlancked.length; i++) {
            if (i % 2 == 0) {
                passwordBlancked[i] = '_';
            } else {
                passwordBlancked[i] = ' ';
            }
        }
        for (int i = 0; i < password.length; i++) {
            boolean isGuessed = false;
            for (int j = 0; j < gueses.length; j++) {
                if (gueses[j] == password[i]) {
                    isGuessed = true;
                }
            }
            if (isGuessed) {
                passwordBlancked[2*i] = password[i];
            }
        }
        System.out.print("hasło: ");
        System.out.println(passwordBlancked);
    }

    public static boolean passwordGuessed(char[] password, char[] usedLetters) {
        for (int i = 0; i < password.length; i++) {
            boolean isLetterGuessed = false;
            for (int j = 0; j < usedLetters.length; j++) {
                if (password[i] == usedLetters[j]) {
                    isLetterGuessed = true;
                    break;
                }
            }
            if (!isLetterGuessed) return false;
        }
        return true;
    }
}