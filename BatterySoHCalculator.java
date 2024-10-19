import java.util.Scanner;

public class BatterySoHCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the rated capacity of the battery (in Ah): ");
        double ratedCapacity = scanner.nextDouble();

        
        System.out.print("Enter the present capacity of the battery (in Ah): ");
        double presentCapacity = scanner.nextDouble();

        
        double stateOfHealth = calculateSoH(presentCapacity, ratedCapacity);

        
        System.out.printf("The State of Health (SoH) of the battery is: %.2f%%\n", stateOfHealth);

        
        scanner.close();
    }

   
    public static double calculateSoH(double presentCapacity, double ratedCapacity) {
        if (ratedCapacity <= 0) {
            throw new IllegalArgumentException("Rated capacity must be greater than zero.");
        }
        return 100 * (presentCapacity / ratedCapacity);
    }
}
