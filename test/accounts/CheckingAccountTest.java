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
        CheckingAccount account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                        AccountTest.DEFAULT_INITIAL_DEPOSIT);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(AccountTest.DEFAULT_INITIAL_DEPOSIT);
        expected.add(secondTrx);
        List<Transaction> actual = account.getHistory();
        AccountTest.assertContainsSame(expected, actual);
    }
    
    @Test
    public void testGetHistory() {
        System.out.println("getHistory");
        CheckingAccount account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                        AccountTest.DEFAULT_INITIAL_DEPOSIT);
        Transaction secondTrx = makeTransaction();
        account.process(secondTrx);
        List<Transaction> expected = new ArrayList<>();
        expected.add(AccountTest.DEFAULT_INITIAL_DEPOSIT);
        expected.add(secondTrx);
        List<Transaction> list = account.getHistory();
        list.add(makeTransaction());
        List<Transaction> actual = account.getHistory();
        AccountTest.assertContainsSame(expected, actual);
    }
    
//    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        fail("Haven't written test yet");
    }
    
}
