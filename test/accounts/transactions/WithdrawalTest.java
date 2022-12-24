/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;

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
