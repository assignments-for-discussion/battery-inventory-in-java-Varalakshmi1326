import java.util.ArrayList;
import java.util.List;

public class BatteryClassification {

    public static void main(String[] args) {
      
        double[] presentCapacities = {120, 100, 85, 70, 60, 50, 110, 90};

        
        BatteryClassificationResult Result = classifyBatteries(presentCapacities);

  
        System.out.println("Healthy Batteries: " + Result.getHealthyCount());
        System.out.println("Exchangeable Batteries: " + Result.getExchangeCount());
        System.out.println("Failed Batteries: " + Result.getFailedCount());

       
        runTests();
    }

    
    public static BatteryClassificationResult classifyBatteries(double[] presentCapacities) {
        int HealthyCount = 0;
        int ExchangeCount = 0;
        int FailedCount = 0;
        final double ratedCapacity = 120.0; 

        for (double presentCapacity : presentCapacities) {
            double soh = calculateSoH(presentCapacity, ratedCapacity);

            if (soh > 80.0) {
                HealthyCount++;
            } else if (soh >= 62.0) {
                ExchangeCount++;
            } else {
                FailedCount++;
            }
        }

        return new BatteryClassificationResult(HealthyCount, ExchangeCount, FailedCount);
    }

    
    public static double calculateSoH(double presentCapacity, double ratedCapacity) {
        if (ratedCapacity <= 0) {
            throw new IllegalArgumentException("Rated capacity must be greater than zero.");
        }
        return 100 * (presentCapacity / ratedCapacity);
    }

    
    public static void runTests() {
        double[] testCapacities1 = {120, 100, 80, 62, 50}; // Expected: 3 healthy, 1 exchange, 1 failed
        BatteryClassificationResult result1 = classifyBatteries(testCapacities1);
        assert result1.getHealthyCount() == 3 : "Test 1 Failed: Expected 3 healthy batteries.";
        assert result1.getExchangeCount() == 1 : "Test 1 Failed: Expected 1 exchangeable battery.";
        assert result1.getFailedCount() == 1 : "Test 1 Failed: Expected 1 failed battery.";

        double[] testCapacities2 = {110, 85, 63, 61, 40}; // Expected: 3 healthy, 1 exchange, 1 failed
        BatteryClassificationResult result2 = classifyBatteries(testCapacities2);
        assert result2.getHealthyCount() == 3 : "Test 2 Failed: Expected 3 healthy batteries.";
        assert result2.getExchangeCount() == 1 : "Test 2 Failed: Expected 1 exchangeable battery.";
        assert result2.getFailedCount() == 1 : "Test 2 Failed: Expected 1 failed battery.";

        System.out.println("All tests passed!");
    }
}


class BatteryClassificationResult {
    private final int healthyCount;
    private final int exchangeCount;
    private final int failedCount;

    public BatteryClassificationResult(int healthyCount, int exchangeCount, int failedCount) {
        this.healthyCount = healthyCount;
        this.exchangeCount = exchangeCount;
        this.failedCount = failedCount;
    }

    public int getHealthyCount() {
        return healthyCount;
    }

    public int getExchangeCount() {
        return exchangeCount;
    }

    public int getFailedCount() {
        return failedCount;
    }
}
