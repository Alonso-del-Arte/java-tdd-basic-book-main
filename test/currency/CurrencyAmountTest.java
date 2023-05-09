/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
    
    private static final Random RANDOM = new Random();
        
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
    
    @Test
    public void testIsPositive() {
        System.out.println("isPositive");
        int cents = RANDOM.nextInt(16384) + 1;
        CurrencyAmount amount = new CurrencyAmount(cents, EUROS);
        String msg = "Amount " + amount.toString() 
                + " should be deemed positive";
        assert amount.isPositive() : msg;
    }
    
    @Test
    public void testIsNotPositive() {
        int cents = -RANDOM.nextInt(16384);
        CurrencyAmount amount = new CurrencyAmount(cents, EUROS);
        String msg = "Amount " + amount.toString() 
                + " should not be deemed positive";
        assert !amount.isPositive() : msg;
    }
    
    @Test
    public void testIsNegative() {
        System.out.println("isNegative");
        int cents = -RANDOM.nextInt(16384) - 1;
        CurrencyAmount amount = new CurrencyAmount(cents, EUROS);
        String msg = "Amount " + amount.toString() 
                + " should be deemed negative";
        assert amount.isPositive() : msg;
    }
    
    @Test(expected = NullPointerException.class)
    public void testPlusNull() {
        CurrencyAmount addend = new CurrencyAmount(533, EUROS);
        CurrencyAmount result = addend.plus(null);
        System.out.println(addend.toString() + " plus null equals " 
                + result.toString() + "???");
    }
    
    @Test
    public void testPlus() {
        System.out.println("plus");
        int addendACents = RANDOM.nextInt(65536) + 1;
        int addendBCents = RANDOM.nextInt(65536) + 1;
        CurrencyAmount addendA = new CurrencyAmount(addendACents, DOLLARS);
        CurrencyAmount addendB = new CurrencyAmount(addendBCents, DOLLARS);
        CurrencyAmount expected 
                = new CurrencyAmount(addendACents + addendBCents, DOLLARS);
        CurrencyAmount actual = addendA.plus(addendB);
        String msg = addendA.toString() + " + " + addendB.toString() + " = " 
                + expected.toString();
        assertEquals(msg, expected, actual);
    }

    @Test
    public void testPlusEuros() {
        int addendACents = RANDOM.nextInt(65536) + 1;
        int addendBCents = RANDOM.nextInt(65536) + 1;
        CurrencyAmount addendA = new CurrencyAmount(addendACents, EUROS);
        CurrencyAmount addendB = new CurrencyAmount(addendBCents, EUROS);
        CurrencyAmount expected 
                = new CurrencyAmount(addendACents + addendBCents, EUROS);
        CurrencyAmount actual = addendA.plus(addendB);
        String msg = addendA.toString() + " + " + addendB.toString() + " = " 
                + expected.toString();
        assertEquals(msg, expected, actual);
    }
    
    @Test(expected = CurrencyConversionNeededException.class)
    public void testPlusDifferentCurrencies() {
        CurrencyAmount dollars = new CurrencyAmount(49989, DOLLARS);
        CurrencyAmount euros = new CurrencyAmount(7320, EUROS);
        CurrencyAmount result = dollars.plus(euros);
        System.out.println("Trying to add " + dollars.toString() + " to " 
            + euros.toString() + " should not have given " + result.toString());
    }
    
    @Test(expected = ArithmeticException.class)
    public void testPlusTooMuch() {
        CurrencyAmount amountA = new CurrencyAmount(9000000000000000000L, 
                DOLLARS);
        CurrencyAmount amountB = new CurrencyAmount(1000000000000000000L, 
                DOLLARS);
        CurrencyAmount result = amountA.plus(amountB);
        System.out.println("Trying to add " + amountA.toString() + " to " 
                + amountB.toString() + " should not have given " 
                + result.toString());
    }

    @Test(expected = NullPointerException.class)
    public void testMinusNull() {
        CurrencyAmount minuend = new CurrencyAmount(533, EUROS);
        CurrencyAmount result = minuend.minus(null);
        System.out.println(minuend.toString() + " minus null equals " 
                + result.toString() + "???");
    }
    
    @Test
    public void testMinus() {
        System.out.println("minus");
        int minuendCents = RANDOM.nextInt(65536) + 1;
        int subtrahendCents = RANDOM.nextInt(65536) + 1;
        CurrencyAmount minuend = new CurrencyAmount(minuendCents, DOLLARS);
        CurrencyAmount subtrahend 
                = new CurrencyAmount(subtrahendCents, DOLLARS);
        CurrencyAmount expected 
                = new CurrencyAmount(minuendCents - subtrahendCents, DOLLARS);
        CurrencyAmount actual = minuend.minus(subtrahend);
        String msg = minuend.toString() + " - " + subtrahend.toString() + " = " 
                + expected.toString();
        assertEquals(msg, expected, actual);
    }

    @Test
    public void testMinusEuros() {
        int minuendCents = RANDOM.nextInt(65536) + 1;
        int subtrahendCents = RANDOM.nextInt(65536) + 1;
        CurrencyAmount minuend = new CurrencyAmount(minuendCents, EUROS);
        CurrencyAmount subtrahend = new CurrencyAmount(subtrahendCents, EUROS);
        CurrencyAmount expected 
                = new CurrencyAmount(minuendCents - subtrahendCents, EUROS);
        CurrencyAmount actual = minuend.minus(subtrahend);
        String msg = minuend.toString() + " - " + subtrahend.toString() + " = " 
                + expected.toString();
        assertEquals(msg, expected, actual);
    }
    
    @Test(expected = CurrencyConversionNeededException.class)
    public void testMinusDifferentCurrencies() {
        CurrencyAmount dollars = new CurrencyAmount(49989, DOLLARS);
        CurrencyAmount euros = new CurrencyAmount(7320, EUROS);
        CurrencyAmount result = dollars.minus(euros);
        System.out.println("Trying to subtract " + euros.toString() + " from " 
                + dollars.toString() + " should not have given " 
                + result.toString());
    }
    
    @Test(expected = ArithmeticException.class)
    public void testMinusTooMuch() {
        CurrencyAmount minuend = new CurrencyAmount(-9000000000000000000L, 
                DOLLARS);
        CurrencyAmount subtrahend = new CurrencyAmount(1000000000000000000L, 
                DOLLARS);
        CurrencyAmount result = minuend.minus(subtrahend);
        System.out.println("Trying to subtract " + subtrahend.toString() 
                + " from " + minuend.toString() + " should not have given " 
                + result.toString());
    }
    
    @Test
    public void testNegate() {
        System.out.println("negate");
        int cents = RANDOM.nextInt(524288) - 262144;
        CurrencyAmount amount = new CurrencyAmount(cents, YEN);
        CurrencyAmount expected = new CurrencyAmount(-cents, YEN);
        CurrencyAmount actual = amount.negate();
        assertEquals(expected, actual);
    }

    @Test
    public void testNegateDinars() {
        int cents = -RANDOM.nextInt(524288) + 262144;
        CurrencyAmount amount = new CurrencyAmount(cents, DINARS);
        CurrencyAmount expected = new CurrencyAmount(-cents, DINARS);
        CurrencyAmount actual = amount.negate();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTimes() {
        System.out.println("times");
        int cents = RANDOM.nextInt(10000);
        CurrencyAmount amount = new CurrencyAmount(cents, EUROS);
        int multiplier = RANDOM.nextInt(256) - 128;
        CurrencyAmount expected = new CurrencyAmount(cents * multiplier, EUROS);
        CurrencyAmount actual = amount.times(multiplier);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testTimesDollars() {
        int cents = RANDOM.nextInt(10000);
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        int multiplier = RANDOM.nextInt(256) - 128;
        CurrencyAmount expected = new CurrencyAmount(cents * multiplier, 
                DOLLARS);
        CurrencyAmount actual = amount.times(multiplier);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testDivisionByZero() {
        int cents = RANDOM.nextInt();
        CurrencyAmount amount = new CurrencyAmount(cents, EUROS);
        try {
            CurrencyAmount badResult = amount.divides(0);
            String msg = amount.toString() + " divided by 0 is said to be " 
                    + badResult.toString();
            fail(msg);
        } catch (IllegalArgumentException | ArithmeticException iae) {
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is wrong exception for division by zero";
            fail(msg);
        }
    }
    
    @Test
    public void testDivides() {
        System.out.println("divides");
        int cents = RANDOM.nextInt();
        CurrencyAmount amount = new CurrencyAmount(cents, DOLLARS);
        int divisor = 2 * RANDOM.nextInt(128) - 63;
        CurrencyAmount expected = new CurrencyAmount(cents / divisor, DOLLARS);
        CurrencyAmount actual = amount.divides(divisor);
        String msg = "Expecting " + amount.toString() + " divided by " + divisor 
                + " to be " + expected.toString();
        assertEquals(msg, expected, actual);
    }
    
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        CurrencyAmount negBal = new CurrencyAmount(-372, DOLLARS);
        CurrencyAmount zero = new CurrencyAmount(0, DOLLARS);
        CurrencyAmount amountA = new CurrencyAmount(49989, DOLLARS);
        CurrencyAmount amountB = new CurrencyAmount(104250, DOLLARS);
        CurrencyAmount amountC = new CurrencyAmount(583047758, DOLLARS);
        List<CurrencyAmount> expected = new ArrayList<>();
        expected.add(negBal);
        expected.add(zero);
        expected.add(amountA);
        expected.add(amountB);
        expected.add(amountC);
        List<CurrencyAmount> actual = new ArrayList<>();
        actual.add(amountB);
        actual.add(amountC);
        actual.add(zero);
        actual.add(negBal);
        actual.add(amountA);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testCompareDifferentCurrencies() {
        CurrencyAmount dollarsAmount = new CurrencyAmount(57380, DOLLARS);
        CurrencyAmount eurosAmount = new CurrencyAmount(57380, EUROS);
        try {
            int result = dollarsAmount.compareTo(eurosAmount);
            String msg = "Trying to compare " + dollarsAmount.toString() 
                    + " to " + eurosAmount.toString()
                    + " should not have given result " + result;
            fail(msg);
        } catch (CurrencyConversionNeededException curConvNeedExc) {
            System.out.println("Trying to compare " + dollarsAmount.toString() 
                    + " to " + eurosAmount.toString() 
                    + " correctly caused exception");
            System.out.println("\"" + curConvNeedExc.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for " + dollarsAmount.toString() 
                    + " to " + eurosAmount.toString() + " comparison";
            fail(msg);
        }
    }

}
