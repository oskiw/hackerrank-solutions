import java.util.*;

/*
 * https://www.hackerrank.com/challenges/p-sequences
 *
 * Created by oskiw on 01/06/2016.
 */
public class PSequences {

    private static int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int N = reader.nextInt();
        int P = reader.nextInt();
        NavigableMap<Long,Long> pairs = generatePairsMap(P);
        List<Long> counter = new ArrayList<>();
        List<Long> amount = new ArrayList<>();

        amount.addAll(pairs.values());

        long currentCounter = 0;
        for (Long e : pairs.descendingMap().values()) {
            counter.add(e - currentCounter);
            currentCounter = e;
        }

        for (int i = 3; i <= N; i++) {
            long current = 0;
            for (int j=0; j < amount.size(); j++) {
                amount.set(j, ((amount.get(j) * counter.get(j) % MOD) + current) % MOD);
                current = amount.get(j);
            }
            Collections.reverse(amount);
        }

        long result = 0;
        for (int i=0; i < amount.size(); i++) {
            result = (result + (amount.get(i) * counter.get(i) % MOD)) % MOD;
        }

        System.out.println(result);
    }

    private static NavigableMap<Long,Long> generatePairsMap(int p) {
        int max = (int) Math.floor(Math.sqrt(p));
        NavigableMap<Long, Long> result = new TreeMap<>();
        for (long i = 1; i <= max; i++) {

            long v =  p / i;
            result.put(i,v);
            if (!result.containsKey(v)) {
                result.put(v,i);
            }
        }
        return result;
    }
}
