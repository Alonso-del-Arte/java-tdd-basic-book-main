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
import currency.CurrencyConversionNeededException;
import entities.Entity;

import static accounts.transactions.TransactionTest.DEFAULT_TRANSACTION_CENTS;
import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeTransaction;
import static accounts.transactions.TransactionTest.makeWithdrawal;
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
     * Euros (EUR), the official currency of much of Europe. This will be used 
     * for a few tests.
     */
    public static final Currency EUROS = Currency.getInstance("EUR");
    
    public static final int DEFAULT_INITIAL_DEPOSIT_AMOUNT_CENTS = 524288;
    
    /**
     * The default initial deposit amount, $5242.88. This amount was chosen so 
     * as to allow several withdrawals of small amounts without overdrawing a 
     * test account. But of course if we do want to overdraw the account, we can 
     * simply select an amount greater than this.
     */
    public static final CurrencyAmount DEFAULT_INITIAL_DEPOSIT_AMOUNT 
            = new CurrencyAmount(DEFAULT_INITIAL_DEPOSIT_AMOUNT_CENTS, DOLLARS);
    
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
    
    /**
     * Asserting two lists of transactions contains all the same transactions 
     * and no others.
     * @param expected The expected transactions. For example, a deposit of $100 
     * and a withdrawal of $20.
     * @param actual The actual transactions. For example, a deposit of $100, a  
     * withdrawal of $20 and another withdrawal of $20.
     * @throws AssertionError If <code>actual</code> is missing transactions 
     * that <code>expected</code> has, or if it does have the same transactions 
     * but also has other transactions.
     */
    static void assertContainsSame(List<Transaction> expected, 
            List<Transaction> actual) {
        String msg = "Expected " + expected.toString() + " but was " 
                + actual.toString();
        boolean isSubset = actual.containsAll(expected);
        boolean sameSize = expected.size() == actual.size();
        assert isSubset && sameSize : msg;
    }
    
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
    public void testNoProcessDiffCurrencyTransaction() {
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        CurrencyAmount euros = new CurrencyAmount(DEFAULT_TRANSACTION_CENTS, 
                AccountTest.EUROS);
        Transaction trx = new Deposit(euros, LocalDateTime.now());
        try {
            account.process(trx);
            String msg = "Trying to process " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString() 
                    + " should have caused an exception";
            fail(msg);
        } catch (CurrencyConversionNeededException curConvNeedExc) {
            System.out.println("Trying to process " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString() 
                    + " correctly caused CurrencyConversionNeededException");
            System.out.println("\"" + curConvNeedExc.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString();
            fail(msg);
        }
    }
    
    @Test
    public void testHasSufficientBalance() {
        System.out.println("hasSufficientBalance");
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        Deposit deposit = makeDeposit();
        account.process(deposit);
        CurrencyAmount amount = DEFAULT_INITIAL_DEPOSIT_AMOUNT
                .plus(deposit.getAmount()).negate();
        Withdrawal withdrawal = new Withdrawal(amount, LocalDateTime.now());
        String msg = "Account with " + account.getBalance().toString() 
                + " should fund withdrawal of " + amount.negate().toString();
        assert account.hasSufficientBalance(withdrawal) : msg;
    }
    
    @Test
    public void testInsufficientBalance() {
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        account.process(makeWithdrawal());
        Withdrawal withdrawal 
                = new Withdrawal(DEFAULT_INITIAL_DEPOSIT_AMOUNT.negate(), 
                        LocalDateTime.now());
        String msg = "Account with " + account.getBalance().toString() 
                + " should not fund withdrawal of " 
                + withdrawal.getAmount().negate().toString();
        assert !account.hasSufficientBalance(withdrawal) : msg;
    }
    
    @Test
    public void testProcess() {
        System.out.println("process");
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(DEFAULT_INITIAL_DEPOSIT);
        expected.add(secondTrx);
        List<Transaction> actual = account.getHistory();
        assertContainsSame(expected, actual);
    }
    
    @Test
    public void testNoProcessWithdrawalForInsufficientBalance() {
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        account.process(makeWithdrawal());
        Withdrawal withdrawal 
                = new Withdrawal(DEFAULT_INITIAL_DEPOSIT_AMOUNT.negate(), 
                        LocalDateTime.now());
        CurrencyAmount balance = account.balance;
        account.process(withdrawal);
        String msg = "Account with a balance of " + balance.toString() 
                + " should not have processed " + withdrawal.toString();
        assert balance.getAmountInCents() >= 0 : msg;
        assert !account.HISTORY.contains(withdrawal) : msg;
    }
    
    @Test
    public void testGetHistory() {
        System.out.println("getHistory");
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(DEFAULT_INITIAL_DEPOSIT);
        expected.add(secondTrx);
        List<Transaction> list = account.getHistory();
        list.add(makeTransaction());
        List<Transaction> actual = account.getHistory();
        assertContainsSame(expected, actual);
    }
    
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        Account account = new AccountImpl(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        CurrencyAmount expected = DEFAULT_INITIAL_DEPOSIT.getAmount();
        while (expected.getAmountInCents() > DEFAULT_TRANSACTION_CENTS) {
            Withdrawal withdrawal = makeWithdrawal();
            account.process(withdrawal);
            expected = expected.plus(withdrawal.getAmount());
            CurrencyAmount actual = account.getBalance();
            assertEquals(expected, actual);
        }
    }
    
    private static class AccountImpl extends Account {

        public AccountImpl(Entity primary, Entity secondary, 
                Deposit initialDeposit) {
            super(primary, secondary, initialDeposit);
        }
        
    }
    
}
