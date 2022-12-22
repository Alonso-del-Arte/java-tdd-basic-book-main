/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

import java.util.Currency;
import java.util.Locale;
import java.util.Random;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the CurrencyConversionNeededException class.
 * @author Alonso del Arte
 */
public class CurrencyConversionNeededExceptionTest {
    
    private static final Currency DOLLARS = Currency.getInstance(Locale.US);
        
    private static final Currency POUNDS_STERLING 
            = Currency.getInstance(Locale.UK);

    private static final String MESSAGE = "For testing purposes";

    private static final Random RANDOM = new Random();

    /* *
     * Test of getMessage method, of class CurrencyConversionNeededException.
     */
//    @Test
    public void testGetMessage() {
        System.out.println("getMessage");
        CurrencyConversionNeededException instance = null;
        String expResult = "";
        String result = instance.getMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of the getAmountA function, of the CurrencyConversionNeededException 
     * class.
     */
    @Test
    public void testGetAmountA() {
        System.out.println("getAmountA");
        int centsA = RANDOM.nextInt(524288);
        CurrencyAmount expected = new CurrencyAmount(centsA, DOLLARS);
        int centsB = RANDOM.nextInt(524288);
        CurrencyAmount amountB = new CurrencyAmount(centsB, POUNDS_STERLING);
        CurrencyConversionNeededException exc 
                = new CurrencyConversionNeededException(MESSAGE, expected, 
                        amountB);
        CurrencyAmount actual = exc.getAmountA();
        assertEquals(expected, actual);
    }

    /* *
     * Test of getAmountB method, of class CurrencyConversionNeededException.
     */
//    @Test
    public void testGetAmountB() {
        System.out.println("getAmountB");
        CurrencyConversionNeededException instance = null;
        CurrencyAmount expResult = null;
        CurrencyAmount result = instance.getAmountB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
