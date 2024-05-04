package com.shixin.drama;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoicePrinterTest {
    private InvoicePrinter printer;

    @BeforeEach
    public void setUp() {
        printer = new InvoicePrinter();
    }

    @Test
    public void should_print_correct_statement() {
        List<Performance> performances = List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)
        );
        Invoice invoice = new Invoice("BigCo", performances);
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("Hamlet", "tragedy"));
        plays.put("as-like", new Play("As You Like It", "comedy"));
        plays.put("othello", new Play("Othello", "tragedy"));

        String actual = printer.statement(invoice, plays);
        String expected = "Statement for BigCo\n"
                + "\tHamlet: $650.00 (55 seats)\n"
                + "\tAs You Like It: $580.00 (35 seats)\n"
                + "\tOthello: $500.00 (40 seats)\n"
                + "Amount owed is $1,730.00\n"
                + "You earned 43 credits\n";
        assertEquals(expected, actual);
    }
}
