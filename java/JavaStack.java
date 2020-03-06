import java.util.*;

class JavaStack {

    private static boolean check(String input) {
        Stack<Character> stack = new Stack<>();
        for (Character c : input.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.empty() || stack.pop() != '{') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.empty() || stack.pop() != '[') {
                    return false;
                }
            } else {
                return false;
            }
        }

        return stack.empty();
    }

    public static void main(String []arg) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            System.out.println(check(input));
        }
    }

}



