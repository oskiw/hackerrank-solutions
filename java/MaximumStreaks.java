import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'getMaxStreaks' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY toss as parameter.
     */

    public static List<Integer> getMaxStreaks(List<String> toss) {
        // Return an array of two integers containing the maximum streak of heads and tails respectively

        int curH = 0, maxH = 0, curT = 0, maxT = 0;

        for (String t : toss) {
            if ("Heads".equals(t)) {
                curH++;
                if (maxH < curH) maxH = curH;
                curT = 0;
            } else if ("Tails".equals(t)) {
                curT++;
                if (maxT < curT) maxT = curT;
                curH = 0;
            }
        }

        return Arrays.asList(maxH, maxT);
    }

}

public class MaximumStreaks {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int tossCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> toss = IntStream.range(0, tossCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<Integer> ans = Result.getMaxStreaks(toss);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
