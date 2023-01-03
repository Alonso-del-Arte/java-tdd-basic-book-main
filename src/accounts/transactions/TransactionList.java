/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.util.Currency;

/**
 * This is to illustrate what we'd have to do if Java didn't have generics. But 
 * since Java does have generics, and the collections framework, we can just use 
 * <code>java.util.ArrayList&lt;Transaction&gt;</code> or something like that.
 * @author Alonso del Arte
 */
public class TransactionList {
    
    static final int DEFAULT_INITIAL_CAPACITY = 10;
    
    private final Currency currencyID;
    
    private Transaction[] elements;
    
    private CurrencyAmount runningTotal;
    
    // TODO: Write tests for this
    public CurrencyAmount getBalance() {
        return new CurrencyAmount(Long.MIN_VALUE, Currency.getInstance("EUR"));
    }
    
    // TODO: Write tests for this
    public boolean add(Transaction transaction) {
        return true;
    }
    
    public TransactionList(Currency currency) {
        this.currencyID = currency;
    }
    
}
