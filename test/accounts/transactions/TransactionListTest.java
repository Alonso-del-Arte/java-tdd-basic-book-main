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

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the TransactionList class.
 * @author Alonso del Arte
 */
public class TransactionListTest {
    
    private static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    private static final Currency EUROS = Currency.getInstance("EUR");
    
    private static Deposit makeDeposit() {
        int cents = TransactionTest.RANDOM.nextInt(262144) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        return new Deposit(amount, LocalDateTime.now());
    }
    
    private static Withdrawal makeWithdrawal() {
        int cents = -TransactionTest.RANDOM.nextInt(262144) - 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        return new Withdrawal(amount, LocalDateTime.now());
    }
    
    @Test
    public void testAddRejectsDifferentCurrency() {
        TransactionList list = new TransactionList(EUROS);
        Transaction transaction = makeDeposit();
        boolean opResult = list.add(transaction);
        String msg = "Should not have been able to add transaction of " 
                + transaction.getAmount().toString() 
                + " to a list of transactions in " + EUROS.getDisplayName();
        assert !opResult : msg;
    }
    
    /**
     * Test of the add function, of the TransactionList class.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Transaction transaction = makeDeposit();
        TransactionList list = new TransactionList(DOLLARS);
        boolean opResult = list.add(transaction);
        String msg = "Should have been able to add transaction of " 
                + transaction.getAmount().toString() 
                + " to a list of transactions in " + DOLLARS.getDisplayName();
        assert opResult : msg;
    }
    
    @Test
    public void testInitialBalanceIsZeroDollars() {
        TransactionList list = new TransactionList(DOLLARS);
        CurrencyAmount expected = new CurrencyAmount(0, DOLLARS);
        CurrencyAmount actual = list.getBalance();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testInitialBalanceIsZeroEuros() {
        TransactionList list = new TransactionList(EUROS);
        CurrencyAmount expected = new CurrencyAmount(0, EUROS);
        CurrencyAmount actual = list.getBalance();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of getBalance method, of class TransactionList.
     */
//    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        TransactionList instance = null;
        CurrencyAmount expResult = null;
        CurrencyAmount result = instance.getBalance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
