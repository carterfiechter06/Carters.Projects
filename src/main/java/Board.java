import java.util.Random;

public class Board {

    public int[][] board = new int[4][4];

    public Board() {
        makeBoard();
    }

    public void makeBoard() {
        Random rand = new Random();

        int[] nums = new int[16];

        // initialize nums 1-15, and a 0
        for (int i = 0; i < 16; i++) {
            nums[i] = i;
        }

        // shuffle
        for (int i = nums.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // create board
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = nums[index++];
            }
        }
    }

    public int[] findZero() { //find the zero so you can swap
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // should never happen
    }

    public void swap(int r1, int c1, int r2, int c2) { // swap the 0 and one other number
        int temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }

    public boolean checkWin() {
        int expected = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (i == 3 && j == 3) {
                    return board[i][j] == 0;
                }

                if (board[i][j] != expected) {
                    return false;
                }

                expected++;
            }
        }

        return true;
    }

    public boolean move(char direction) {
        int[] pos = findZero();
        int r = pos[0];
        int c = pos[1];

        if (direction == 's') { // up
            if (r > 0) {
                swap(r, c, r - 1, c);
                return true;
            }
        }
        else if (direction == 'w') { // down
            if (r < 3) {
                swap(r, c, r + 1, c);
                return true;
            }
        }
        else if (direction == 'd') { // left
            if (c > 0) {
                swap(r, c, r, c - 1);
                return true;
            }
        }
        else if (direction == 'a') { // right
            if (c < 3) {
                swap(r, c, r, c + 1);
                return true;
            }
        }

        return false; // invalid move
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        String horizontalLine = "+----+----+----+----+\n";

        sb.append(horizontalLine);

        for (int i = 0; i < 4; i++) {
            sb.append("|");
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    sb.append("    |"); // empty tile
                } else {
                    sb.append(String.format(" %2d |", board[i][j]));
                }
            }
            sb.append("\n");
            sb.append(horizontalLine);
        }

        return sb.toString();
    }

}