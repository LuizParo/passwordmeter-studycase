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
public class ConsecutiveUppercaseLettersTest {
    private Formula consecutiveUppercaseLetters = new ConsecutiveUppercaseLetters();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.consecutiveUppercaseLetters.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnSufficientWhenThereAreNoUppercaseLetterIsInSequence() {
        BonusResult bonus = this.consecutiveUppercaseLetters.calculateBonus(new Password("abcDeFgH"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneUppercaseLetterIsInSequence() {
        BonusResult bonus = this.consecutiveUppercaseLetters.calculateBonus(new Password("abcDeFGh"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreUppercaseLettersInSequence() {
        BonusResult bonus = this.consecutiveUppercaseLetters.calculateBonus(new Password("abcDeFGhIJKLM"));
        Assert.assertEquals(-10, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoUppercaseLettersInSequence() {
        BonusResult bonus = this.consecutiveUppercaseLetters.calculateBonus(new Password("abcDeFgHiJkLm"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.consecutiveUppercaseLetters.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}