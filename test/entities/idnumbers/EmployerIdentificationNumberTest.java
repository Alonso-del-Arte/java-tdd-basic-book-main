/*
 * Copyright (C) 2020 Alonso del Arte
 *
 * This program is free software; you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free Software 
 * Foundation; either version 2 of the License, or (at your option) any later 
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License along with 
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple 
 * Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package entities.idnumbers;

import org.junit.Test;

/**
 * Tests of the EmployerIdentificationNumber class.
 * @author Alonso del Arte
 */
public class EmployerIdentificationNumberTest {
    
    /**
     * Test of correctEINDashPlacement function, of class 
     * EmployerIdentificationNumber.
     */
    @Test
    public void testCorrectEINDashPlacement() {
        System.out.println("correctEINDashPlacement");
        String s = "12-3456789";
        String msg = "\"" + s + "\" has correct EIN dash placement";
        assert EmployerIdentificationNumber.correctEINDashPlacement(s) : msg;
    }
    
    /**
     * Another test of correctEINDashPlacement function, of class 
     * EmployerIdentificationNumber.
     */
    @Test
    public void testIncorrectEINDashPlacement() {
        String s = "123-456-789";
        String msg = "\"" + s + "\" does not have correct EIN dash placement";
        assert !EmployerIdentificationNumber.correctEINDashPlacement(s) : msg;
    }
    
}
