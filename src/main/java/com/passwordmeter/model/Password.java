package com.passwordmeter.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class that represents the password in application. It encapsulates
 * the {@link String} password typed on the main page and provides useful
 * methods for it.
 * 
 * @author Luiz Guilherme Paro
 *
 */
public final class Password {

    /**
     * The string containing the typed password.
     */
    private final String password;

    /**
     * Constructor that receives the {@link String} password.
     * 
     * @param password - {@link String} representing the password
     * @throws NullPointerException if password is null
     */
    public Password(String password) {
        this.password = Objects.requireNonNull(password, "password cannot be null");
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getPasswordLowercase() {
        return password.toLowerCase();
    }
    
    public int getPasswordLength() {
        return this.getPassword().length();
    }
    
    public String[] getPasswordAsArray() {
        return this.password.trim().split("\\s*");
    }
    
    public int getQuantityOfUppercaseLetters() {
        int quantity = 0;
        
        for (char character : this.password.toCharArray()) {
            if(Character.isUpperCase(character)) {
                ++quantity;
            }
        }
        return quantity;
    }
    
    public int getQuantityOfLowercaseLetters() {
        int quantity = 0;
        for (char character : this.password.toCharArray()) {
            if(Character.isLowerCase(character)) {
                ++quantity;
            }
        }
        return quantity;
    }
    
    public int getQuantityOfSpecialCharacters() {
        return this.getQuantityOfSpecialCharacters0(this.password);
    }
    
    public int getQuantityOfSpecialCharactersInMiddle() {
        String passwordWithoutBounds = this.extractPasswordWithoutBounds();
        return this.getQuantityOfSpecialCharacters0(passwordWithoutBounds);
    }
    
    private int getQuantityOfSpecialCharacters0(String password) {
        int quantity = 0;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9_\\s]+");
        
        for (char character : password.toCharArray()) {
            Matcher matcher = pattern.matcher(String.valueOf(character));
            if(matcher.matches()) {
                ++quantity;
            }
        }
        return quantity;
    }
    
    public int getQuantityOfDigits() {
        return this.getQuantityOfDigits0(this.password);
    }
    
    public int getQuantityOfDigitsInMiddle() {
        String passwordWithoutBounds = this.extractPasswordWithoutBounds();
        return this.getQuantityOfDigits0(passwordWithoutBounds);
    }

    private String extractPasswordWithoutBounds() {
        int length = this.getPasswordLength();
        
        if(length == 0) {
            return "";
        }
        
        if(length == 1) {
            return new StringBuilder(this.password)
                    .deleteCharAt(0)
                    .toString();
        }
        
        return new StringBuilder(this.password)
                .deleteCharAt(0)
                .deleteCharAt(length - 2)
                .toString();
    }
    
    private int getQuantityOfDigits0(String password) {
        int quantity = 0;
        for (char character : password.toCharArray()) {
            if(Character.isDigit(character)) {
                ++quantity;
            }
        }
        return quantity;
    }
    
    public boolean isLettersOnly() {
        return this.password.matches("[a-zA-Z_]+");
    }
    
    public boolean isNumbersOnly() {
        return this.password.matches("[\\d]+");
    }
    
    public boolean isPasswordEmpty() {
        return this.getPasswordLength() == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Password))
            return false;
        Password other = (Password) obj;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Password [password=" + password + "]";
    }
}