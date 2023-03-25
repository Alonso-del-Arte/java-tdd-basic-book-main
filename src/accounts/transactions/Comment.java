/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.Currency;

/**
 *
 * @author Alonso del Arte
 */
public class Comment extends Transaction {
    
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
        return this.description;
    }
    
    @Override
    public String toString() {
        return this.description;
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
        Comment other = (Comment) obj;
        return this.amount.getCurrency().equals(other.amount.getCurrency());
//        if (this.amount.getCurrency() != other.amount.getCurrency()) {
//            return false;
//        }
//        if (!this.dateTime.equals(other.dateTime)) {
//            return false;
//        }
//        return this.description.equals(other.description);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    public Comment(Currency currency, LocalDateTime time, String desc) {
        super(new CurrencyAmount(0, currency), time, "Comment: " + desc);
    }
    
}
