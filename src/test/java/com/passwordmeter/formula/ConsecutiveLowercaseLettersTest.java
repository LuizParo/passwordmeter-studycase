package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class ConsecutiveLowercaseLettersTest {
    private Formula consecutiveLowercaseLetters = new ConsecutiveLowercaseLetters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.consecutiveLowercaseLetters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoLowercaseLetterIsInSequence() {
        BonusResult bonus = this.consecutiveLowercaseLetters.calculateBonus(new Password("ABCdEFG"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneLowercaseLetterIsInSequence() {
        BonusResult bonus = this.consecutiveLowercaseLetters.calculateBonus(new Password("abcDefg"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreLowercaseLettersInSequence() {
        BonusResult bonus = this.consecutiveLowercaseLetters.calculateBonus(new Password("abcDefgHIJKLM"));
        Assert.assertEquals(-8, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoLowercaseLettersInSequence() {
        BonusResult bonus = this.consecutiveLowercaseLetters.calculateBonus(new Password("ABCDEFGHIJKLM"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.consecutiveLowercaseLetters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}