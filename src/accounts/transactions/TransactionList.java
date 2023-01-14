/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.util.ArrayList;
import java.util.Currency;

/**
 * This is to illustrate what we'd have to do if Java didn't have generics. But 
 * since Java does have generics, and the collections framework, we can just use 
 * <code>java.util.ArrayList&lt;Transaction&gt;</code> or something like that.
 * @author Alonso del Arte
 */
public class TransactionList extends ArrayList<Transaction> {
    
    static final int DEFAULT_INITIAL_CAPACITY = 10;
    
    private final Currency currencyID;
    
    private CurrencyAmount runningTotal;
    
    public CurrencyAmount getBalance() {
        return this.runningTotal;
    }
    
    @Override
    public boolean add(Transaction transaction) {
        if (transaction.getAmount().getCurrency().equals(this.currencyID)) {
            this.runningTotal = this.runningTotal.plus(transaction.amount);
            return super.add(transaction);
        } else {
            return false;
        }
    }
    
    /**
     * Tells how many transactions this list contains.
     * @return Zero if the list is empty, a positive number otherwise. For 
     * example, if the list has forty-seven transactions, this function would 
     * return 47.
     * @deprecated Use <code>size()</code> instead.
     */
    @Deprecated
    public int getTransactionCount() {
        return this.size();
    }
    
    public TransactionList(Currency currency) {
        super(DEFAULT_INITIAL_CAPACITY);
        this.currencyID = currency;
        this.runningTotal = new CurrencyAmount(0, this.currencyID);
    }
    
}
