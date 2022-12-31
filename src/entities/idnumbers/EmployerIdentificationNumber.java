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
public class EmployerIdentificationNumber extends TaxpayerIdentificationNumber {
    
    // TODO: Write tests for this
    public static boolean correctEINDashPlacement(String s) {
        return false;
    }
    
    public EmployerIdentificationNumber(long number) {
        super(number);
    }
    
}
