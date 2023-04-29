/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.idnumbers;

import java.io.Serializable;

/**
 *
 * @author Alonso del Arte
 */
public abstract class TaxpayerIdentificationNumber implements Serializable {
    
    private final long idNumber;

    TaxpayerIdentificationNumber(long number) {
        this.idNumber = number;
    }

}
