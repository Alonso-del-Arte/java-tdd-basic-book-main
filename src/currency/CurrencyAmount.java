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
        String numStr = Long.toString(this.amountInCents);
        int decPointPlace = numStr.length() - 2;
        if (decPointPlace < 0) {
            decPointPlace = 0;
        }
        String amtStr = "$" + numStr.substring(0, decPointPlace) + "." 
            + numStr.substring(decPointPlace);
        return amtStr;
    }
    
    public CurrencyAmount(long cents, Currency currency) {
        this.amountInCents = cents;
        this.currencyID = currency;
    }
    
}
