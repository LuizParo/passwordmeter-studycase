package com.passwordmeter.util;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.Password;

public class StringUtilsTest {

    @Test
    public void shouldCountTheQuantityOfConsecutiveLowercaseLettersInAString() {
        Password password = new Password("abcDefgHIJKLM");
        String[] passwordAsArray = password.getPasswordAsArray();
        int consecutiveCharacters = StringUtils.getNumberOfConsecutiveCharactersByPattern(passwordAsArray, "[a-z]+");
        Assert.assertEquals(4, consecutiveCharacters);
    }
    
    @Test
    public void shouldCountTheQuantityOfConsecutiveNumbersInAString() {
        Password password = new Password("abc1234defg567");
        String[] passwordAsArray = password.getPasswordAsArray();
        int consecutiveCharacters = StringUtils.getNumberOfConsecutiveCharactersByPattern(passwordAsArray, "[\\d]+");
        Assert.assertEquals(5, consecutiveCharacters);
    }
    
    @Test
    public void shouldCountTheQuantityOfSequentialLowercaseLettersInAString() {
        Password password = new Password("ABCdEFGhCBA");
        int consecutiveCharacters = StringUtils.getNumberOfSequentialLettersFromSequence(password.getPasswordLowercase(), "abcdefghijklmnopqrstuvwxyz");
        Assert.assertEquals(6, consecutiveCharacters);
    }
    
    @Test
    public void shouldCountTheQuantityOfSequentialNumbersInAString() {
        Password password = new Password("abc1234utr987");
        int consecutiveCharacters = StringUtils.getNumberOfSequentialLettersFromSequence(password.getPasswordLowercase(), "01234567890");
        Assert.assertEquals(3, consecutiveCharacters);
    }
}