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

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Account class.
 * @author Alonso del Arte
 */
public class AccountTest {
    
    static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    static final CurrencyAmount DEFAULT_INITIAL_DEPOSIT_AMOUNT 
            = new CurrencyAmount(524288, DOLLARS);
    
    public static final Deposit DEFAULT_INITIAL_DEPOSIT 
            = new Deposit(DEFAULT_INITIAL_DEPOSIT_AMOUNT, LocalDateTime.now());
    
    static final Random RANDOM = new Random();
    
    // TODO: Write tests
    
}
