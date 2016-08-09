package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class MiddleNumberOrSymbolsTest {
    private Formula middleNumberOrSymbols = new MiddleNumberOrSymbols();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.middleNumberOrSymbols.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoDigitsOrSymbolsArePresent() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("abc"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnFailureWhenDigitsOrSymbolsArePresentInTheBounds() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("1abc@"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenOneDigitOrSymbolIsPresent() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("abc@d"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanOneDigitOrSymbolIsPresent() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("abcDfG123@4$%"));
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenNoDigitsOrSymbolsArePresent() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("abcDfG"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenNoDigitsAndSymbolsArePresentInTheMiddle() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("1abcDfG@"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenDigitsOrSymbolsAreSolo() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("@#%&$"));
        Assert.assertEquals(6, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenDigitsAndSymbolsArePresentBetweenOtherCharacters() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password("aBCdEfGHi123Jkl@#%&_$"));
        Assert.assertEquals(14, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.middleNumberOrSymbols.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}