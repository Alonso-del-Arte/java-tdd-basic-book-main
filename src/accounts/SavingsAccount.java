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
 * Represents a savings account. A savings account can be connected to a 
 * checking account to provide overdraft transfers.
 * @author Alonso del Arte
 */
public class SavingsAccount extends Account {
    
    public SavingsAccount(Entity primary, Deposit initialDeposit) {
        this(primary, null, initialDeposit);
    }
    
    public SavingsAccount(Entity primary, Entity secondary, 
            Deposit initialDeposit) {
        super(primary, secondary, initialDeposit);
    }
    
}
