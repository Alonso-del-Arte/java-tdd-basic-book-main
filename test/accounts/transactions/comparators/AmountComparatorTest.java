/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import accounts.transactions.Comment;
import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;

import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeTransaction;
import static accounts.transactions.TransactionTest.makeWithdrawal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alonso del Arte
 */
public class BalanceComparatorTest {
    
    /**
     * Test of compare method, of class BalanceComparator.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Transaction trxA = null;
        Transaction trxB = null;
        BalanceComparator instance = new BalanceComparator();
        int expResult = 0;
        int result = instance.compare(trxA, trxB);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
