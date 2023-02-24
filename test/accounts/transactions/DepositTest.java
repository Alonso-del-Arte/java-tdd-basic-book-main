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
 * Tests of the Deposit class.
 * @author Alonso del Arte
 */
public class DepositTest {
    
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        int cents = TransactionTest.RANDOM.nextInt(262144) + 1;
        CurrencyAmount expected = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Deposit deposit = new Deposit(expected, LocalDateTime.now());
        CurrencyAmount actual = deposit.getAmount();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        CurrencyAmount amount = new CurrencyAmount(100, 
                TransactionTest.DOLLARS);
        LocalDateTime expected = LocalDateTime.now()
                .minusHours(TransactionTest.RANDOM.nextInt(72) + 1);
        Deposit deposit = new Deposit(amount, expected);
        LocalDateTime actual = deposit.getTime();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        CurrencyAmount amount = new CurrencyAmount(100, 
                TransactionTest.DOLLARS);
        Deposit deposit = new Deposit(amount, LocalDateTime.now());
        String expected = "Deposit";
        String actual = deposit.getDescription();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testReferentialEquality() {
        Deposit deposit = TransactionTest.makeDeposit();
        assertEquals(deposit, deposit);
    }
    
    @Test
    public void testNotEqualsNull() {
        Deposit deposit = TransactionTest.makeDeposit();
        assertNotEquals(deposit, null);
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        Deposit deposit = TransactionTest.makeDeposit();
        Withdrawal withdrawal = TransactionTest.makeWithdrawal();
        assertNotEquals(deposit, withdrawal);
    }
    
    @Test
    public void testNotEqualsDepositForDifferentAmount() {
        LocalDateTime time = LocalDateTime.now();
        int cents = TransactionTest.RANDOM.nextInt(10000) + 1;
        CurrencyAmount amountA = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Deposit depositA = new Deposit(amountA, time);
        CurrencyAmount amountB = amountA.times(2);
        Deposit depositB = new Deposit(amountB, time);
        assertNotEquals(depositA, depositB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        int cents = TransactionTest.RANDOM.nextInt(10000) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        Deposit someDeposit = new Deposit(amount, time);
        Deposit sameDeposit = new Deposit(amount, time);
        assertEquals(someDeposit, sameDeposit);
    }
    
    @Test
    public void testNotEqualsDepositForDifferentTime() {
        LocalDateTime timeA = LocalDateTime.now();
        LocalDateTime timeB = timeA.plusHours(1);
        int cents = TransactionTest.RANDOM.nextInt(10000) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        Deposit depositA = new Deposit(amount, timeA);
        Deposit depositB = new Deposit(amount, timeB);
        assertNotEquals(depositA, depositB);
    }
    
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        int capacity = TransactionTest.RANDOM.nextInt(32) + 8;
        Set<Deposit> deposits = new HashSet<>(capacity);
        Set<Integer> hashes = new HashSet<>(capacity);
        while (deposits.size() < capacity) {
            Deposit deposit = TransactionTest.makeDeposit();
            deposits.add(deposit);
            hashes.add(deposit.hashCode());
        }
        int expected = deposits.size();
        int actual = hashes.size();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testConstructorRejectsZeroDollars() {
        CurrencyAmount badAmount = new CurrencyAmount(0, 
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        try {
            Deposit badDeposit = new Deposit(badAmount, time);
            String msg = "Should not have been able to create deposit " 
                    + badDeposit.toString() + " with " + badAmount.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Bad deposit amount " + badAmount.toString() 
                     + " correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for bad deposit amount " 
                    + badAmount.toString();
            fail(msg);
        }
    }
    
    @Test
    public void testConstructorRejectsNegativeDollars() {
        int cents = -TransactionTest.RANDOM.nextInt(524288) - 1;
        CurrencyAmount badAmount = new CurrencyAmount(cents, 
                TransactionTest.DOLLARS);
        LocalDateTime time = LocalDateTime.now();
        try {
            Deposit badDeposit = new Deposit(badAmount, time);
            String msg = "Should not have been able to create " 
                    + badDeposit.toString() + " with " + badAmount.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Bad deposit amount " + badAmount.toString() 
                    + " correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for bad deposit amount " 
                    + badAmount.toString();
            fail(msg);
        }
    }
    
}
