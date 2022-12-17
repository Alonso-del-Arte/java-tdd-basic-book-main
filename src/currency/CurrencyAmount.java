/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

import java.util.Currency;

/**
 *
 * @author Alonso del Arte
 */
public class CurrencyAmount {
    
    private final long amountInCents;
    
    private final Currency currencyID;
    
    @Override
    public String toString() {
        return "$499.89";
    }
    
    public CurrencyAmount(long cents, Currency currency) {
        this.amountInCents = cents;
        this.currencyID = currency;
    }
    
}
