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
 * Tests of the Transaction class. Also includes helper functions to create 
 * fictional transactions for testing purposes.
 * @author Alonso del Arte
 */
public class TransactionTest {
    
    static final Currency DOLLARS = Currency.getInstance(Locale.US);
    
    static final Random RANDOM = new Random();
    
    private static final int HOURS_IN_A_MONTH = 720;
    
    private static CurrencyAmount choosePositiveAmount() {
        int cents = RANDOM.nextInt(262144) + 1;
        return new CurrencyAmount(cents, DOLLARS);
    }
    
    private static CurrencyAmount chooseAmount() {
        return new CurrencyAmount(RANDOM.nextInt(), DOLLARS);
    }
    
    private static LocalDateTime chooseDate() {
        int backdate = RANDOM.nextInt(HOURS_IN_A_MONTH);
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
