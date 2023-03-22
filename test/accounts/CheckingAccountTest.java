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
import static accounts.transactions.TransactionTest.makeTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the CheckingAccount class.
 * @author Alonso del Arte
 */
public class CheckingAccountTest {
    
    @Test
    public void testBalanceReflectsInitialDeposit() {
        int cents = 10000 + AccountTest.RANDOM.nextInt(10000);
        CurrencyAmount expected 
                = new CurrencyAmount(cents, AccountTest.DOLLARS);
        Deposit deposit = new Deposit(expected, LocalDateTime.now());
        CheckingAccount account 
                = new CheckingAccount(EXAMPLE_CUSTOMER, deposit);
        CurrencyAmount actual = account.balance;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testProcess() {
        System.out.println("process");
        Deposit initialDeposit = new Deposit(AccountTest
                .DEFAULT_INITIAL_DEPOSIT_AMOUNT, 
                LocalDateTime.now());
        CheckingAccount account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                        initialDeposit);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(initialDeposit);
        expected.add(secondTrx);
        List<Transaction> actual = account.getHistory();
        assertEquals(expected, actual);
    }
    
}
