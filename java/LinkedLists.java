import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LinkedLists {

    /*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as:
*/
    class Node {
        int data;
        Node next;
    }

    boolean hasCycle(Node head) {
        if (head == null) return false;
        Set<Node> visited = new HashSet<>();
        Node current = head;
        visited.add(current);
        while (current.next != null) {
            current = current.next;
            if (visited.contains(current)) {
                return true;
            }
            visited.add(current);
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        LinkedLists s = new LinkedLists();
        Node b = s.new Node();
        b.data = 2;
        b.next = b;
        Node a = s.new Node();
        a.data = 1;
        a.next = b;
        System.out.println(s.hasCycle(a));
    }
}
