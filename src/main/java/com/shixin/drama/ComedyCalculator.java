package com.shixin.drama;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmount() {
        int result = 30000;
        if (this.getPerformance().getAudience() > 20) {
            result += 10000 + 500 * (this.getPerformance().getAudience() - 20);
        }
        result += 300 * this.getPerformance().getAudience();
        return result;
    }

    @Override
    public int getVolumeCredits() {
        return super.getVolumeCredits() + this.getPerformance().getAudience() / 10;
    }
}
