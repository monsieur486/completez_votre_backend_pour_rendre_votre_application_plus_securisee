package com.nnk.springboot.tools;

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

    @Test
    void hasMinimumLength() {
        assertTrue(PasswordValidation.hasMinimumLength("pasSword1!"));
        assertFalse(PasswordValidation.hasMinimumLength("pasS1!"));
    }

    @Test
    void hasMaximumLength() {
        assertTrue(PasswordValidation.hasMaximumLength("pasSword1!"));
        assertFalse(PasswordValidation.hasMaximumLength("123456789012345678901234567890123456789012345678901"));
    }
}