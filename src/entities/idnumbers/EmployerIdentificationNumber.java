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
    
    private static final long serialVersionUID = 4549294527461997363L;
    
    public static boolean correctEINDashPlacement(String s) {
        return s.indexOf('-') == 2 && s.indexOf('-', 3) < 0;
    }
    
    public EmployerIdentificationNumber(long number) {
        super(number);
    }
    
}
