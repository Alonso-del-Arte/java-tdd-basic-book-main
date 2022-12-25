/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.idnumbers.EmployerIdentificationNumber;

/**
 *
 * @author Alonso del Arte
 */
public class Organization extends Entity {
    
    public Organization(String name, EmployerIdentificationNumber ein) {
        super(name, ein);
    }
    
}
