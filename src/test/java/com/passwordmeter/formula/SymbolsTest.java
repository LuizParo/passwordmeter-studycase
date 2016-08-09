package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class SymbolsTest {
    private Formula symbols = new Symbols();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.symbols.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoSymbolsArePresent() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("abc"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenOneSymbolIsPresent() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("abc@"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanOneSymbolIsPresent() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("abcDfG123@4$%"));
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenNoSymbolsArePresent() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("abcDfG"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenSymbolsAreSolo() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("@#%&$"));
        Assert.assertEquals(30, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenSymbolsArePresentBetweenOtherCharacters() {
        BonusResult bonus = this.symbols.calculateBonus(new Password("aBCdEfGHi123Jkl@#%&_$"));
        Assert.assertEquals(30, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.symbols.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}