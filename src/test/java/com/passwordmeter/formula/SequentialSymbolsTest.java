package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class SequentialSymbolsTest {
    private Formula sequentialSymbols = new SequentialSymbols();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.sequentialSymbols.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoSequentialSymbols() {
        BonusResult bonus = this.sequentialSymbols.calculateBonus(new Password("ab123cde"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneSequentialSymbols() {
        BonusResult bonus = this.sequentialSymbols.calculateBonus(new Password("asdc!@#$23"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreSequentialSymbols() {
        BonusResult bonus = this.sequentialSymbols.calculateBonus(new Password("asdc!@#$23tgbnhy$#@"));
        Assert.assertEquals(-6, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoSequentialSymbols() {
        BonusResult bonus = this.sequentialSymbols.calculateBonus(new Password("!a@b#c$"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.sequentialSymbols.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}