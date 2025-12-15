package org.Harel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    @DisplayName("Valid postal code: alternating letter-digit pattern")
    void isPostalCodeValid() {
        assertTrue(Address.isPostalCodeValid("A1B2C3"));
        assertTrue(Address.isPostalCodeValid("m5v3l9"));
    }

    @Test
    @DisplayName("Invalid postal code: null or wrong length")
    void testInvalidPostalCodeLength1() {
        assertFalse(Address.isPostalCodeValid(null));
        assertFalse(Address.isPostalCodeValid(""));
        assertFalse(Address.isPostalCodeValid("A1B2C"));
    }
    @Test
    @DisplayName("Invalid postal code: null or wrong length")
    void testInvalidPostalCodeLength2() {
        assertFalse(Address.isPostalCodeValid("676767"));
        assertFalse(Address.isPostalCodeValid("BBBBBBBBBBBBB"));
        assertFalse(Address.isPostalCodeValid("OpoP@#"));
    }
}