package org.Harel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    /**
     * checks if a postcode is valid or not
     * @param postalCode is the postal code to verify
     * @return a boolean value whether the postal code is valid or not
     */
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";
        for (int i = 0; i < postalCode.length(); i++) {
            char s = postalCode.charAt(i);
            if (i % 2 == 0) {
                if (!letters.contains(String.valueOf(s).toUpperCase())) {
                    return false;
                }
            } else
            if (i % 2 != 0) {
                if (!numbers.contains(String.valueOf(s))) {
                    return false;
                }
            }
        }
        return true;
    }
    public Address (int streetNo, String street, String city,Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = 0;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
    }
    public enum Province {
        ON, QC, MB, BC, AB, NB, NS, PE, NL
    }
}
