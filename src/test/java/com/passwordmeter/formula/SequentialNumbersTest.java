package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class SequentialNumbersTest {
    private Formula sequentialNumbers = new SequentialNumbers();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.sequentialNumbers.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoSequentialNumbers() {
        BonusResult bonus = this.sequentialNumbers.calculateBonus(new Password("ab12cd"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneSequentialNumbers() {
        BonusResult bonus = this.sequentialNumbers.calculateBonus(new Password("123abc456"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreSequentialNumbers() {
        BonusResult bonus = this.sequentialNumbers.calculateBonus(new Password("abc1234utr987"));
        Assert.assertEquals(-9, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoSequentialNumbers() {
        BonusResult bonus = this.sequentialNumbers.calculateBonus(new Password("ab12cd34EF56"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.sequentialNumbers.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}