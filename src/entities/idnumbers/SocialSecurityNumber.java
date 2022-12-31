/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.idnumbers;

/**
 *
 * @author Alonso del Arte
 */
public class SocialSecurityNumber extends TaxpayerIdentificationNumber {
    
    // TODO: Write tests for this
    public String toRedactedString() {
        return "NOT IMPLEMENTED YET";
    }
    
    // TODO: Write tests for this
    public boolean matchesLast4(int partial) {
        return false;
    }
    
    // TODO: Write tests for this
    public boolean matchesLast4(String numberText) {
        return false;
    }
    
    // TODO: Write tests for this
    static boolean correctSSNDashPlacement(String s) {
        return false;
    }

    public SocialSecurityNumber(long number) {
        super(number);
    }
    
}
