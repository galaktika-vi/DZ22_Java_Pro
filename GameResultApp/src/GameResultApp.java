import java.util.Scanner;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

class GameSaveResult {
    String name;
    int winCounter;
    int loseCounter;

    public GameSaveResult(String name) {
        this.name = name;
        this.winCounter = 0;
        this.loseCounter = 0;
    }

    @Override
    public String toString() {
        return "Игрок: " + name + "\nПобед: " + winCounter + "\nПоражений: " + loseCounter + "\n";
    }
}

public class GameResultApp {
    private static final String FILE_NAME = "result.txt";

    private static void saveResult(GameSaveResult result) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME, true), StandardCharsets.UTF_8))) {
            writer.write(result.toString());
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении результатов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        GameSaveResult currentPlayer = null;

        while (true) {
            if (currentPlayer == null) {
                System.out.println("Введите имя игрока:");
                String name = scanner.nextLine();
                currentPlayer = new GameSaveResult(name);
            }

            Random random = new Random();
            boolean isWin = random.nextBoolean();
            if (isWin) {
                currentPlayer.winCounter++;
            } else {
                currentPlayer.loseCounter++;
            }
            saveResult(currentPlayer);
            System.out.println(currentPlayer);

            System.out.println("Введите имя следующего игрока");
            String input = scanner.nextLine();

            if ("нет".equalsIgnoreCase(input)) {
                currentPlayer = null;
            } else if ("выход".equalsIgnoreCase(input)) {
                break;
            }
        }

        scanner.close();
    }
}
