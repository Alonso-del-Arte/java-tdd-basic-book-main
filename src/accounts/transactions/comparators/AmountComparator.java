/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import accounts.transactions.Transaction;

import java.util.Comparator;

/**
 * Comparator for two transactions according to their amounts. Lists sorted with 
 * this comparator consist first of withdrawals, if any, then comments, if any, 
 * and lastly deposits if any.
 * @author Alonso del Arte
 */
public class AmountComparator implements Comparator<Transaction> {

    /**
     * Compares two transactions according to their amounts. The two 
     * transactions should be drawn in the same currency.
     * @param trxA The first transaction. For example, a deposit of $200.00.
     * @param trxB The second transaction. For example, a withdrawal of $10.00.
     * @return 0 if the two transactions are equal in amount, a negative number 
     * (most likely &minus;1) if <code>trxA</code> is for less than 
     * <code>trxB</code>, or a positive number (most likely 1) if 
     * <code>trxA</code> is for more than <code>trxB</code>. In the example 
     * given above, this function would most likely return 1 but it may return 
     * some other positive integer.
     * @throws currency.CurrencyConversionNeededException If <code>trxA</code> 
     * and <code>trxB</code> are of different currencies, e.g., if 
     * <code>trxA</code> is a deposit of euros and <code>trxB</code> is a 
     * deposit of yen.
     */
    @Override
    public int compare(Transaction trxA, Transaction trxB) {
        return trxA.getAmount().compareTo(trxB.getAmount());
    }
    
}
