package com.nnk.springboot.configuration.tools;

import com.nnk.springboot.tools.PasswordValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationTest {

    @Test
    void containsCapitalLetter() {
        assertTrue(PasswordValidation.containsCapitalLetter("pasSword"));
        assertFalse(PasswordValidation.containsCapitalLetter("password"));
    }

    @Test
    void containsLowercaseLetter() {
        assertTrue(PasswordValidation.containsLowercaseLetter("PASsWORD"));
        assertFalse(PasswordValidation.containsLowercaseLetter("PASSWORD"));
    }

    @Test
    void containsDigit() {
        assertTrue(PasswordValidation.containsDigit("pasSword1"));
        assertFalse(PasswordValidation.containsDigit("pasSword"));
    }

    @Test
    void containsSpecialCharacter() {
        assertTrue(PasswordValidation.containsSpecialCharacter("pasSword!"));
        assertFalse(PasswordValidation.containsSpecialCharacter("pasSword"));
    }
}