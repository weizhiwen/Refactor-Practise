package com.shixin.drama;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatementData {
    private String customer;

    private List<Performance> performances;

    private int totalAmount;

    private int totalVolumeCredits;

    public static StatementData create(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementData();
        statementData.setCustomer(invoice.getCustomer());
        statementData.setPerformances(invoice.getPerformances().stream()
                .map(perf -> enrichPerformance(plays, perf)).collect(Collectors.toList()));
        statementData.setTotalAmount(totalAmount(statementData));
        statementData.setTotalVolumeCredits(totalVolumeCredits(statementData));
        return statementData;
    }

    private static int totalAmount(StatementData data) {
        return data.getPerformances().stream().mapToInt(Performance::getAmount).sum();
    }

    private static int totalVolumeCredits(StatementData data) {
        return data.getPerformances().stream().mapToInt(Performance::getVolumeCredits).sum();
    }

    private static Performance enrichPerformance(Map<String, Play> plays, Performance aPerformance) {
        PerformanceCalculator calculator = createPerformanceCalculator(aPerformance, playFor(plays, aPerformance));
        Performance result = new Performance(aPerformance.getPlayId(), aPerformance.getAudience());
        result.setPlay(calculator.getPlay());
        result.setAmount(calculator.getAmount());
        result.setVolumeCredits(calculator.getVolumeCredits());
        return result;
    }

    private static PerformanceCalculator createPerformanceCalculator(Performance aPerformance, Play aPlay) {
        return switch (aPlay.getType()) {
            case "tragedy" -> new TragedyCalculator(aPerformance, aPlay);
            case "comedy" -> new ComedyCalculator(aPerformance, aPlay);
            default -> throw new IllegalArgumentException("unknown type: " + aPlay.getType());
        };
    }

    private static Play playFor(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.getPlayId());
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalVolumeCredits(int totalVolumeCredits) {
        this.totalVolumeCredits = totalVolumeCredits;
    }

    public int getTotalVolumeCredits() {
        return totalVolumeCredits;
    }
}
