import java.util.*;

public class AbaspaBasapa {

    public static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        String second = scanner.nextLine();

        dp = new int[first.length()][second.length()];

        int bestLength = -1;
        int bestRow = -1;
        int bestCol = -1;

        for (int rowIndex = 0; rowIndex < first.length(); rowIndex++) {
            for (int colIndex = 0; colIndex < second.length(); colIndex++) {
                if (first.charAt(rowIndex) == second.charAt(colIndex)) {
                    dp[rowIndex][colIndex] = getPrevBest(rowIndex, colIndex) + 1;
                }

                if (dp[rowIndex][colIndex] > bestLength) {
                    bestLength = dp[rowIndex][colIndex];
                    bestRow = rowIndex;
                    bestCol = colIndex;
                }
            }
        }

        List<Character> result = new ArrayList<>();

        while (bestRow >= 0 && bestCol >= 0 && dp[bestRow][bestCol] != 0) {
            result.add(first.charAt(bestRow));
            bestRow--;
            bestCol--;

        }
        Collections.reverse(result);
        for (Character character : result) {
            System.out.print(character);
        }

    }

    private static int getPrevBest(int rowIndex, int colIndex) {
        if (rowIndex - 1 < 0 || colIndex - 1 < 0) {
            return 0;
        }
        return dp[rowIndex - 1][colIndex - 1];
    }
}
