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
import currency.CurrencyConversionNeededException;

import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT;
import static accounts.AccountTest.DOLLARS;
import static accounts.AccountTest.RANDOM;
import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeWithdrawal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the AmountComparator class.
 * @author Alonso del Arte
 */
public class AmountComparatorTest {
    
//    @Test
    public void testCompareNoCompareForDifferentCurrencies() {
        int cents;
        //Currency
    }
    
    /**
     * Test of compare method, of class AmountComparator.
     */
//    @Test
    public void testCompare() {
        System.out.println("compare");
        Withdrawal withdrawal = makeWithdrawal();
        Comment comment = new Comment(DOLLARS, LocalDateTime.now(), 
                "$0.00 transaction for testing purposes");
        Deposit smallDeposit = makeDeposit();
        List<Transaction> expected = new ArrayList<>();
        expected.add(withdrawal);
        expected.add(comment);
        expected.add(smallDeposit);
        expected.add(DEFAULT_INITIAL_DEPOSIT);
        List<Transaction> actual = new ArrayList<>();
        actual.add(DEFAULT_INITIAL_DEPOSIT);
        actual.add(withdrawal);
        actual.add(comment);
        actual.add(smallDeposit);
        actual.sort(new AmountComparator());
        assertEquals(expected, actual);
    }
    
}
