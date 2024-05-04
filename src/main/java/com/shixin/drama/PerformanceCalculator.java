package com.shixin.drama;

public abstract class PerformanceCalculator {
    private Performance performance;

    private Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public abstract int getAmount();

    public int getVolumeCredits() {
        return Math.max(this.getPerformance().getAudience() - 30, 0);
    }
}
