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
    
    Transaction(CurrencyAmount amt, LocalDateTime time, String desc) {
        this.amount = amt;
        this.dateTime = time;
        this.description = desc;
    }

}
