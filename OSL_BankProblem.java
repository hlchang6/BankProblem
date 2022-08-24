package osl_bankproblem;

import com.sun.source.tree.ContinueTree;
import java.util.Scanner;

/**
 *
 * @author Hilary
 */
public class OSL_BankProblem {

    static String name;

    public static void main(String[] args) {
        String uname;
        String curr;
        Scanner sc = new Scanner(System.in);

        new Bank();
        System.out.println("This banks supports the following currency: " + Bank.supportCurrency);       // only support the 3 currencies

        Account peter = new Account("a", "HKD"); // testing account

        System.out.println("enter username for new account:");            // account creation
        uname = sc.nextLine();
        System.out.println("enter currency for new account: e.g. HKD");
        curr = sc.nextLine();
        new Account(uname, curr);
       
        // system runs forever
        while (true) {
            System.out.println("enter username: (case-sensitive)");
            name = sc.nextLine();
            Object o = name;

            while (Bank.clientCurr.containsKey(o) != true) {
                System.out.println("Username " + name + " does not exist");
                System.out.println("Please try again:");
                name = sc.nextLine();
                o = name;
                if (Bank.clientCurr.containsKey(o) == true) {
                    //        check if user exist
                    //        check if pw matched, omitted
                    break;
                }
            }

            System.out.println("select action by entering a number:");
            System.out.println("""
                           1: moneyDeposit
                           2: moneyWithdrawal
                           3: moneyTransfer
                           4: listBalance
                           5: displayTransaction
                           Enter any other number to return
                           """);
            int action;
            action = sc.nextInt();
            if (peter.actionSelect(action) == false) {
                sc.nextLine();
            }
        }
    }

}
