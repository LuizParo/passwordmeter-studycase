package com.passwordmeter.formula;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RequirementsTest {
    private Password password;
    private Formula requirements;
    private List<Formula> formulas;
    
    @Mock
    private Formula numberOfCharacters;
    
    @Mock
    private Formula uppercaseLetters;
    
    @Mock
    private Formula lowercaseLetters;
    
    @Mock
    private Formula numbers;
    
    @Mock
    private Formula symbols;
    
    @Before
    public void setUp() {
        this.password = new Password("");
        
        Mockito.when(this.numberOfCharacters.calculateBonus(this.password))
            .thenReturn(new BonusResult(8, StatusValidation.SUFFICIENT));

        Mockito.when(this.uppercaseLetters.calculateBonus(this.password))
            .thenReturn(new BonusResult(8, StatusValidation.EXCEPTIONAL));
        
        Mockito.when(this.lowercaseLetters.calculateBonus(this.password))
            .thenReturn(new BonusResult(8, StatusValidation.EXCEPTIONAL));
        
        Mockito.when(this.numbers.calculateBonus(this.password))
            .thenReturn(new BonusResult(8, StatusValidation.EXCEPTIONAL));
        
        Mockito.when(this.symbols.calculateBonus(this.password))
            .thenReturn(new BonusResult(8, StatusValidation.EXCEPTIONAL));
        
        this.formulas = Arrays.asList(this.numberOfCharacters,
            this.uppercaseLetters,
            this.lowercaseLetters,
            this.numbers,
            this.symbols);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        this.requirements.calculateBonus(null);
    }
    
    @Test
    public void shouldReturnFailureWhenNoRequirementsArePresent() {
        this.requirements = new Requirements(Collections.emptyList());
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnSufficientWhenFourRequirementsArePresent() {
        List<Formula> formulas = Arrays.asList(this.numberOfCharacters,
                this.uppercaseLetters,
                this.lowercaseLetters,
                this.numbers);
        
        this.requirements = new Requirements(formulas);
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(StatusValidation.SUFFICIENT, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnExceptionalWhenMoreThanFourRequirementsArePresent() {
        this.requirements = new Requirements(this.formulas);
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(StatusValidation.EXCEPTIONAL, bonus.getStatus());
    }
    
    @Test
    public void shouldNotConsiderMiddleNumberOrSymbolsARequirement() {
        Formula middleNumberOrSymbols = Mockito.mock(MiddleNumberOrSymbols.class);
        
        List<Formula> formulas = Arrays.asList(this.numberOfCharacters,
                this.uppercaseLetters,
                this.lowercaseLetters,
                middleNumberOrSymbols);
        
        this.requirements = new Requirements(formulas);
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(StatusValidation.FAILURE, bonus.getStatus());
    }
    
    @Test
    public void shouldReturnZeroBonusWhenNoRequirementsArePresent() {
        this.requirements = new Requirements(Collections.emptyList());
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(0, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenFourRequirementsArePresent() {
        List<Formula> formulas = Arrays.asList(this.numberOfCharacters,
                this.uppercaseLetters,
                this.lowercaseLetters,
                this.numbers);
        
        this.requirements = new Requirements(formulas);
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(8, bonus.getBonus());
    }
    
    @Test
    public void shouldCalculateBonusWhenMoreThanFourRequirementsArePresent() {
        this.requirements = new Requirements(this.formulas);
        BonusResult bonus = this.requirements.calculateBonus(this.password);
        Assert.assertEquals(10, bonus.getBonus());
    }
}