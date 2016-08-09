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
public class ConsecutiveNumbersTest {
    private Formula consecutiveNumbers = new ConsecutiveNumbers();

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.consecutiveNumbers.calculateBonus(null);
    }

    @Test
    public void shouldReturnSufficientWhenThereAreNoNumbersIsInSequence() {
        BonusResult bonus = this.consecutiveNumbers.calculateBonus(new Password("abc!@#$"));
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnWarningWhenMoreThanOneNumberIsInSequence() {
        BonusResult bonus = this.consecutiveNumbers.calculateBonus(new Password("abc1234"));
        Assert.assertEquals(StatusValidation.WARNING, bonus.getStatus());
    }
    
    @Test
    public void shouldReduceBonusWhenThereAreNumbersInSequence() {
        BonusResult bonus = this.consecutiveNumbers.calculateBonus(new Password("abc1234defg567"));
        Assert.assertEquals(-10, bonus.getBonus());
    }
    
    @Test
    public void shouldNotReduceBonusWhenThereAreNoNumbersInSequence() {
        BonusResult bonus = this.consecutiveNumbers.calculateBonus(new Password("abc!@#$"));
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldNotChangeBonusWhenPasswordIsBlank() {
        BonusResult bonus = this.consecutiveNumbers.calculateBonus(new Password(""));
        Assert.assertEquals(0, bonus.getBonus());
    }
}