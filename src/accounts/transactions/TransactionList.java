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
    
    private int trxCount = 0;
    
    public CurrencyAmount getBalance() {
        return this.runningTotal;
    }
    
    private void expandCapacity() {
        int oldCapacity = this.elements.length;
        int expandedCapacity = 3 * oldCapacity / 2;
        Transaction[] replacementArray = new Transaction[expandedCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            replacementArray[i] = this.elements[i];
        }
        this.elements = replacementArray;
    }
    
    public boolean add(Transaction transaction) {
        if (transaction.getAmount().getCurrency().equals(this.currencyID)) {
            if (this.trxCount == this.elements.length) {
                this.expandCapacity();
            }
            this.runningTotal = this.runningTotal.plus(transaction.amount);
            this.elements[this.trxCount] = transaction;
            this.trxCount++;
            return true;
        } else {
            return false;
        }
    }
    
    public int getTransactionCount() {
        return this.trxCount;
    }
    
    public Transaction get(int index) {
        return this.elements[index];
    }
    
    public TransactionList(Currency currency) {
        this.currencyID = currency;
        this.runningTotal = new CurrencyAmount(0, this.currencyID);
        this.elements = new Transaction[DEFAULT_INITIAL_CAPACITY];
    }
    
}
