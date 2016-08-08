package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class SequentialLettersTest {
    private Formula sequentialLetters = new SequentialLetters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.sequentialLetters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoSequentialLetters() {
        BonusResult bonus = this.sequentialLetters.calculateBonus(new Password("ab12cd"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneSequentialLetters() {
        BonusResult bonus = this.sequentialLetters.calculateBonus(new Password("ABCdEFGh"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreSequentialLetters() {
        BonusResult bonus = this.sequentialLetters.calculateBonus(new Password("ABCdEFGhCBA"));
        Assert.assertEquals(-18, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoSequentialLetters() {
        BonusResult bonus = this.sequentialLetters.calculateBonus(new Password("ab12cd34EF56"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.sequentialLetters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}