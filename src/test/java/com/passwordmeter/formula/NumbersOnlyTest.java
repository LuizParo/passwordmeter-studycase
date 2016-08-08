package com.passwordmeter.formula;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NumbersOnlyTest {
    private Formula numbersOnly = new NumbersOnly();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.numbersOnly.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenPasswordIsNumbersOnly() {
        BonusResult bonus = this.numbersOnly.calculateBonus(new Password("123456789"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenPasswordIsNotNumbersOnly() {
        BonusResult bonus = this.numbersOnly.calculateBonus(new Password("123456789abc@"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenPasswordIsNumbersOnly() {
        BonusResult bonus = this.numbersOnly.calculateBonus(new Password("123456789"));
        Assert.assertEquals(-9, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenPasswordIsNotNumbersOnly() {
        BonusResult bonus = this.numbersOnly.calculateBonus(new Password("123456789abc@"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.numbersOnly.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}