package osl_bankproblem;


import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    public static ArrayList<String> supportCurrency;
    ArrayList<String> curr;  // list 
    static HashMap<String, ArrayList> clientCurr; //map client names to currencies they use

    public Bank() {
        supportCurrency = new ArrayList<>();
        supportCurrency.add("HKD");
        supportCurrency.add("USD");
        supportCurrency.add("SGD");
        
        curr = new ArrayList<>();
        clientCurr = new HashMap<>();
        
        //        for every currency in array list, create an OSL account for that currency
        new Account("OSL_FEE", "HKD");
        new Account("OSL_FEE", "USD");
        new Account("OSL_FEE", "SGD");   
    }

}
