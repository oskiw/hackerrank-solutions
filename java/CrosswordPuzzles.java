import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CrosswordPuzzles {

    static class Position {
        int x, y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
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

    static class Gap {
        Position start; //most top left position of the word
        int length;
        boolean down; //true if down, false if right

        Gap(Position s, int l, boolean d) {
            start = s;
            length = l;
            down = d;
        }

        List<Position> getPositions() {
            List<Position> positions = new ArrayList<>();
            Position cur = start;
            for (int i = 0; i < length; i++) {
                positions.add(cur);
                if (down) {
                    cur = new Position(cur.x + 1, cur.y);
                } else {
                    cur = new Position(cur.x, cur.y + 1);
                }
            }
            return positions;
        }
    }

    private static char[][] finalResult = new char[10][10];

    // Complete the crosswordPuzzle function below.
    private static String[] crosswordPuzzle(String[] crossword, String words) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                finalResult[i][j] = crossword[i].charAt(j);
            }
        }

        List<Gap> gaps = new ArrayList<>();
        Set<Position> savedGapPositions = new HashSet<>();
        for (int i = 0; i < crossword.length; i++) {
            for (int j = 0; j < crossword[i].length(); j++) {
                if (crossword[i].charAt(j) == '-') {
                    Position startPos = new Position(i, j);
                    if (i + 1 < crossword.length && crossword[i + 1].charAt(j) == '-' && !savedGapPositions.contains(new Position(i - 1, j))) {
                        int downLen = 1;
                        savedGapPositions.add(startPos);
                        int k = i + 1;
                        while (k < crossword.length && crossword[k].charAt(j) == '-') {
                            downLen++;
                            savedGapPositions.add(new Position(k, j));
                            k++;
                        }
                        gaps.add(new Gap(startPos, downLen, true));
                    }
                    if (j + 1 < crossword[i].length() && crossword[i].charAt(j + 1) == '-' && !savedGapPositions.contains(new Position(i, j - 1))) {
                        int leftLen = 1;
                        savedGapPositions.add(startPos);
                        int k = j + 1;
                        while (k < crossword[i].length() && crossword[i].charAt(k) == '-') {
                            leftLen++;
                            savedGapPositions.add(new Position(i, k));
                            k++;
                        }
                        gaps.add(new Gap(startPos, leftLen, false));
                    }
                }
            }
        }

        String[] wordsArray = words.split(";");
        Set<Gap> usedGaps = new HashSet<>();
        Gap[] assignments = new Gap[wordsArray.length];

        run(wordsArray, 0, gaps, usedGaps, assignments);

        String[] res = new String[10];
        for (int i = 0; i < 10; i++) {
            res[i] = new String(finalResult[i]);
        }

        return res;
    }

    private static boolean run(String[] wordsArray, int pos, List<Gap> gaps, Set<Gap> usedGaps, Gap[] assignments) {
        if (pos >= wordsArray.length) {
            return allowed(assignments, wordsArray);
        }
        for (Gap g : gaps) {
            if (g.length == wordsArray[pos].length() && !usedGaps.contains(g)) {
                usedGaps.add(g);
                assignments[pos] = g;
                boolean allowed = run(wordsArray, pos + 1, gaps, usedGaps, assignments);
                if (allowed) return true;
                assignments[pos] = null;
                usedGaps.remove(g);
            }
        }
        return false;
    }

    private static boolean allowed(Gap[] assignments, String[] wordsArray) {
        Map<Position, Character> filled = new HashMap<>();

        for (int i = 0; i < assignments.length; i++) {
            Gap gap = assignments[i];
            List<Position> positions = gap.getPositions();
            for (int k = 0; k < gap.length; k++) {
                Position curPos = positions.get(k);
                char letter = wordsArray[i].charAt(k);
                if (filled.containsKey(curPos) && !filled.get(curPos).equals(letter)) {
                    return false;
                }
                filled.put(curPos, letter);
                finalResult[curPos.x][curPos.y] = letter;
            }
        }

        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] crossword = new String[10];

        for (int i = 0; i < 10; i++) {
            String crosswordItem = scanner.nextLine();
            crossword[i] = crosswordItem;
        }

        String words = scanner.nextLine();

        String[] result = crosswordPuzzle(crossword, words);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
