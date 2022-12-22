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
    
    public Deposit(CurrencyAmount amt, LocalDateTime time) {
        super(amt, time, "Deposit");
    }
    
}
