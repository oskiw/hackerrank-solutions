/*
 * https://www.hackerrank.com/challenges/beautiful-pairs
 *
 * Created by oskiw on 23/11/2016.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BeautifulPairs {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int n = reader.nextInt();

        List<Integer> A = new ArrayList<>(n);
        List<Integer> B = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            A.add(reader.nextInt());
        }
        for (int i=0; i<n; i++) {
            B.add(reader.nextInt());
        }

        Collections.sort(A);
        Collections.sort(B);

        int a = 0, b = 0, result = 0;
        while (a < n && b < n) {
            if (A.get(a).equals(B.get(b))) {
                result++;
                a++;
                b++;
            } else if (A.get(a) > B.get(b)) {
                b++;
            } else {
                a++;
            }
        }

        System.out.println(result != n ? ++result : --result);
    }
}
