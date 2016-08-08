package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class LowercaseLettersTest {
    private Formula lowercaseLetters = new LowercaseLetters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.lowercaseLetters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoLowerLettersArePresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("ABC"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenOneLowercaseLetterIsPresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("aBCD"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanOneLowercaseLetterIsPresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("abcDfG"));
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenOnlyUppercaseLettersArePresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("ABC"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenOnlyLowercaseLettersArePresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("abc"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenUpperAndLowercasesArePresent() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("abcDefGHiJk"));
        Assert.assertEquals(8, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenUpperAndLowercasesArePresentAlongsideNumbers() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password("abcDefGHiJk123"));
        Assert.assertEquals(14, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.lowercaseLetters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}