import java.util.Scanner;

public class IterativeKanpsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int capacity = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();

        while (!line.equals("end")) {
            String[] tokens = line.split("\\s+");
//            weights.add(Integer.parseInt(tokens[1]));
//            prices.add(Integer.parseInt(tokens[2]));

            line = scanner.nextLine();
        }
    }
}
