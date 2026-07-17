public class FinancialForecaster {

    public static double calculateFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return calculateFutureValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 7: Financial Forecaster ---");
        
        double startingCapital = 2500.0; 
        double predictableGrowth = 0.06; 
        int forecastTargetTimeline = 4;   

        double finalForecastResult = calculateFutureValue(startingCapital, predictableGrowth, forecastTargetTimeline);
        
        System.out.printf("Starting Asset Base: $%.2f\n", startingCapital);
        System.out.printf("Projected Compound Multiplier Value after %d Years: $%.2f\n", 
                          forecastTargetTimeline, finalForecastResult);
    }
}