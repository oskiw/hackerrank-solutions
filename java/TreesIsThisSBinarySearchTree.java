package com.example.happypumpkin;

import java.util.*;

class Solution {

    class Node {
        int data;
        Node left;
        Node right;

        public Node(int d, Node l, Node r) {
            data = d;
            left = l;
            right = r;
        }
    }

    boolean checkBstMax(Node current, int max, Set<Integer> dataSet) {
        if (current == null) return true;

        int data = current.data;
        if (dataSet.contains(data)) return false;

        dataSet.add(data);

        if (data >= max) return false;

        return checkBstMax(current.left, data, dataSet) && checkBstMin(current.right, data, dataSet);
    }

    boolean checkBstMin(Node current, int min, Set<Integer> dataSet) {
        if (current == null) return true;

        int data = current.data;
        if (dataSet.contains(data)) return false;

        dataSet.add(data);

        if (data <= min) return false;

        return checkBstMax(current.left, data, dataSet) && checkBstMin(current.right, data, dataSet);
    }

    boolean checkBST(Node root) {
        Set<Integer> dataSet = new HashSet<>();
        int data = root.data;
        dataSet.add(data);
        return checkBstMax(root.left, data, dataSet) && checkBstMin(root.right, data, dataSet);
    }

    public static void main(String []arg) {
        Solution s = new Solution();
        Node p1 = s.new Node(1, null, null);
        Node p3 = s.new Node(3, null, null);
        Node p2 = s.new Node(2, p1, p3);
        Node p5 = s.new Node(5, null, null);
        Node p7 = s.new Node(7, null, null);
        Node p6 = s.new Node(6, p5, p7);
        Node p4 = s.new Node(4, p2, p6);

        System.out.println(s.checkBST(p4));
    }
}