package ru.unn.agile.StatisticsCalculation.model;

public final class StatisticsCalculation {
    public static Double calculateExpectedValue(final Number[] values,
                                                final Double[] probabilities) {
        DistributionChecker.validate(values, probabilities);
        Double result = 0.0;
        for (int i = 0; i < values.length; i++) {
            result += values[i].doubleValue() * probabilities[i];
        }
        return result;
    }

    public static Double calculateDispersion(final Number[] values,
                                             final Double[] probabilities) {
        Double result = 0.0;
        Double expectedValue = calculateExpectedValue(values, probabilities);
        for (int i = 0; i < values.length; i++) {
            result += probabilities[i] * Math.pow((values[i].doubleValue() - expectedValue), 2);
        }
        return result;
    }

    public static Double calculateRawMoment(final Number[] values,
                                             final Double[] probabilities, final Integer order) {
        if (order == 2) {
            Double result = 0.0;
            for (int i = 0; i < values.length; i++) {
                result += probabilities[i] * Math.pow((values[i].doubleValue()), 2);
            }
            return result;
        }
        else if (order == 3) {
            Double result = 0.0;
            for (int i = 0; i < values.length; i++) {
                result += probabilities[i] * Math.pow((values[i].doubleValue()), 3);
            }
            return result;
        }
        return calculateExpectedValue(values, probabilities);
    }

    private StatisticsCalculation() { }
}
