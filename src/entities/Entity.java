/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.idnumbers.TaxpayerIdentificationNumber;

/**
 *
 * @author Alonso del Arte
 */
public class Entity {
    
    private String entityName;
    
    private final TaxpayerIdentificationNumber taxIDNum;
    
    Entity(String name, TaxpayerIdentificationNumber tin) {
        this.entityName = name;
        this.taxIDNum = tin;
    }
    
}
