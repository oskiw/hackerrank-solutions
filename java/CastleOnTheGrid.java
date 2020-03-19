import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CastleOnTheGrid {

    static class Position {
        int x, y;

        Position(int i, int j) {
            x = i;
            y = j;
        }

        boolean withinBoundries(String[] grid) {
            return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length() && grid[x].charAt(y) != 'X';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class PositionWithDistance {
        Position pos;
        int distance;
        PositionWithDistance(Position p, int d) {
            pos = p;
            distance = d;
        }
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        Set<Position> added = new HashSet<>();
        Queue<PositionWithDistance> queue = new LinkedList<>();
        Position start = new Position(startX, startY);
        queue.add(new PositionWithDistance(start, 0));
        added.add(start);

        while (!queue.isEmpty()) {
            PositionWithDistance pwd = queue.poll();

            if (pwd.pos.x == goalX && pwd.pos.y == goalY) {
                return pwd.distance;
            }


            Position up = new Position(pwd.pos.x - 1, pwd.pos.y);
            while (up.withinBoundries(grid)) {
                if (!added.contains(up)) {
                    queue.add(new PositionWithDistance(up, pwd.distance + 1));
                    added.add(up);
                }
                up = new Position(up.x - 1, up.y);
            }
            Position down = new Position(pwd.pos.x + 1, pwd.pos.y);
            while (down.withinBoundries(grid)) {
                if (!added.contains(down)) {
                    queue.add(new PositionWithDistance(down, pwd.distance + 1));
                    added.add(down);
                }
                down = new Position(down.x + 1, down.y);
            }
            Position left = new Position(pwd.pos.x, pwd.pos.y - 1);
            while (left.withinBoundries(grid)) {
                if (!added.contains(left)) {
                    queue.add(new PositionWithDistance(left, pwd.distance + 1));
                    added.add(left);
                }
                left = new Position(left.x, left.y - 1);
            }
            Position right = new Position(pwd.pos.x, pwd.pos.y + 1);
            while (right.withinBoundries(grid)) {
                if (!added.contains(right)) {
                    queue.add(new PositionWithDistance(right, pwd.distance + 1));
                    added.add(right);
                }
                right = new Position(right.x, right.y + 1);
            }
        }

        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
