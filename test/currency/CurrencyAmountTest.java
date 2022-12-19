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
    
    private static final Currency DOLLARS 
            = Currency.getInstance(Locale.US);
        
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

}
