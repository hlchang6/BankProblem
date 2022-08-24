package osl_bankproblem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    LocalDateTime date;
    String currency;
    String operation;
    double amount;

    public Transaction(LocalDateTime date, String currency, String operation, double amount) {
        this.date = date;
        this.currency = currency;
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        String formatDateTime = date.format(format);
        String s = String.format("%-30s%s\t%-20s%+.2f", formatDateTime,currency,operation,amount);
        return s;
    }

}
