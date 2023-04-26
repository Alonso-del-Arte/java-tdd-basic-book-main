/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import currency.CurrencyConversionNeededException;
import entities.Entity;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Represents a bank account. Instances hold a list of transactions and keep 
 * track of the balance.
 * @author Alonso del Arte
 */
public abstract class Account {
    
    CurrencyAmount balance;
    
    Currency currency;
    
    final List<Transaction> HISTORY = new ArrayList<>();
    
    // TODO: Write tests for this
    public boolean hasSufficientBalance(Withdrawal withdrawal) {
        CurrencyAmount projectedBalance = this.HISTORY.get(0).getAmount()
                .plus(withdrawal.getAmount());
        return projectedBalance.getAmountInCents() >= 0;
    }
    
    public void process(Transaction trx) {
        CurrencyAmount trxAmount = trx.getAmount();
        Currency trxCurrency = trxAmount.getCurrency();
        if (trxCurrency != this.currency) {
            String excMsg = "Transaction should be in " 
                    + this.currency.getDisplayName();
            throw new CurrencyConversionNeededException(excMsg, this.balance, 
                    trxAmount);
        }
        this.HISTORY.add(trx);
        this.balance = this.balance.plus(trx.getAmount());
    }
    
    public CurrencyAmount getBalance() {
        return this.balance;
    }

    public List<Transaction> getHistory() {
        return new ArrayList<>(this.HISTORY);
    }
    
    public Account(Entity primary, Entity secondary, Deposit initialDeposit) {
        this.balance = initialDeposit.getAmount();
        this.currency = this.balance.getCurrency();
        this.HISTORY.add(initialDeposit);
    }
    
}
