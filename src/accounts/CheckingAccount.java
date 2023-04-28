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
 *
 * @author Alonso del Arte
 */
public class CheckingAccount extends Account {
    
    // TODO: Write tests for this
    public boolean hasAssociatedSavingsAccount() {
        return false;
    }
    
    public void associate(SavingsAccount savings) {
        // TODO: Write tests for this
    }
    
    // TODO: Write tests for this
    public SavingsAccount getAssociatedSavings() {
        Deposit deposit = new Deposit(new CurrencyAmount(1, 
                java.util.Currency.getInstance(java.util.Locale.CANADA)), 
                java.time.LocalDateTime.now());
        return new SavingsAccount(null, deposit);
    }
    
    @Override
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
    
    @Override
    public CurrencyAmount getBalance() {
        return this.balance;
    }

    public CheckingAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, initialDeposit);
    }
    
    public CheckingAccount(Entity primary, Entity secondary, 
            Deposit initialDeposit) {
        super(primary, secondary, initialDeposit);
    }
    
}
