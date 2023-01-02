/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.idnumbers.EmployerIdentificationNumber;
import entities.idnumbers.SocialSecurityNumber;

/**
 *
 * @author Alonso del Arte
 */
public class ExampleEntities {
    
    private static final EmployerIdentificationNumber EXAMPLE_EIN 
            = new EmployerIdentificationNumber(123456789);
    
    public static final Organization EXAMPLE_BUSINESS_CUSTOMER 
            = new Organization("Woolworth", EXAMPLE_EIN);
    
    private static final SocialSecurityNumber EXAMPLE_SSN 
            = new SocialSecurityNumber(78051120);
    
    public static final Person EXAMPLE_CUSTOMER 
            = new Person("Hillary Schrader Whitcher", EXAMPLE_SSN);
    
}
