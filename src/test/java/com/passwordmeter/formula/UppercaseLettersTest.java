package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class UppercaseLettersTest {
    private Formula uppercaseLetters = new UppercaseLetters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.uppercaseLetters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoUppercaseLettersArePresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abc"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenOneUppercaseLetterIsPresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abcD"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanOneUppercaseLetterIsPresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abcDfG"));
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenOnlyUppercaseLettersArePresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("ABC"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenOnlyLowercaseLettersArePresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abc"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenUpperAndLowercasesArePresent() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abcDefGHiJk"));
        Assert.assertEquals(14, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenUpperAndLowercasesArePresentAlongsideNumbers() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password("abcDefGHiJk123"));
        Assert.assertEquals(20, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.uppercaseLetters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}