/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.idnumbers.SocialSecurityNumber;

/**
 *
 * @author Alonso del Arte
 */
public class Person extends Entity {
    
    public Person(String name, SocialSecurityNumber ssn) {
        super(name, ssn);
    }
    
}
