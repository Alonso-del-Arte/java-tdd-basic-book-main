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
    
    public Comment(Currency currency, LocalDateTime time, String desc) {
        super(new CurrencyAmount(0, currency), time, "Comment: " + desc);
    }
    
}
