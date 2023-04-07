/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT_AMOUNT_CENTS;
import static accounts.AccountTest.DEFAULT_NUMBER_OF_MONTHS_TO_BACKDATE;
import static accounts.AccountTest.DOLLARS;
import static accounts.AccountTest.RANDOM;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Transaction class. Also includes helper functions to create 
 * fictional transactions for testing purposes.
 * @author Alonso del Arte
 */
public class TransactionTest {
    
    public static final int DEFAULT_TRANSACTION_CENTS 
            = DEFAULT_INITIAL_DEPOSIT_AMOUNT_CENTS / 16;
    
    private static final int HOURS_IN_A_MONTH = 720;
    
    private static CurrencyAmount choosePositiveAmount() {
        int cents = RANDOM.nextInt(DEFAULT_TRANSACTION_CENTS) + 1;
        return new CurrencyAmount(cents, DOLLARS);
    }
    
    private static CurrencyAmount chooseAmount() {
        return new CurrencyAmount(RANDOM.nextInt(), DOLLARS);
    }
    
    private static LocalDateTime chooseDate() {
        int backdate = RANDOM.nextInt(HOURS_IN_A_MONTH 
                * DEFAULT_NUMBER_OF_MONTHS_TO_BACKDATE);
        return LocalDateTime.now().minusHours(backdate);
    }
    
    public static Deposit makeDeposit() {
        return new Deposit(choosePositiveAmount(), chooseDate());
    }
    
    public static Withdrawal makeWithdrawal() {
        return new Withdrawal(choosePositiveAmount().negate(), chooseDate());
    }
    
    public static Transaction makeTransaction() {
        if (RANDOM.nextBoolean()) {
            return makeDeposit();
        } else {
            return makeWithdrawal();
        }
    }
    
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        CurrencyAmount expected = chooseAmount();
        LocalDateTime date = chooseDate();
        Transaction trx = new TransactionImpl(expected, date);
        CurrencyAmount actual = trx.getAmount();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        Transaction trx = new TransactionImpl(chooseAmount(), chooseDate());
        assertEquals(trx, trx);
    }
    
    @Test
    public void testNotEqualsNull() {
        Transaction trx = new TransactionImpl(chooseAmount(), chooseDate());
        assertNotEquals(trx, null);
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        Transaction trxB = makeTransaction();
        Transaction trxA = new TransactionImpl(trxB.getAmount(), 
                trxB.getTime());
        assertNotEquals(trxA, trxB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        CurrencyAmount amount = chooseAmount();
        LocalDateTime date = chooseDate();
        Transaction someTransaction = new TransactionImpl(amount, date);
        Transaction sameTransaction = new TransactionImpl(amount, date);
        assertEquals(someTransaction, sameTransaction);
    }
    
    @Test
    public void testNotEqualsDiffAmount() {
        LocalDateTime time = LocalDateTime.now();
        CurrencyAmount amountA = chooseAmount();
        CurrencyAmount oneDollar = new CurrencyAmount(100, DOLLARS);
        CurrencyAmount amountB = amountA.plus(oneDollar);
        Transaction trxA = new TransactionImpl(amountA, time);
        Transaction trxB = new TransactionImpl(amountB, time);
        assertNotEquals(trxA, trxB);
    }
    
    @Test
    public void testNotEqualsDiffTime() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = timeA.minusDays(1);
        CurrencyAmount amount = chooseAmount();
        Transaction trxA = new TransactionImpl(amount, timeA);
        Transaction trxB = new TransactionImpl(amount, timeB);
        assertNotEquals(trxA, trxB);
    }
    
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CurrencyAmount amount = new CurrencyAmount(0, DOLLARS);
        LocalDateTime date = LocalDateTime.now();
        Transaction trxA = new TransactionImpl(amount, date);
        Transaction trxB = new Transaction(amount, date, "Transaction") {};
        assertNotEquals(trxA.hashCode(), trxB.hashCode());
    }
    
    private static class TransactionImpl extends Transaction {
        
        @Override
        public String toString() {
            return "Transaction of " + this.amount.toString() + " on " 
                    + this.dateTime.toString();
        }
        
        TransactionImpl(CurrencyAmount amt, LocalDateTime time) {
            super(amt, time, "Example Transaction");
        }
    
    }
    
}
