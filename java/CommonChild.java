import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CommonChild {

    public static class Node {
        boolean marked;
        int count;
    }

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {

        Node prevNode = null;

        Node[] prevMatrix = new Node[s2.length()];
        Node[] currMatrix = new Node[s2.length()];

        for (int i = 0; i < s1.length(); i++) {
            boolean used = false;
            for (int j = 0; j < s2.length(); j++) {

                if (i == 0 && j == 0) {
                    Node aCandidate = new Node();
                    if (s1.charAt(i) == s2.charAt(j)) {
                        used = true;
                        aCandidate.count = 1;
                        aCandidate.marked = true;
                    } else {
                        used = false;
                        aCandidate.count = 0;
                        aCandidate.marked = false;
                    }

                    currMatrix[0] = aCandidate;
                    prevNode = aCandidate;

                } else {

                    Node aboveCandidate = new Node();
                    boolean aboveCandidateUsed = used;

                    //above
                    if (i - 1 >= 0) {
                        if (prevMatrix[j].marked) {
                            aboveCandidate.count = prevMatrix[j].count;
                            aboveCandidate.marked = true;
                            aboveCandidateUsed = false;
                        } else if (s1.charAt(i) == s2.charAt(j)) {
                            aboveCandidate.count = prevMatrix[j].count + 1;
                            aboveCandidate.marked = true;
                            aboveCandidateUsed = true;
                        } else {
                            aboveCandidate.count = prevMatrix[j].count;
                            aboveCandidate.marked = false;
                            aboveCandidateUsed = false;
                        }
                    }

                    Node leftCandidate = new Node();
                    boolean leftCandidateUsed = used;

                    //on the left
                    if (j - 1 >= 0) {
                        if (used) {
                            leftCandidate.count = prevNode.count;
                            leftCandidate.marked = false;
                            leftCandidateUsed = true;
                        } else if (s1.charAt(i) == s2.charAt(j)) {
                            leftCandidate.count = prevNode.count + 1;
                            leftCandidate.marked = true;
                            leftCandidateUsed = true;
                        } else {
                            leftCandidate.count = prevNode.count;
                            leftCandidate.marked = false;
                            leftCandidateUsed = false;
                        }
                    }

                    if (aboveCandidate.count > leftCandidate.count) {
                        currMatrix[j] = aboveCandidate;
                        used = aboveCandidateUsed;
                    } else if (aboveCandidate.count < leftCandidate.count) {
                        currMatrix[j] = leftCandidate;
                        used = leftCandidateUsed;
                    } else if (!leftCandidateUsed && aboveCandidateUsed) {
                        currMatrix[j] = leftCandidate;
                        used = leftCandidateUsed;
                    } else if (leftCandidateUsed && !aboveCandidateUsed) {
                        currMatrix[j] = aboveCandidate;
                        used = aboveCandidateUsed;
                    } else if (!leftCandidate.marked && aboveCandidate.marked) {
                        currMatrix[j] = leftCandidate;
                        used = leftCandidateUsed;
                    } else {
                        currMatrix[j] = aboveCandidate;
                        used = aboveCandidateUsed;
                    }

                    prevNode = currMatrix[j];
                }
            }
            prevMatrix = currMatrix;
            currMatrix = new Node[s2.length()];
        }

        return prevNode.count;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
