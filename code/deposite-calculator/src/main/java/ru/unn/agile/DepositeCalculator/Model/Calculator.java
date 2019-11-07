package ru.unn.agile.DepositeCalculator.Model;

public class Calculator {

    private final int MAX_PERCENT = 100;
    private final int MONTHS_IN_YEAR = 12;
    private final int DAYS_IN_YEAR = 365;

    private double mStartSum;
    private double mPercent;
    private double mPeriodCoeff;
    private int mCapitalizationCount;

    public Calculator setStartSum(double startSum) {
        mStartSum = startSum;
        return this;
    }

    public Calculator setPercent(int percent) {
        mPercent = (double) percent / MAX_PERCENT;
        return this;
    }

    public Calculator setPeriod(DepositeTimeType periodType, int count) {
        switch (periodType) {
            case YEAR:
                mPeriodCoeff = count;
                break;
            case MONTH:
                mPeriodCoeff = (double)count / MONTHS_IN_YEAR;
                break;
            case DAY:
                mPeriodCoeff = (double)count / DAYS_IN_YEAR;
                break;
        }
        return this;
    }

    public Calculator setCapitalizationPeriod(DepositeTimeType capitalizationPeriod) {
        switch (capitalizationPeriod) {
            case YEAR:
                mCapitalizationCount = 1;
                break;
            case MONTH:
                mCapitalizationCount = MONTHS_IN_YEAR;
                break;
            case DAY:
                mCapitalizationCount = DAYS_IN_YEAR;
                break;
        }
        return this;
    }

    public double calculate() {

        if (mCapitalizationCount == 0) {
            return mStartSum + mStartSum * mPercent * mPeriodCoeff;
        } else {
            double effectiveInterestRate = Math.pow(1 + mPercent/mCapitalizationCount, mCapitalizationCount) - 1;
            return mStartSum + mStartSum * effectiveInterestRate * mPeriodCoeff;
        }
    }
}