/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alonso del Arte
 */
public class CurrencyAmountTest {
    
    private static final Currency DINARS = Currency.getInstance(Locale
            .forLanguageTag("ar-LY"));
    
    private static final Currency DOLLARS 
            = Currency.getInstance(Locale.US);
    
    private static final Currency EUROS = Currency.getInstance("EUR");
    
    private static final Currency YEN = Currency.getInstance(Locale.JAPAN);
        
    @Test
    public void testToString() {
        System.out.println("toString");
        CurrencyAmount amount 
                = new CurrencyAmount(49989, DOLLARS);
        String expected = "$499.89";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringOtherAmount() {
        CurrencyAmount amount = new CurrencyAmount(104250, DOLLARS);
        String expected = "$1042.50";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringCentAmount() {
        CurrencyAmount amount = new CurrencyAmount(5, DOLLARS);
        String expected = "$0.05";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringNegativeCentAmount() {
        CurrencyAmount amount = new CurrencyAmount(-8, DOLLARS);
        String expected = "$-0.08";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringEuroAmount() {
        CurrencyAmount amount = new CurrencyAmount(7320, EUROS);
        String expected = "EUR73.20";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringDinarAmount() {
        CurrencyAmount amount = new CurrencyAmount(29505, DINARS);
        String expected = "LYD29.505";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringDirhamAmount() {
        Locale arJordan = Locale.forLanguageTag("ar-JO");
        Currency dinars = Currency.getInstance(arJordan);
        CurrencyAmount amount = new CurrencyAmount(709, dinars);
        String expected = "JOD0.709";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringYenAmount() {
        CurrencyAmount amount = new CurrencyAmount(20167, YEN);
        String expected = "JPY20167";
        String actual = amount.toString();
        assertEquals(expected, actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testConstructorRefuseNullCurrency() {
        CurrencyAmount badAmount = new CurrencyAmount(0, null);
        System.out.println("CurrencyAmount@" 
                + Integer.toHexString(badAmount.hashCode()) 
                + " should not have been created with null currency");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorRefuseMetals() {
        Currency platinum = Currency.getInstance("XPT");
        CurrencyAmount ptAmount = new CurrencyAmount(102153, platinum);
        System.out.println("Shouldn't have created CurrencyAmount@" 
                + Integer.toHexString(ptAmount.hashCode()) + " with " 
                + platinum.getDisplayName() + " as the currency");
    }
    
    @Test
    public void testGetAmountInCents() {
        System.out.println("getAmountInCents");
        int expected = (int) (Math.random() * 262144) - 131072;
        CurrencyAmount amount = new CurrencyAmount(expected, EUROS);
        long actual = amount.getAmountInCents();
        String msg = "Amount in cents for " + amount.toString() + " should be " 
                + expected;
        assertEquals(msg, expected, actual);
    }
    
    @Test
    public void testGetCurrency() {
        System.out.println("getCurrency");
        CurrencyAmount amount = new CurrencyAmount(15347, EUROS);
        Currency actual = amount.getCurrency();
        assertEquals(EUROS, actual);
    }
    
    @Test
    public void testGetOtherCurrency() {
        CurrencyAmount amount = new CurrencyAmount(15347, DOLLARS);
        Currency actual = amount.getCurrency();
        assertEquals(DOLLARS, actual);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlusNull() {
        CurrencyAmount addend = new CurrencyAmount(533, EUROS);
        CurrencyAmount result = addend.plus(null);
        System.out.println(addend.toString() + " plus null equals " 
                + result.toString() + "???");
    }

}
