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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        Deposit other = (Deposit) obj;
        if (!this.amount.equals(other.amount)) {
            return false;
        }
        return this.dateTime.equals(other.dateTime);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    public Deposit(CurrencyAmount amt, LocalDateTime time) {
        super(amt, time, "Deposit");
        if (amt.getAmountInCents() < 1L) {
            String excMsg = "Deposit amount must not be " + amt.toString();
            throw new IllegalArgumentException(excMsg);
        }
    }
    
}
