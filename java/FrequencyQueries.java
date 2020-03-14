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

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> dict = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> result = new LinkedList<>();

        for (List<Integer> query : queries) {
            Integer val = query.get(1);
            if (query.get(0) == 1) {
                if (dict.containsKey(val)) {
                    Integer count = dict.get(val);
                    updateFrequency(freq, count, count + 1);
                    dict.put(val, count + 1);
                } else {
                    dict.put(val, 1);
                    updateFrequency(freq, 0, 1);
                }
            } else if (query.get(0) == 2) {
                if (dict.containsKey(val)) {
                    Integer count = dict.get(val);
                    updateFrequency(freq, count, count - 1);
                    if (count == 1) {
                        dict.remove(val);
                    } else {
                        dict.put(val, count - 1);
                    }
                }
            } else if (query.get(0) == 3) {
                result.add(freq.containsKey(val) ? 1 : 0);
            }
        }

        return result;
    }

    private static void updateFrequency(Map<Integer, Integer> freq, Integer oldCount, Integer newCount) {
        //add new
        if (newCount > 0) {
            if (freq.containsKey(newCount)) {
                Integer value = freq.get(newCount);
                freq.put(newCount, value + 1);
            } else {
                freq.put(newCount, 1);
            }
        }

        // remove old
        if (oldCount > 0) {
            Integer value = freq.get(oldCount);
            if (value > 1) {
                freq.put(oldCount, value - 1);
            } else {
                freq.remove(oldCount);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
