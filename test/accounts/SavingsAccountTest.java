/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts;

import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT;
import static accounts.AccountTest.DEFAULT_INITIAL_DEPOSIT_AMOUNT;
import accounts.transactions.Deposit;
import accounts.transactions.Transaction;
import static accounts.transactions.TransactionTest.DEFAULT_TRANSACTION_CENTS;
import static accounts.transactions.TransactionTest.makeDeposit;
import static accounts.transactions.TransactionTest.makeTransaction;
import static accounts.transactions.TransactionTest.makeWithdrawal;
import accounts.transactions.Withdrawal;
import currency.CurrencyAmount;
import currency.CurrencyConversionNeededException;
import static entities.ExampleEntities.EXAMPLE_CUSTOMER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    
    @Test
    public void testNoProcessDiffCurrencyTransaction() {
        SavingsAccount account = new SavingsAccount(EXAMPLE_CUSTOMER, 
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
        SavingsAccount account = new SavingsAccount(EXAMPLE_CUSTOMER, 
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
    public void testNoProcessWithdrawalForInsufficientBalance() {
        Account account = new SavingsAccount(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        account.process(makeWithdrawal());
        Withdrawal withdrawal 
                = new Withdrawal(DEFAULT_INITIAL_DEPOSIT_AMOUNT.negate(), 
                        LocalDateTime.now());
        CurrencyAmount balance = account.balance;
        account.process(withdrawal);
        String msg = "Account with a balance of " + balance.toString() 
                + " should not have processed " + withdrawal.toString();
        assert balance.getAmountInCents() >= 0 : msg;
        assert !account.HISTORY.contains(withdrawal) : msg;
    }
    
    @Test
    public void testGetHistory() {
        System.out.println("getHistory");
        SavingsAccount account = new SavingsAccount(EXAMPLE_CUSTOMER, 
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
        SavingsAccount account = new SavingsAccount(EXAMPLE_CUSTOMER, 
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
    
    @Test
    public void testHasSufficientBalance() {
        System.out.println("hasSufficientBalance");
        Account account = new SavingsAccount(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        Deposit deposit = makeDeposit();
        account.process(deposit);
        CurrencyAmount amount = DEFAULT_INITIAL_DEPOSIT_AMOUNT
                .plus(deposit.getAmount()).negate();
        Withdrawal withdrawal = new Withdrawal(amount, LocalDateTime.now());
        String msg = "Account with " + account.getBalance().toString() 
                + " should fund withdrawal of " + amount.negate().toString();
        assert account.hasSufficientBalance(withdrawal) : msg;
    }
    
    @Test
    public void testInsufficientBalance() {
        Account account = new SavingsAccount(EXAMPLE_CUSTOMER, null, 
                DEFAULT_INITIAL_DEPOSIT);
        account.process(makeWithdrawal());
        Withdrawal withdrawal 
                = new Withdrawal(DEFAULT_INITIAL_DEPOSIT_AMOUNT.negate(), 
                        LocalDateTime.now());
        String msg = "Account with " + account.getBalance().toString() 
                + " should not fund withdrawal of " 
                + withdrawal.getAmount().negate().toString();
        assert !account.hasSufficientBalance(withdrawal) : msg;
    }
    
}
