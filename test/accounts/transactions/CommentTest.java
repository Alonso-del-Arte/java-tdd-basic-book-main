/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alonso del Arte
 */
public class CommentTest {
    
    private static final String DEFAULT_DESCRIPTION 
            = "For testing purposes only";
    
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        CurrencyAmount expected = new CurrencyAmount(0, 
                TransactionTest.DOLLARS);
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        CurrencyAmount actual = comment.getAmount();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        LocalDateTime expected = LocalDateTime.now()
                .minusHours(TransactionTest.RANDOM.nextInt(72) + 1);
        Comment comment = new Comment(TransactionTest.DOLLARS, expected, 
                DEFAULT_DESCRIPTION);
        LocalDateTime actual = comment.getTime();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        String expected = "Comment: " + DEFAULT_DESCRIPTION;
        String actual = comment.getDescription();
        assertEquals(expected, actual);
    }
    
    @Test
    public void testToString() {
        System.out.println("toString");
        int points = TransactionTest.RANDOM.nextInt(1000);
        String desc = points + " Rewards Points awarded";
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), desc);
        String expected = "Comment: " + desc;
        String actual = comment.toString();
        assertEquals(expected, actual);
    }
    
}
