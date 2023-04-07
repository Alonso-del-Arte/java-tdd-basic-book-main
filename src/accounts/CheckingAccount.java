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
import entities.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alonso del Arte
 */
public class CheckingAccount extends Account {
    
    // TODO: Write tests for this
    public boolean hasAssociatedSavingsAccount() {
        return true;
    }
    
    public CheckingAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, initialDeposit);
    }
    
    public CheckingAccount(Entity primary, Entity secondary, 
            Deposit initialDeposit) {
        super(primary, secondary, initialDeposit);
    }
    
}
