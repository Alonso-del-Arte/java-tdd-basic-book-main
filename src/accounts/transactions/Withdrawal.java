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
public class Withdrawal extends Transaction {
    
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
        return "Withdrawal";
    }
    
    @Override
    public String toString() {
        return "Withdrawal of " + this.amount.negate().toString() + " on " 
                + this.dateTime.toString();
    }
    
    public Withdrawal(CurrencyAmount amt, LocalDateTime time) {
        super(amt, time, "Withdrawal");
        if (amt.getAmountInCents() > -1L) {
            String excMsg = "Withdrawal amount must not be " + amt.toString();
            throw new IllegalArgumentException(excMsg);
        }
    }
    
}
