import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Scanner sc = new Scanner(System.in);
        int moves = 0;

        displayLeaderboard();

        startGame();

        String name = sc.nextLine();
        System.out.println("\n");

        while (!b.checkWin()) {

            System.out.println(b);
            System.out.println("---- Moves: " + moves + " ----");

            System.out.print("\nMove (w/a/s/d): ");
            char move = sc.next().charAt(0);

            boolean moved = b.move(move);

            if (moved) {
                moves++;
            } else {
                System.out.println("Invalid move!");
            }
        }


        System.out.println(b);
        System.out.println("YOU WIN!");
        System.out.println("Solved in " + moves + " moves!");

        saveScore(name, moves);
    }



    public static void saveScore(String name, int moves) {
        try {
            FileWriter writer = new FileWriter("scores.txt", true); // true = append

            writer.write("\n" + name + "-" + moves);

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving score.");
        }
    }

    public static void displayLeaderboard() {
        try {
            File file = new File("scores.txt");
            Scanner reader = new Scanner(file);

            // store name and moves
            ArrayList<String[]> scores = new ArrayList<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                // split by " - "
                String[] parts = line.split("-");
                String name = parts[0].trim();
                int moves = Integer.parseInt(parts[1].replace("moves", "").trim());

                scores.add(new String[]{name, String.valueOf(moves)});
            }

            reader.close();

            // sort by moves (ascending)
            scores.sort(Comparator.comparingInt(a -> Integer.parseInt(a[1])));

            // print leaderboard
            System.out.println("---- Leaderboard ----");
            for (String[] entry : scores) {
                System.out.println(entry[0] + " - " + entry[1] + " moves");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No scores yet.");
        }
    }

    public static void startGame() {
        System.out.println("\n------------------------------");
        System.out.println("Welcome to the 15-Puzzle Game!");
        System.out.println("------------------------------");
        System.out.print("\nEnter your name: ");
    }
}
