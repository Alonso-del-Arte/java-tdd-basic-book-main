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
import currency.CurrencyConversionNeededException;

import static accounts.transactions.TransactionTest.DEFAULT_TRANSACTION_CENTS;
import static accounts.transactions.TransactionTest.makeTransaction;
import static accounts.transactions.TransactionTest.makeWithdrawal;
import static entities.ExampleEntities.EXAMPLE_CUSTOMER;

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
    public void testNoAssociatedSavingsForNewChecking() {
        CheckingAccount account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        String msg = "New checking account shouldn't have associated savings";
        assert !account.hasAssociatedSavingsAccount() : msg;
    }
    
    @Test
    public void testHasAssociatedSavingsAccount() {
        System.out.println("hasAssociatedSavingsAccount");
        CheckingAccount checking = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        SavingsAccount savings = new SavingsAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        checking.associate(savings);
        String msg = "After associate, checking should have associated savings";
        assert checking.hasAssociatedSavingsAccount() : msg;
    }
    
    @Test
    public void testGetAssociatedSavings() {
        System.out.println("getAssociatedSavings");
        CheckingAccount checking = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        SavingsAccount expected = new SavingsAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        checking.associate(expected);
        Account actual = checking.getAssociatedSavings();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDisassociate() {
        CheckingAccount checking = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        SavingsAccount savings = new SavingsAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        checking.associate(savings);
        checking.associate(null);
        String msg = "After null associate, checking should have no associate";
        assert !checking.hasAssociatedSavingsAccount() : msg;
    }
    
    @Test
    public void testBalanceReflectsInitialDeposit() {
        int cents = 10000 + AccountTest.RANDOM.nextInt(10000);
        CurrencyAmount expected 
                = new CurrencyAmount(cents, AccountTest.DOLLARS);
        Deposit deposit = new Deposit(expected, LocalDateTime.now());
        Account account = new CheckingAccount(EXAMPLE_CUSTOMER, deposit);
        CurrencyAmount actual = account.balance;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNoProcessDiffCurrencyTransaction() {
        Account account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        CurrencyAmount euros = new CurrencyAmount(DEFAULT_TRANSACTION_CENTS, 
                AccountTest.EUROS);
        Transaction trx = new Deposit(euros, LocalDateTime.now());
        try {
            account.process(trx);
            String msg = "Trying to process " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString() 
                    + " should have caused an exception";
            fail(msg);
        } catch (CurrencyConversionNeededException curConvNeedExc) {
            System.out.println("Trying to process " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString() 
                    + " correctly caused CurrencyConversionNeededException");
            System.out.println("\"" + curConvNeedExc.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for " + trx.toString() 
                    + " to account with initial deposit " 
                    + AccountTest.DEFAULT_INITIAL_DEPOSIT.toString();
            fail(msg);
        }
    }
    
    @Test
    public void testProcess() {
        System.out.println("process");
        Account account = new CheckingAccount(EXAMPLE_CUSTOMER, 
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
        Account account = new CheckingAccount(EXAMPLE_CUSTOMER, 
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
    
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        Account account = new CheckingAccount(EXAMPLE_CUSTOMER, 
                AccountTest.DEFAULT_INITIAL_DEPOSIT);
        CurrencyAmount expected 
                = AccountTest.DEFAULT_INITIAL_DEPOSIT.getAmount();
        while (expected.getAmountInCents() > DEFAULT_TRANSACTION_CENTS) {
            Withdrawal withdrawal = makeWithdrawal();
            account.process(withdrawal);
            expected = expected.plus(withdrawal.getAmount());
            CurrencyAmount actual = account.getBalance();
            assertEquals(expected, actual);
        }
    }
    
}
