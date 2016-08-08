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
public class LettersOnlyTest {
    private Formula lettersOnly = new LettersOnly();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.lettersOnly.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenPasswordIsLettersOnly() {
        BonusResult bonus = this.lettersOnly.calculateBonus(new Password("abcdefghij__"));
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenPasswordIsNotLettersOnly() {
        BonusResult bonus = this.lettersOnly.calculateBonus(new Password("abcdefghij__@123"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenPasswordIsLettersOnly() {
        BonusResult bonus = this.lettersOnly.calculateBonus(new Password("abcdefghij__"));
        Assert.assertEquals(-12, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenPasswordIsNotLettersOnly() {
        BonusResult bonus = this.lettersOnly.calculateBonus(new Password("abcdefghij__@123"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.lettersOnly.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}