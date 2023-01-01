/*
 * Copyright (C) 2022 Alonso del Arte
 *
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation; either version 2 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple 
 * Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package entities.idnumbers;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the SocialSecurityNumber class.
 * @author Alonso del Arte
 */
public class SocialSecurityNumberTest {
    
    // REF: https://www.ssa.gov/history/ssnmyth.html
    
    // REF: https://www.ssa.gov/employer/stateweb.htm
    
    // REF: https://www.ssa.gov/history/ssn/misused.html
    
    // REF: https://www.irs.gov/forms-pubs/about-form-ss-4
    
    private static final int MAX_SSN = 772999999;
    
    private static final Random RANDOM = new Random();
    
    @Test
    public void testReferentialEquality() {
        int number = RANDOM.nextInt(MAX_SSN) + 1;
        SocialSecurityNumber ssn = new SocialSecurityNumber(number);
        assertEquals(ssn, ssn);
    }
    
    @Test
    public void testNotEqualsNull() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(726);
        assertNotEquals(ssn, null);
    }
    
    @Test
    public void testNotEqualsObjOtherClass() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(750025000);
        Color color = new Color(750025000, true);
        assertNotEquals(ssn, color);
    }
    
    @Test
    public void testUnequalSSNs() {
        SocialSecurityNumber ssnA = new SocialSecurityNumber(78051120);
        SocialSecurityNumber ssnB = new SocialSecurityNumber(219099999);
        assertNotEquals(ssnA, ssnB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        int number = RANDOM.nextInt(MAX_SSN) + 1;
        SocialSecurityNumber someSSN = new SocialSecurityNumber(number);
        SocialSecurityNumber sameSSN = new SocialSecurityNumber(number);
        assertEquals(someSSN, sameSSN);
    }

    @Test
    public void testHashCode() {
        int expected = 1000;
        Set<Integer> hashes = new HashSet<>(expected);
        int firstNumber = RANDOM.nextInt(MAX_SSN - expected) + 1;
        int lastNumber = firstNumber + expected;
        for (int i = firstNumber; i < lastNumber; i++) {
            SocialSecurityNumber ssn = new SocialSecurityNumber(i);
            int hash = ssn.hashCode();
            assertNotEquals(i, hash);
            hashes.add(hash);
        }
        int actual = hashes.size();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of the toString function, of the class SocialSecurityNumber. The 
     * Social Security Administration asserts that an SSN with area number 000 
     * will never be assigned to anyone.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SocialSecurityNumber ssn = new SocialSecurityNumber(1729);
        String expected = "000-00-1729";
        String actual = ssn.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringArea001() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(1999999);
        String expected = "001-99-9999";
        String actual = ssn.toString();
        assertEquals(expected, actual);
    }
    
    /**
     * Test of toString function of class SocialSecurityNumber. The Social 
     * Security Number 078-05-1120 belonged to Hilda Schrader Whitcher, an 
     * executive secretary at E. H. Ferree, a wallet manufacturer. Then it was 
     * used in an example card in wallets sold at Woolworth and other stores. 
     * Eventually, the Social Security Administration voided the number and 
     * issued the secretary a new number.
     */
    @Test
    public void testToStringArea078() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(78051120);
        String expected = "078-05-1120";
        String actual = ssn.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToStringArea750() {
        SocialSecurityNumber ssn = new SocialSecurityNumber(750025000);
        String expected = "750-02-5000";
        String actual = ssn.toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToRedactedString() {
        System.out.println("toRedactedString");
        int number = RANDOM.nextInt(MAX_SSN) + 1;
        SocialSecurityNumber ssn = new SocialSecurityNumber(number);
        String expected = "***-**-" + ssn.toString().substring(7);
        String actual = ssn.toRedactedString();
        assertEquals(expected, actual);
    }
    
//    @Test
    public void testMatchesLast4() {
        System.out.println("matchesLast4");
        fail("Haven't written group of tests yet");
    }
    
    @Test
    public void testCorrectSSNDashPlacement() {
        System.out.println("correctSSNDashPlacement");
        String s = "***-**-1120";
        String msg = "\"" + s + "\" has correct SSN dash placement";
        assert SocialSecurityNumber.correctSSNDashPlacement(s) : msg;
    }
    
    @Test
    public void testIncorrectSSNDashPlacement() {
        String s = "12-3456789";
        String msg = "\"" + s + "\" does not have correct SSN dash placement";
        assert !SocialSecurityNumber.correctSSNDashPlacement(s) : msg;
    }
    
    /**
     * Test of parseSSN function of SocialSecurityNumber class. The Social 
     * Security Number 219-09-9999 was used as an example in a Social Security 
     * pamphlet in 1940. In 1962, a woman in Utah claimed that was her Social 
     * Security Number and presented the pamphlet as proof. So the number was 
     * invalidated.
     */
    @Test
    public void testParseSSN() {
        System.out.println("parseSSN");
        String s = "219-09-9999";
        SocialSecurityNumber expected = new SocialSecurityNumber(219099999);
        SocialSecurityNumber actual = SocialSecurityNumber.parseSSN(s);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testParseOtherSSN() {
        int area = RANDOM.nextInt(672) + 100;
        int group = RANDOM.nextInt(90) + 10;
        int serial = RANDOM.nextInt(9000) + 1000;
        int number = area * 1000000 + group * 10000 + serial;
        String s = area + "-" + group + "-" + serial;
        SocialSecurityNumber expected = new SocialSecurityNumber(number);
        SocialSecurityNumber actual = SocialSecurityNumber.parseSSN(s);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testNoParseEIN() {
        String s = "12-3456789";
        try {
            SocialSecurityNumber ein = SocialSecurityNumber.parseSSN(s);
            String msg = "EIN \"" + s 
                    + "\" should not have been interpresented as SSN " + ein;
            fail(msg);
        } catch (NumberFormatException nfe) {
            System.out.println("Trying to interpret EIN \"" + s 
                    + "\" as an SSN correctly caused NumberFormatException");
            System.out.println("\"" + nfe.getMessage() + "\"");
            String allCaps = nfe.getMessage().toUpperCase();
            boolean mentionEIN = allCaps.contains("EIN") 
                    || allCaps.contains("EMPLOYER IDENTIFICATION NUMBER");
            String msg = "Message should mention EIN";
            assert mentionEIN : msg;
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for taking EIN \"" + s
                    + "\" as an SSN";
            fail(msg);
        }
    }
    
//    @Test
    public void testNoParseBadString() {
        String s = "Not an SSN";
        try {
            SocialSecurityNumber badSSN = SocialSecurityNumber.parseSSN(s);
            String msg = "Bad String \"" + s 
                    + "\" should not have been interpresented as SSN \"" 
                    + badSSN;
            fail(msg);
        } catch (NumberFormatException nfe) {
            System.out.println("Trying to interpret bad String \"" + s 
                    + "\" as an SSN correctly caused NumberFormatException");
            System.out.println("\"" + nfe.getMessage() + "\"");
            String allCaps = nfe.getMessage().toUpperCase();
            boolean mentionEIN = allCaps.contains("EIN") 
                    || allCaps.contains("EMPLOYER IDENTIFICATION NUMBER");
            String msg = "Message should not mention EIN";
            assert !mentionEIN : msg;
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for taking \"" + s 
                    + "\" as an SSN";
            fail(msg);
        }
    }
    
//    @Test
    public void testNoNegativeSSNs() {
        try {
            SocialSecurityNumber badSSN = new SocialSecurityNumber(-1);
            String msg = "Should not have been able to create SSN " 
                    + badSSN.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to create SSN with negative number correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception for SSN with negative number";
            fail(msg);
        }
    }

//    @Test
    public void testNoArea773SSNs() {
        try {
            SocialSecurityNumber badSSN = new SocialSecurityNumber(773000000);
            String msg = "Should not have been able to create SSN " 
                    + badSSN.toString();
            fail(msg);
        } catch (IllegalArgumentException iae) {
            System.out.println("Trying to create SSN with area 773 correctly caused IllegalArgumentException");
            System.out.println("\"" + iae.getMessage() + "\"");
        } catch (RuntimeException re) {
            String msg = re.getClass().getName() 
                    + " is the wrong exception to throw for trying to create SSN with area 773";
            fail(msg);
        }
    }

}
