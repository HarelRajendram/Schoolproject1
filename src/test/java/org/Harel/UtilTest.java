package org.Harel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void toTitleCase1() {
        String input = "piraven sribala";
        String expected = "Piraven Sribala";

        String actual = Util.toTitleCase(input);

        assertEquals(expected,actual);
    }
    @Test
    void toTitleCase2() {
        String input = "carl santos";
        String expected = "Carl Santos";

        String actual = Util.toTitleCase(input);

        assertEquals(expected,actual);
    }
    @Test
    void toTitleCase3() {
        String input = "john singh";
        String expected = "John Singh";

        String actual = Util.toTitleCase(input);

        assertEquals(expected,actual);
    }
}