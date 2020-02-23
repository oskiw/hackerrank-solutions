import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountTriplets {

    public static class Pair {
        Long first, second;

        public Pair(Long s) {
            first = 1L;
            second = s;
        }

        void add(Long s) {
            first++;
            second += s;
        }
    }

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        long solution = 0;
        Map<Long, Pair> map = new HashMap<>();

        long second = 0;

        for (Long number : arr) {
            long prev = number / r;
            if (number % r != 0 || !map.containsKey(prev)) {
                second = 0;
            } else {
                Pair pair = map.get(prev);
                second = pair.first;
                solution += pair.second;
            }

            if (map.containsKey(number)) {
                Pair pair = map.get(number);

                pair.add(second);
            } else {
                map.put(number, new Pair(second));
            }
        }

        return solution;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}