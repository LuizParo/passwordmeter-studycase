package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class RepeatCharactersTest {
    private Formula repeatCharacters = new RepeatCharacters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.repeatCharacters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoSequentialSymbols() {
        BonusResult bonus = this.repeatCharacters.calculateBonus(new Password("abc123"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneSequentialSymbols() {
        BonusResult bonus = this.repeatCharacters.calculateBonus(new Password("abc123abc"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreRepetedCharacters() {
        BonusResult bonus = this.repeatCharacters.calculateBonus(new Password("   abc123abc456abc"));
        Assert.assertEquals(-1, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoRepetedCharacters() {
        BonusResult bonus = this.repeatCharacters.calculateBonus(new Password("  !a@b#c$"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.repeatCharacters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}