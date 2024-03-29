/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

/**
 *
 * @author Alonso del Arte
 */
public class Deposit extends Transaction {
    
    @Override
    public CurrencyAmount getAmount() {
        return this.amount;
    }
    
    @Override
    public LocalDateTime getTime() {
        return this.dateTime;
    }
    
    @Override
    public String getDescription() {
        return "Deposit";
    }
    
    @Override
    public String toString() {
        return "Deposit for " + this.amount.toString() + " on " 
                + this.dateTime.toString();
    }
    
    public Deposit(CurrencyAmount amt, LocalDateTime time) {
        super(amt, time, "Deposit");
        if (amt.getAmountInCents() < 1L) {
            String excMsg = "Deposit amount must not be " + amt.toString();
            throw new IllegalArgumentException(excMsg);
        }
    }
    
}
