/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT;
import static accounts.AccountTest.DOLLARS;
import static accounts.AccountTest.RANDOM;
import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeWithdrawal;

import accounts.transactions.Comment;
import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import currency.CurrencyConversionNeededException;

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
    
    @Test
    public void testCompareNoCompareForDifferentCurrencies() {
        int cents = RANDOM.nextInt(1048576) + 1;
        Currency canadianDollars = Currency.getInstance(Locale.CANADA);
        Currency swissFrancs = Currency.getInstance("CHF");
        CurrencyAmount amountA = new CurrencyAmount(cents, canadianDollars);
        CurrencyAmount amountB = new CurrencyAmount(cents, swissFrancs);
        LocalDateTime date = LocalDateTime.now();
        Transaction depositA = new Deposit(amountA, date);
        Transaction depositB = new Deposit(amountB, date);
        AmountComparator comparator = new AmountComparator();
        String msgPart = "Trying to compare " + depositA.toString() + " to " 
                + depositB.toString() + " ";
        try {
            int badResult = comparator.compare(depositA, depositB);
            String msg = msgPart + "should not have given result " + badResult;
            fail(msg);
        } catch (CurrencyConversionNeededException curConvNeedExc) {
            System.out.println(msgPart 
                    + " correctly caused CurrencyConversionNeededException");
            System.out.println("\"" + curConvNeedExc.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = msgPart + " should not have caused " 
                    + re.getClass().getName();
            fail(msg);
        }
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
