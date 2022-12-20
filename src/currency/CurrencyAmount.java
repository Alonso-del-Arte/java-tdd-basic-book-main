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
        final int centPlaces = this.currencyID.getDefaultFractionDigits();
        if (centPlaces == 0) {
            return this.currencyID.getSymbol() + this.amountInCents;
        }
        String numStr = Long.toString(Math.abs(this.amountInCents));
        while (numStr.length() <= centPlaces) {
            numStr = "0" + numStr;
        }
        if (this.amountInCents < 0) {
            numStr = "-" + numStr;
        }
        int decPointPlace = numStr.length() - centPlaces;
        return this.currencyID.getSymbol() + numStr.substring(0, decPointPlace) 
                + "." + numStr.substring(decPointPlace);
}    

    public CurrencyAmount(long cents, Currency currency) {
        if (currency == null) {
            String excMsg = "Currency must not be null";
            throw new NullPointerException(excMsg);
        }
        if (currency.getDefaultFractionDigits() < 0) {
            String excMsg = currency.getDisplayName() + " (" 
                    + currency.getCurrencyCode() + ") is not valid";
            throw new IllegalArgumentException(excMsg);
        }
        this.amountInCents = cents;
        this.currencyID = currency;
    }
    
}
