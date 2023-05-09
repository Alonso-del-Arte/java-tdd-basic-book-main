/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

import java.util.Currency;
import java.util.Objects;

/**
 * Represents an amount of money, like $3,083 or 50,000&yen;. Some functions for 
 * money arithmetic are provided.
 * @author Alonso del Arte
 */
public class CurrencyAmount implements Comparable<CurrencyAmount> {
    
    private final long amountInCents;
    
    private final Currency currencyID;
    
    public long getAmountInCents() {
        return this.amountInCents;
    }
    
    public Currency getCurrency() {
        return this.currencyID;
    }
    
    public boolean isPositive() {
        return this.amountInCents > 0;
    }

    public boolean isNegative() {
        return this.amountInCents < 0;
    }

    public boolean isNotPositive() {
        return false;
    }

    public boolean isNotNegative() {
        return false;
    }

    public CurrencyAmount plus(CurrencyAmount addend) {
        if (addend == null) {
            String excMsg = "Addend must not be null";
            throw new NullPointerException(excMsg);
        }
        if (this.currencyID != addend.currencyID) {
            String excMsg = "Convert before adding";
            throw new CurrencyConversionNeededException(excMsg, this, addend);
        }
        return new CurrencyAmount(Math.addExact(this.amountInCents, 
                addend.amountInCents), this.currencyID);
    }

    public CurrencyAmount negate() {
        return new CurrencyAmount(-this.amountInCents, this.currencyID);
    }

    public CurrencyAmount minus(CurrencyAmount subtrahend) {
        return this.plus(subtrahend.negate());
    }
    
    public CurrencyAmount times(int multiplicand) {
        return new CurrencyAmount(this.amountInCents * multiplicand, 
                this.currencyID);
    }
    
    public CurrencyAmount divides(int divisor) {
        if (divisor == 0) {
            String excMsg = "Can't divide " + this.toString() + " by 0";
            throw new IllegalArgumentException(excMsg);
        }
        return new CurrencyAmount(this.amountInCents / divisor, 
                this.currencyID);
    }
    
    @Override
    public int compareTo(CurrencyAmount other) {
        CurrencyAmount diff = this.minus(other);
        return Long.signum(diff.amountInCents);
    }
    
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (int) (this.amountInCents 
                ^ (this.amountInCents >>> 32));
        hash = 17 * hash + Objects.hashCode(this.currencyID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final CurrencyAmount other = (CurrencyAmount) obj;
        if (this.amountInCents != other.amountInCents) {
            return false;
        }
        return Objects.equals(this.currencyID, other.currencyID);
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
