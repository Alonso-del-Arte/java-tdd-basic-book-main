/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import static entities.ExampleEntities.EXAMPLE_CUSTOMER;

import java.time.LocalDateTime;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the SavingsAccount class.
 * @author Alonso del Arte
 */
public class SavingsAccountTest {
    
    @Test
    public void testBalanceReflectsInitialDeposit() {
        int cents = 10000 + AccountTest.RANDOM.nextInt(10000);
        CurrencyAmount expected 
                = new CurrencyAmount(cents, AccountTest.DOLLARS);
        Deposit deposit = new Deposit(expected, LocalDateTime.now());
        SavingsAccount account 
                = new SavingsAccount(EXAMPLE_CUSTOMER, deposit);
        CurrencyAmount actual = account.balance;
        assertEquals(expected, actual);
    }
    
}
