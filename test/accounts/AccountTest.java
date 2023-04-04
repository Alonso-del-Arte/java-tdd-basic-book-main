/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import static accounts.transactions.TransactionTest.makeTransaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import entities.Entity;
import static entities.ExampleEntities.EXAMPLE_CUSTOMER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Account class. Also contains some useful constants to use in 
 * this and several other test classes.
 * @author Alonso del Arte
 */
public class AccountTest {
    
    /**
     * United States dollars (USD), the official currency of the United States. 
     * Also the currency of the vast majority of the fictional accounts we will 
     * be using for test purposes.
     */
    public static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    /**
     * The default initial deposit amount, $5242.88. This amount was chosen so 
     * as to allow several withdrawals of small amounts without overdrawing a 
     * test account. But of course if we do want to overdraw the account, we can 
     * simply select an amount greater than this.
     */
    public static final CurrencyAmount DEFAULT_INITIAL_DEPOSIT_AMOUNT 
            = new CurrencyAmount(524288, DOLLARS);
    
    /**
     * How many months to backdate the default initial deposit. Then other test 
     * transactions can be realistically placed closer to the present day.
     */
    public static final int DEFAULT_NUMBER_OF_MONTHS_TO_BACKDATE = 3;
    
    /**
     * The default initial deposit. The deposit amount is {@link 
     * #DEFAULT_INITIAL_DEPOSIT_AMOUNT}, and the deposit date is today minus 
     * {@link #DEFAULT_NUMBER_OF_MONTHS_TO_BACKDATE} months.
     */
    public static final Deposit DEFAULT_INITIAL_DEPOSIT 
            = new Deposit(DEFAULT_INITIAL_DEPOSIT_AMOUNT, 
                    LocalDateTime.now()
                            .minusMonths(DEFAULT_NUMBER_OF_MONTHS_TO_BACKDATE));
    
    /**
     * A pseudorandom number generator. No explicit seed is given.
     */
    public static final Random RANDOM = new Random();
    
    @Test
    public void testBalanceReflectsInitialDeposit() {
        int cents = 10000 + AccountTest.RANDOM.nextInt(10000);
        CurrencyAmount expected = new CurrencyAmount(cents, DOLLARS);
        Deposit deposit = new Deposit(expected, LocalDateTime.now());
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, deposit);
        CurrencyAmount actual = account.balance;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testProcess() {
        System.out.println("process");
        Deposit initialDeposit = new Deposit(DEFAULT_INITIAL_DEPOSIT_AMOUNT, 
                LocalDateTime.now());
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                initialDeposit);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(initialDeposit);
        expected.add(secondTrx);
        List<Transaction> actual = account.getHistory();
        assertEquals(expected, actual);
    }
    
    public static class AccountImpl extends Account {

        public AccountImpl(Entity primary, Entity secondary, 
                Deposit initialDeposit) {
            super(primary, secondary, initialDeposit);
        }
        
    }
    
}
