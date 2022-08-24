package osl_bankproblem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {

    private String userName;
    private String currency;
    private double balance;
    ArrayList<Object> transaction;  // every client has a list of their transactions 

    public Account() {
        userName = "";
        currency = "";
        balance = 0;
        transaction = new ArrayList<>();
    }

    public Account(String name, String curr) {
        userName = name;
        currency = curr;
        transaction = new ArrayList<>();
        ArrayList<String> currList = new ArrayList<>();
//        the system only support some currency, so reject the others

        while (Bank.supportCurrency.contains(curr) != true) {
//            if the currency chosen by user is none of HKD/USD/SGD
//            require user to enter again
        }
        if (Bank.clientCurr.containsKey(name)) {    // if is existing client // else if is new client, no extra action
            Bank.clientCurr.get(name);
            currList.add(curr);
        }
        currList.add(curr);
        Bank.clientCurr.put(name, currList);

    }

    public boolean actionSelect(int s) {
        double amount;
        Scanner sc = new Scanner(System.in);
        switch (s) {
            case 1 -> {
                System.out.println("Enter the amount of deposit:");
                amount = sc.nextDouble();
                this.moneyDeposit(amount);
            }
            case 2 -> {
                System.out.println("Enter the amount of withdrawal:");
                amount = sc.nextDouble();
                moneyWithdrawal(amount);
            }
            case 3 -> {
                String target;
                System.out.println("Enter the target of transfer:");
                target = sc.nextLine();
                System.out.println("Enter the amount of withdrawal:");
                amount = sc.nextDouble();
                moneyTransfer(amount, target);
            }
            case 4 -> {
                listBalance();
            }
            case 5 -> {
                displayTransaction();
            }
            default -> {
                return false;   // no action, return to enter user name
            }
        }
        sc.nextLine();
        return true;
    }

    private void moneyDeposit(double amount) {
        this.balance += amount;
        LocalDateTime now = LocalDateTime.now();
        transaction.add(new Transaction(now, this.currency, "Deposit", amount));
    }

    private boolean moneyWithdrawal(double amount) {
// retrieve the 5th withdrawal record backwards        
// exception if the time is within 5 minutes
        double fee;
        fee = amount * 0.01;
        boolean validBalance = (balance > amount + fee);
        if (validBalance) {// cannot withdraw/transfer money given insufficient funds
            balance -= (amount + fee);
            switch (this.currency) {    // fee transfer to respective OSL account depends on currency using
                case "HKD" -> {
                }
                case "USD" -> {
                }
                case "SGD" -> {
                }
                default -> { // exception
                }
            }
            LocalDateTime now = LocalDateTime.now();
            transaction.add(new Transaction(now, this.currency, "Withdrawal", amount * -1));
            transaction.add(new Transaction(now, this.currency, "Withdrawal FEE", fee * -1));
            
            
//            1% to OSL default account
//             withdrawal physically, omitted
            return true;
        }
        System.out.println("failed");
        return false; // withdrawal fail
    }

    private boolean moneyTransfer(double amount, String target) {
        double fee;
        fee = amount * 0.01;
        boolean validBalance = (balance > amount + fee);// cannot withdraw/transfer money given insufficient funds
        boolean validCurrency = (this.balance == this.balance); // Money transfer cannot happen between different currencies
        if (validBalance && validCurrency) {
            balance -= (amount + fee);
            //target_balance += amount;
            switch (this.currency) {    // fee transfer to respective OSL account depends on currency using
                case "HKD" -> {
                }
                case "USD" -> {
                }
                case "SGD" -> {
                }
                default -> { // error message
                }
            }
            LocalDateTime now = LocalDateTime.now();
            transaction.add(new Transaction(now, this.currency, "Transfer OUT", amount * -1));
            transaction.add(new Transaction(now, this.currency, "Transfer FEE", fee * -1));

            //target_transaction.add(new Transaction(now, this.currency, "Transfer IN", amount * -1));
            return true;
        }
        System.out.println("failed");
        return false; // withdrawal fail
    }

    private double listBalance() {
        // show balance for all currencies the client has
        int temp = Bank.clientCurr.get(this.userName).size();
        for (int i = 0; i < temp; i++) {
            System.out.println("Your current balance is " + this.balance + Bank.clientCurr.get(this.userName).get(i));
        }
        return this.balance;
    }

    private void displayTransaction() {
        System.out.printf("Client Name%2s\t\t\n", userName);
        System.out.printf("Date%30s", "Currency\t");
        System.out.printf("Operation%20s", "Amount\t\n");
        for (int i = 0; i < transaction.size(); i++) {
            System.out.println(transaction.get(i));
        }
    }

}
