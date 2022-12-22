/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currency;

/**
 *
 * @author Alonso del Arte
 */
public class CurrencyConversionNeededException 
        extends RuntimeException {
    
    private final String message;
    
    private final CurrencyAmount amountA, amountB;
    
    @Override
    public String getMessage() {
        return "Not implemented yet, sorry";
    }
    
    public CurrencyAmount getAmountA() {
        return this.amountA;
    }
    
    public CurrencyAmount getAmountB() {
        return this.amountB;
    }
    
    public CurrencyConversionNeededException
            (String msg, 
            CurrencyAmount amtA, 
            CurrencyAmount amtB) {
        this.message = msg;
        this.amountA = amtA;
        this.amountB = amtB;
    }
    
}
