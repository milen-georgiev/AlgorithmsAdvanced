import java.util.*;
import java.util.stream.Collectors;

public class Time {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] first = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] second = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] dp = new int[first.length + 1][second.length + 1];

        for (int r = 1; r <= first.length; r++) {
            for (int c = 1; c <= second.length; c++) {
                if (first[r - 1] == second[c - 1]) {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                } else {
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
                }
            }
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int r = first.length;
        int c = second.length;

        while (r != 0 && c != 0) {
            if (first[r - 1] == second[c - 1]) {
                deque.push(first[r - 1]);
                r--;
                c--;
            } else if (dp[r - 1][c] > dp[r][c - 1]) {
                r--;
            } else {
                c--;
            }
        }

        String LCS = deque.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(LCS);
        System.out.println(dp[first.length][second.length]);
    }
}
