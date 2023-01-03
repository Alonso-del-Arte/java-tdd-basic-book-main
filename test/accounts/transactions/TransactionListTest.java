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
 * Tests of the TransactionList class.
 * @author Alonso del Arte
 */
public class TransactionListTest {
    
    private static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    private static final Currency EUROS = Currency.getInstance("EUR");
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testAddRejectsDifferentCurrency() {
        TransactionList list = new TransactionList(EUROS);
        int cents = RANDOM.nextInt(262144) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        Transaction transaction = new Deposit(amount, LocalDateTime.now());
        boolean opResult = list.add(transaction);
        String msg = "Should not have been able to add transaction of " 
                + amount.toString() + " to a list of transactions in " 
                + EUROS.getDisplayName();
        assert !opResult : msg;
    }
    
    /**
     * Test of the add function, of the TransactionList class.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        int cents = RANDOM.nextInt(262144) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        Transaction transaction = new Deposit(amount, LocalDateTime.now());
        TransactionList list = new TransactionList(DOLLARS);
        boolean opResult = list.add(transaction);
        String msg = "Should have been able to add transaction of " 
                + amount.toString() + " to a list of transactions in " 
                + DOLLARS.getDisplayName();
        assert opResult : msg;
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
