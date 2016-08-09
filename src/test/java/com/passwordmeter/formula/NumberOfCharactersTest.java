package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class NumberOfCharactersTest {
    private static final int RATE = 4;
    private Formula numberOfCharacters = new NumberOfCharacters();
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.numberOfCharacters.calculateBonus(null);
    }
    
    @Test
    public void shouldCalculateBonusForPasswordWithOneCharacter() {
        Password password = new Password("1");
        BonusResult bonus = this.numberOfCharacters.calculateBonus(password);
        
        Assert.assertEquals(password.getPasswordLength() * RATE, bonus.getBonus());
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldCalculateBonusForPasswordWithOnlyWhiteSpaces() {
        Password password = new Password("               ");
        BonusResult bonus = this.numberOfCharacters.calculateBonus(password);
        
        Assert.assertEquals(password.getPasswordLength() * RATE, bonus.getBonus());
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldCalculateBonusForPasswordWithFiveCharacters() {
        Password password = new Password("12345");
        BonusResult bonus = this.numberOfCharacters.calculateBonus(password);
        
        Assert.assertEquals(password.getPasswordLength() * RATE, bonus.getBonus());
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldCalculateBonusForPasswordWithEightCharacters() {
        Password password = new Password("12345678");
        BonusResult bonus = this.numberOfCharacters.calculateBonus(password);
        
        Assert.assertEquals(password.getPasswordLength() * RATE, bonus.getBonus());
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldCalculateBonusForPasswordWithMoreThanEightCharacters() {
        Password password = new Password("123456789");
        BonusResult bonus = this.numberOfCharacters.calculateBonus(password);
        
        Assert.assertEquals(password.getPasswordLength() * RATE, bonus.getBonus());
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.numberOfCharacters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}