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

import java.util.Currency;

/**
 * Represents a checking account. A savings account can be connected to a 
 * checking account to provide overdraft transfers.
 * @author Alonso del Arte
 */
public class CheckingAccount extends Account {
    
    private SavingsAccount associatedSavings = null;
    
    /**
     * Tells whether or not this checking account has an associated savings 
     * account. If it does, the associated savings account might be able to 
     * cover deficits incurred in checking account transactions. A savings 
     * account is associated by calling {@link 
     * #associate(accounts.SavingsAccount) associate()}.
     * @return True if this checking account has an associated savings account, 
     * false otherwise.
     */
    public boolean hasAssociatedSavingsAccount() {
        return this.associatedSavings != null;
    }
    
    /**
     * Associates this checking account to a savings account, or severs a 
     * previously made association. Whether or not this checking account has an 
     * associated savings account can be queried by calling {@link 
     * #hasAssociatedSavingsAccount()}.
     * @param savings The savings account to associate this checking account 
     * with. May be null in order to disassociate this checking account from any 
     * savings account.
     */
    public void associate(SavingsAccount savings) {
        this.associatedSavings = savings;
    }
    
    /**
     * Getter for the associated savings account. A savings account is 
     * associated by calling {@link #associate(accounts.SavingsAccount) 
     * associate()}. Whether or not there's an associated savings account can be 
     * queried by calling {@link #hasAssociatedSavingsAccount()}.
     * @return The associated savings account. Null if there's no currently 
     * associated savings account.
     */
    public SavingsAccount getAssociatedSavings() {
        return this.associatedSavings;
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
    
    @Override
    public boolean hasSufficientBalance(Withdrawal withdrawal) {
        CurrencyAmount projectedBalance = this.balance
                .plus(withdrawal.getAmount());
        return projectedBalance.getAmountInCents() >= 0;
    }

    public CheckingAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, initialDeposit);
    }
    
    public CheckingAccount(Entity primary, Entity secondary, 
            Deposit initialDeposit) {
        super(primary, secondary, initialDeposit);
    }
    
}
