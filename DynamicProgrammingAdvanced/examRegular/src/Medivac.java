import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Medivac {
    public static class Item {
        int unit;
        int capacityUnit;
        int urgencyRating;

        public Item(int unit, int capacityUnit, int urgencyRating) {
            this.unit = unit;
            this.capacityUnit = capacityUnit;
            this.urgencyRating = urgencyRating;
        }

        public int getUnit() {
            return unit;
        }

        public void setUnit(int unit) {
            this.unit = unit;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(reader.readLine());
        String input = reader.readLine();

        List<Item> items = new ArrayList<>();

        while (!input.equals("Launch")) {
            String[] tokens = input.split("\\s+");

            items.add(new Item(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])));

            input = reader.readLine();
        }

        int[][] dp = new int[items.size() + 1][capacity + 1];
        boolean[][] takenItems = new boolean[items.size() + 1][capacity + 1];

        for (int itemRow = 1; itemRow <= items.size(); itemRow++) {
            Item item = items.get(itemRow - 1);


            for (int capacityCol = 0; capacityCol <= capacity; capacityCol++) {

                int excluded = dp[itemRow - 1][capacityCol];

                if (capacityCol - item.capacityUnit < 0) {
                    dp[itemRow][capacityCol] = excluded;
                } else {
                    int included = dp[itemRow - 1][capacityCol - item.capacityUnit] + item.urgencyRating;

                    if (excluded > included) {
                        dp[itemRow][capacityCol] = excluded;
                    } else {
                        dp[itemRow][capacityCol] = included;
                        takenItems[itemRow][capacityCol] = true;
                    }
                }
            }
        }
        int weight = capacity;

        int bestValue = dp[items.size()][capacity];

        while (dp[items.size()][weight - 1] == bestValue) {
            weight--;
        }

        System.out.println(weight);
        System.out.println(dp[items.size()][capacity]);

        List<Item> result = new ArrayList<>();
        int last = items.size();
        while (last > 0) {
            if (takenItems[last][capacity]) {
                Item item = items.get(last - 1);
                result.add(item);
                capacity -= item.capacityUnit;
            }
            last--;
        }

        result.stream().sorted(Comparator.comparingInt(Item::getUnit)).forEach(n -> System.out.println(n.getUnit()));

    }
}
