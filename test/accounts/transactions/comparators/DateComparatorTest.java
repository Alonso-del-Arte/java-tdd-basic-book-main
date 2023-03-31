/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT;
import static accounts.AccountTest.DOLLARS;
import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeWithdrawal;

import accounts.transactions.Comment;
import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the DateComparator class.
 * @author Alonso del Arte
 */
public class DateComparatorTest {
    
    /**
     * Test of the compare function, of the DateComparator class.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Withdrawal pastWithdrawal = makeWithdrawal();
        LocalDateTime now = LocalDateTime.now();
        Comment todaysComment = new Comment(DOLLARS, now, 
                "$0.00 transaction for testing purposes");
        CurrencyAmount amount = makeDeposit().getAmount();
        LocalDateTime tomorrow = now.plusDays(1);
        Deposit futureDeposit = new Deposit(amount, tomorrow);
        List<Transaction> expected = new ArrayList<>();
        expected.add(DEFAULT_INITIAL_DEPOSIT);
        expected.add(pastWithdrawal);
        expected.add(todaysComment);
        expected.add(futureDeposit);
        List<Transaction> actual = new ArrayList<>();
        actual.add(todaysComment);
        actual.add(futureDeposit);
        actual.add(DEFAULT_INITIAL_DEPOSIT);
        actual.add(pastWithdrawal);
        actual.sort(new DateComparator());
        assertEquals(expected, actual);
    }
    
}
