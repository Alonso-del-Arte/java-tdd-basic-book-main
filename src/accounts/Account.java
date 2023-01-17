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
public class Account {
    
    CurrencyAmount balance = new CurrencyAmount(0, 
            java.util.Currency.getInstance(java.util.Locale.ITALY));
    
    private static final List<Transaction> HISTORY = new ArrayList<>();
    
    public Account(Entity primary, Entity secondary, Deposit initialDeposit) {
        //
    }
    
}
