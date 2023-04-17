/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

/**
 * Represents a transaction in a bank account, such as a deposit or a 
 * withdrawal. Instances hold how much the transaction was or will be for, what 
 * date it took place on or is scheduled for, and a description of the 
 * transaction.
 * @author Alonso del Arte
 */
public abstract class Transaction {
    
    final CurrencyAmount amount;
    
    final LocalDateTime dateTime;
    
    final String description;
    
    public CurrencyAmount getAmount() {
        return this.amount;
    }
    
    public LocalDateTime getTime() {
        return LocalDateTime.MIN;
    }
    
    public String getDescription() {
        return "SORRY, NOT IMPLEMENTED YET";
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
        Transaction other = (Transaction) obj;
        if (!this.amount.equals(other.amount)) {
            return false;
        }
        return this.dateTime.equals(other.dateTime);
    }
    
    @Override
    public int hashCode() {
        int hash = this.amount.hashCode() << 16;
        hash += this.dateTime.hashCode();
        return hash + this.getClass().getSimpleName().hashCode();
    }
    
    Transaction(CurrencyAmount amt, LocalDateTime time, String desc) {
        this.amount = amt;
        this.dateTime = time;
        this.description = desc;
    }

}
