package com.shixin.drama;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class InvoicePrinter {
    public String statement(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(StatementData.create(invoice, plays));
    }

    private String renderPlainText(StatementData data) {
        String result = String.format("Statement for %s\n", data.getCustomer());
        for (Performance perf : data.getPerformances()) {
            result += String.format("\t%s: %s (%s seats)\n", perf.getPlay().getName(),
                    usd(perf.getAmount()), perf.getAudience());
        }

        result += String.format("Amount owed is %s\n", usd(data.getTotalAmount()));
        result += String.format("You earned %s credits\n", data.getTotalVolumeCredits());
        return result;
    }

    public String htmlStatement(Invoice invoice, Map<String, Play> plays) {
        return renderHtml(StatementData.create(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        String result = String.format("<h1>Statement for %s</h1>\n", data.getCustomer());
        result += "<table>\n";
        result += "<tr><th>play</th><th>seats</th><th>cost</th></tr>";
        for (Performance perf : data.getPerformances()) {
            result += String.format("<tr><td>%s</td><td>%s</td>", perf.getPlay().getName(), perf.getAudience());
            result += String.format("<td>%s</td></tr>\n", usd(perf.getAmount()));
        }
        result += "</table>\n";
        result += String.format("<p>Amount owed is <em>%s</em></p>\n", usd(data.getTotalAmount()));
        result += String.format("<p>You earned <em>%s</em> credits</p>\n", data.getTotalVolumeCredits());
        return result;
    }

    private String usd(double number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number / 100.0);
    }
}
