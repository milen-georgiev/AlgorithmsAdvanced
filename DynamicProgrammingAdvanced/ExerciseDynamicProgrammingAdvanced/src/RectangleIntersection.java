import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class RectangleIntersection {
    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(scanner.readLine());
        int[][] dp = new int[2001][2001];

        for (int i = 0; i < n; i++) {
            int[] coordinates = Arrays.stream(scanner.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int minX = coordinates[0] + 1000;
            int maxX = coordinates[1] + 1000;
            int minY = coordinates[2] + 1000;
            int maxY = coordinates[3] + 1000;

            for (int x = minX; x < maxX; x++) {
                for (int y = minY; y < maxY; y++) {
                    dp[y][x] += 1;

                }
            }
        }

        int result = 0;

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                if (dp[row][col] > 1){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
