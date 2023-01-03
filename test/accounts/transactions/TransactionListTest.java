/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

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
    
    /**
     * Test of add method, of class TransactionList.
     */
//    @Test
    public void testAdd() {
        System.out.println("add");
        Transaction transaction = null;
        TransactionList instance = null;
        boolean expResult = false;
        boolean result = instance.add(transaction);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
