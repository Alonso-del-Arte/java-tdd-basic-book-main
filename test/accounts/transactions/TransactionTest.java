/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Transaction class. Also includes helper functions to create 
 * fictional transactions for testing purposes.
 * @author Alonso del Arte
 */
public class TransactionTest {
    
    static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    static final Random RANDOM = new Random();
    
    private static final int HOURS_IN_A_MONTH = 720;
    
    public static Deposit makeDeposit() {
        int cents = RANDOM.nextInt(262144) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        int backdate = RANDOM.nextInt(HOURS_IN_A_MONTH);
        LocalDateTime date = LocalDateTime.now().minusHours(backdate);
        return new Deposit(amount, date);
    }
    
    public static Withdrawal makeWithdrawal() {
        int cents = -RANDOM.nextInt(262144) - 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        int backdate = RANDOM.nextInt(HOURS_IN_A_MONTH);
        LocalDateTime date = LocalDateTime.now().minusHours(backdate);
        return new Withdrawal(amount, date);
    }
    
    public static Transaction makeTransaction() {
        if (RANDOM.nextBoolean()) {
            return makeDeposit();
        } else {
            return makeWithdrawal();
        }
    }
    
}
