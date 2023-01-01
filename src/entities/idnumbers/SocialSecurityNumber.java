/*
 * Copyright (C) 2022 Alonso del Arte
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

import java.text.DecimalFormat;

/**
 *
 * @author Alonso del Arte
 */
public class SocialSecurityNumber extends TaxpayerIdentificationNumber {
    
    private static final long serialVersionUID = 4549586048367213363L;

    public static final String AREA_NUMBER_FORMAT = "000";
    public static final String GROUP_NUMBER_FORMAT = "00";
    public static final String SERIAL_NUMBER_FORMAT = "0000";

    private static final DecimalFormat AREA_NUMBER_FORMATTER 
            = new DecimalFormat(AREA_NUMBER_FORMAT);
    private static final DecimalFormat GROUP_NUMBER_FORMATTER 
            = new DecimalFormat(GROUP_NUMBER_FORMAT);
    private static final DecimalFormat SERIAL_NUMBER_FORMATTER 
            = new DecimalFormat(SERIAL_NUMBER_FORMAT);

    private final int areaNumber;
    private final int groupNumber;
    private final int serialNumber;

    private final String ssnWDashes;
    private final String lastFour;
    
    // TODO: Determine if there's a way to do without this redundancy
    private final long redundantNumberHold;
    
    // TODO: Write tests for this
    public boolean matchesLast4(int partial) {
        return false;
    }
    
    // TODO: Write tests for this
    public boolean matchesLast4(String numberText) {
        return false;
    }
    
    static boolean correctSSNDashPlacement(String s) {
        return (s.indexOf('-') == 3) && (s.indexOf('-', 4) == 6) 
                && (s.indexOf('-', 7) == -1);
    }
    
    public static SocialSecurityNumber parseSSN(String s) {
        if (!correctSSNDashPlacement(s)) {
            String excMsg;
            if (EmployerIdentificationNumber.correctEINDashPlacement(s)) {
                excMsg = "Input \"" + s 
                        + "\" is Employer Identification Number (EIN), not SSN";
            } else {
                excMsg = "Input \"" + s + "\" is not a properly formatted SSN";
            }
            throw new NumberFormatException(excMsg);
        }
        String dashesOff = s.replace("-", "");
        int num = Integer.parseInt(dashesOff);
        return new SocialSecurityNumber(num);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!this.getClass().equals(obj.getClass())) {
            return false;
        }
        return this.redundantNumberHold 
                == ((SocialSecurityNumber) obj).redundantNumberHold;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.redundantNumberHold 
                ^ (this.redundantNumberHold >>> 32));
        return hash;
    }
    
    @Override
    public String toString() {
        return this.ssnWDashes;
    }

    public String toRedactedString() {
        return "***-**-" + this.lastFour;
    }
    
    public SocialSecurityNumber(long number) {
        super(number);
        if (number < 0) {
            String excMsg = "Number " + number + " is negative, not positive";
            throw new IllegalArgumentException(excMsg);
        }
        if (number > 772999999) {
            String excMsg = "Area 773 or higher is not valid for SSN";
            throw new IllegalArgumentException(excMsg);
        }
        int intermediate = ((int) number) / 10000;
        this.areaNumber = intermediate / 100;
        this.groupNumber = intermediate % 100;
        this.serialNumber = ((int) number) % 10000;
        this.lastFour = SERIAL_NUMBER_FORMATTER.format(this.serialNumber);
        String areaNumStr = AREA_NUMBER_FORMATTER.format(this.areaNumber);
        String groupNumStr = GROUP_NUMBER_FORMATTER.format(this.groupNumber);
        this.ssnWDashes = areaNumStr + "-" + groupNumStr + "-" + this.lastFour;
        this.redundantNumberHold = number;
    }
    
}
