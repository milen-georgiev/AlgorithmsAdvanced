import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RoadTrip {

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));

        int[] value = Arrays.stream(scanner.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] capacities = Arrays.stream(scanner.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int trenkCapacities = Integer.parseInt(scanner.readLine());

        int[][] dp = new int[value.length + 1][trenkCapacities + 1];

        for (int i = 1; i <= value.length; i++) {
            for (int j = 1; j <= trenkCapacities; j++) {
                if (capacities[i-1] <= j){
                    dp[i][j] = Math.max(value[i-1] + dp[i -1][j -capacities[i-1]],
                            dp[i-1][j]);
                }else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println("maximum value: " + dp[value.length][trenkCapacities]);
    }
}
