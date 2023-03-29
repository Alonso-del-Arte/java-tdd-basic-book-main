/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import accounts.transactions.Transaction;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Comparator for two transactions according to their timestamps. Lists sorted 
 * with this comparator have the most recent transaction last and the least 
 * recent transaction first.
 * @author Alonso del Arte
 */
public class DateComparator implements Comparator<Transaction> {
    
    /**
     * Compares two transactions according to their timestamps. The two 
     * transactions need not be drawn in the same currency.
     * @param trxA A transaction. For example, the very first deposit to the 
     * account.
     * @param trxB A transaction. For example, a direct deposit scheduled for 
     * next Friday.
     * @return 0 if the two transactions are on the same date and time, a 
     * negative number (most likely &minus;1) if <code>trxA</code> took place 
     * before <code>trxB</code>, or a positive number (most likely 1) if 
     * <code>trxA</code> took place after <code>trxB</code>. In the example 
     * given above, this function would most likely return &minus;1 but it may 
     * return some other negative integer.
     */
    @Override
    public int compare(Transaction trxA, Transaction trxB) {
        return trxA.getTime().compareTo(trxB.getTime());
    }
    
}
