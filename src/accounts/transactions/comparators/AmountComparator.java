/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions.comparators;

import accounts.transactions.Transaction;
import currency.CurrencyAmount;
import currency.CurrencyConversionNeededException;

import java.util.Comparator;
import java.util.Currency;

/**
 *
 * @author Alonso del Arte
 */
public class AmountComparator implements Comparator<Transaction> {

    @Override
    public int compare(Transaction trxA, Transaction trxB) {
        CurrencyAmount amountA = trxA.getAmount();
        CurrencyAmount amountB = trxB.getAmount();
        Currency currencyA = amountA.getCurrency();
        Currency currencyB = amountB.getCurrency();
        if (currencyA != currencyB) {
            String excMsg = "Currency conversion needed to compare " 
                    + trxA.toString() + " to " + trxB.toString();
            throw new CurrencyConversionNeededException(excMsg, amountA, 
                    amountB);
        }
        return 0;
    }
    
}
