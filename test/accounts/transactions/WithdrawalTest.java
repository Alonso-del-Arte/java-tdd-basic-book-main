/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alonso del Arte
 */
public class WithdrawalTest {
    
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        int cents = -TransactionTest.RANDOM.nextInt(262144) - 1;
        CurrencyAmount expected = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Withdrawal withdrawal = new Withdrawal(expected, LocalDateTime.now());
        CurrencyAmount actual = withdrawal.getAmount();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        CurrencyAmount amount = new CurrencyAmount(-100, 
                TransactionTest.DOLLARS);
        LocalDateTime expected = LocalDateTime.now()
                .minusHours(TransactionTest.RANDOM.nextInt(72) + 1);
        Withdrawal withdrawal = new Withdrawal(amount, expected);
        LocalDateTime actual = withdrawal.getTime();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        CurrencyAmount amount = new CurrencyAmount(-100, 
                TransactionTest.DOLLARS);
        Withdrawal withdrawal = new Withdrawal(amount, LocalDateTime.now());
        String expected = "Withdrawal";
        String actual = withdrawal.getDescription();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        Withdrawal withdrawal = TransactionTest.makeWithdrawal();
        assertEquals(withdrawal, withdrawal);
    }
    
    @Test
    public void testNotEqualsNull() {
        Withdrawal withdrawal = TransactionTest.makeWithdrawal();
        assertNotEquals(withdrawal, null);
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        Withdrawal withdrawal = TransactionTest.makeWithdrawal();
        Deposit deposit = TransactionTest.makeDeposit();
        assertNotEquals(withdrawal, deposit);
    }
    
    @Test
    public void testNotEqualsWithdrawalForDifferentAmount() {
        LocalDateTime time = LocalDateTime.now();
        int cents = -TransactionTest.RANDOM.nextInt(10000) - 1;
        CurrencyAmount amountA = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Withdrawal withdrawalA = new Withdrawal(amountA, time);
        CurrencyAmount amountB = amountA.times(2);
        Withdrawal withdrawalB = new Withdrawal(amountB, time);
        assertNotEquals(withdrawalA, withdrawalB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        int cents = -TransactionTest.RANDOM.nextInt(10000) - 1;
        CurrencyAmount amount = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        Withdrawal someWithdrawal = new Withdrawal(amount, time);
        Withdrawal sameWithdrawal = new Withdrawal(amount, time);
        assertEquals(someWithdrawal, sameWithdrawal);
    }
    
    @Test
    public void testNotEqualsWithdrawalForDifferentTime() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = timeA.plusHours(1);
        int cents = -TransactionTest.RANDOM.nextInt(10000) - 1;
        CurrencyAmount amount = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Withdrawal withdrawalA = new Withdrawal(amount, timeA);
        Withdrawal withdrawalB = new Withdrawal(amount, timeB);
        assertNotEquals(withdrawalA, withdrawalB);
    }
    
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int capacity = TransactionTest.RANDOM.nextInt(32) + 8;
        Set<Withdrawal> withdrawals = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        while (withdrawals.size() < capacity) {
            Withdrawal withdrawal = TransactionTest.makeWithdrawal();
            withdrawals.add(withdrawal);
            hashes.add(withdrawal.hashCode());
        }
        int expected = withdrawals.size();
        int actual = hashes.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testConstructorRejectsZeroDollars() {
        CurrencyAmount badAmount = new CurrencyAmount(0, 
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        try {
            Withdrawal badWithdrawal = new Withdrawal(badAmount, time);
            String msg = "Should not have been able to create " 
                    + badWithdrawal.toString() + " with " 
                    + badAmount.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Bad withdrawal amount " + badAmount.toString() 
                    + " correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for bad withdrawal amount " 
                    + badAmount.toString();
            fail(msg);
        }
    }
    
    @Test
    public void testConstructorRejectsPositiveDollars() {
        int cents = TransactionTest.RANDOM.nextInt(524288) + 1;
        CurrencyAmount badAmount = new CurrencyAmount(cents,  
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        try {
            Withdrawal badWithdrawal = new Withdrawal(badAmount, time);
            String msg = "Should not have been able to create " 
                    + badWithdrawal.toString() + " with " 
                    + badAmount.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Bad withdrawal amount " + badAmount.toString() 
                    + " correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for bad withdrawal amount " 
                    + badAmount.toString();
            fail(msg);
        }
    }
    
}
