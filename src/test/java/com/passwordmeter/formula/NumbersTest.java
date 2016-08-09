package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class NumbersTest {
    private Formula numbers = new Numbers();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.numbers.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoDigitsArePresent() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("ABC"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenOneDigitIsPresent() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("aBCD1"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanOneDigitIsPresent() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("abcDfG123"));
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenNoNumbersArePresent() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("abcDfG"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenOnlyNumbersArePresent() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("123456"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenDigitsArePresentBetweenOtherCharacters() {
        BonusResult bonus = this.numbers.calculateBonus(new Password("abcd12345EFGH"));
        Assert.assertEquals(20, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.numbers.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}