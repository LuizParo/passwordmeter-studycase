package com.passwordmeter.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.passwordmeter.util.FormulaUtil;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordStrengthCalculatorTest {
    private PasswordStrengthCalculator passwordStrengthCalculator;
    
    @Autowired
    private FormulaUtil formulaUtil;
    
    @Before
    public void setUp() {
        this.passwordStrengthCalculator = new PasswordStrengthCalculator(this.formulaUtil.allFormulas());
    }
    
    @Test
    public void shouldMarkComplexityTooShortWhenNoPasswordIsGiven() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password(""));
        Assert.assertEquals(Complexity.TOO_SHORT, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityVeryWeakWhenScoreIsBetweenZeroAndNineteen() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("shortpass"));
        Assert.assertEquals(Complexity.VERY_WEAK, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityWeakWhenScoreIsBetweenTewntyAndThirtyNine() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("abcde852"));
        Assert.assertEquals(Complexity.WEAK, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityGoodWhenScoreIsBetweenFourtyAndFiftyNine() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("abcde852@"));
        Assert.assertEquals(Complexity.GOOD, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityStrongWhenScoreIsBetweenSixtyAndSeventyNine() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("abcde852@#"));
        Assert.assertEquals(Complexity.STRONG, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityVeryStrongWhenScoreIsBetweenEightyAndOneHundred() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("abcde852@#L"));
        Assert.assertEquals(Complexity.VERY_STRONG, scoreResult.getComplexity());
    }
    
    @Test
    public void shouldMarkComplexityVeryStrongWhenScoreSurpassesOneHundred() {
        ScoreResult scoreResult = this.passwordStrengthCalculator.calculateStrength(new Password("abcde852@#L987"));
        Assert.assertEquals(Complexity.VERY_STRONG, scoreResult.getComplexity());
    }

    @Test
    public void shouldCalculateStrengthOfPasswordCorrectly() {
        Password password = new Password("P@55w0rd");
        ScoreResult result = this.passwordStrengthCalculator.calculateStrength(password);
        
        Assert.assertEquals(86, result.getStrength());
    }
    
    @Test
    public void shouldCalculateStrengthOfPasswordCorrectlyWhenItHasWhiteSpaces() {
        Password password = new Password("              @");
        ScoreResult result = this.passwordStrengthCalculator.calculateStrength(password);
        
        Assert.assertEquals(66, result.getStrength());
    }
    
    @Test
    public void shouldCalculateZeroBonusForLongSequentialPasswords() {
        Password password = new Password("abcdefghijklmnopqrstuvwxyz");
        ScoreResult result = this.passwordStrengthCalculator.calculateStrength(password);
        
        Assert.assertEquals(0, result.getStrength());
        Assert.assertEquals(Complexity.VERY_WEAK, result.getComplexity());
    }
}