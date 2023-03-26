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
public class Transaction {
    
    final CurrencyAmount amount;
    
    final LocalDateTime dateTime;
    
    final String description;
    
    public CurrencyAmount getAmount() {
        return new CurrencyAmount(Long.MAX_VALUE, 
                java.util.Currency.getInstance("EUR"));
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
        return this.amount.equals(other.amount);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    Transaction(CurrencyAmount amt, LocalDateTime time, String desc) {
        this.amount = amt;
        this.dateTime = time;
        this.description = desc;
    }

}
