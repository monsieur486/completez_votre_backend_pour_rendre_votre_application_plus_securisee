package com.nnk.springboot.tools;

import com.nnk.springboot.configuration.ApplicationConfiguration;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidation {

    public PasswordValidation() {
    }

    /**
     * Checks if the given string contains a capital letter.
     *
     * @param string the string to check
     * @return true if the string contains a capital letter, false otherwise
     */
    public static boolean containsCapitalLetter(String string) {
        return string.matches(".*[A-Z].*");
    }

    /**
     * Checks if the given string contains a lowercase letter.
     *
     * @param string the string to check
     * @return true if the string contains a lowercase letter, false otherwise
     */
    public static boolean containsLowercaseLetter(String string) {
        return string.matches(".*[a-z].*");
    }

    /**
     * Checks if the given string contains a digit.
     *
     * @param string the string to check
     * @return true if the string contains a digit, false otherwise
     */
    public static boolean containsDigit(String string) {
        return string.matches(".*\\d.*");
    }

    /**
     * Checks if the given string contains a special character.
     *
     * @param string the string to check
     * @return true if the string contains a special character, false otherwise
     */
    public static boolean containsSpecialCharacter(String string) {
        return string.matches(".*[^a-zA-Z0-9].*");
    }

    /**
     * Checks if the given string has a minimum length.
     *
     * @param string the string to check
     * @return true if the string has a minimum length, false otherwise
     */
    public static boolean hasMinimumLength(String string) {
        return string.length() >= ApplicationConfiguration.MINIMUM_PASSWORD_LENGTH;
    }

    /**
     * Checks if the given string has a maximum length.
     *
     * @param string the string to check
     * @return true if the string has a maximum length, false otherwise
     */
    public static boolean hasMaximumLength(String string) {
        return string.length() <= ApplicationConfiguration.MAXIMUM_PASSWORD_LENGTH;
    }

}
