/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.transactions;

import currency.CurrencyAmount;

import java.time.LocalDateTime;
import java.util.Currency;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests of the Comment class.
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
    
    @Test
    public void testReferentialEquality() {
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        assertEquals(comment, comment);
    }
    
    @Test
    public void testNotEqualsNull() {
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        assertNotEquals(comment, null);
    }
    
    @Test
    public void testNotEqualsDiffClass() {
        Comment comment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        Transaction nonCommentTrx = TransactionTest.makeTransaction();
        assertNotEquals(comment, nonCommentTrx);
    }
    
    @Test
    public void testNotEqualsDiffCurrency() {
        Comment commentA = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        Comment commentB = new Comment(Currency.getInstance("EUR"), 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        assertNotEquals(commentA, commentB);
    }
    
    @Test
    public void testEquals() {
        System.out.println("equals");
        Comment someComment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        Comment sameComment = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        assertEquals(someComment, sameComment);
    }
    
    @Test
    public void testNotEqualsDiffDate() {
        Comment commentA = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now(), DEFAULT_DESCRIPTION);
        Comment commentB = new Comment(TransactionTest.DOLLARS, 
                LocalDateTime.now().minusDays(1), DEFAULT_DESCRIPTION);
        assertNotEquals(commentA, commentB);
    }
    
}
