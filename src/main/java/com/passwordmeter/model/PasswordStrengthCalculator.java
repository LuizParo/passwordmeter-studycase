package com.passwordmeter.model;

import java.util.ArrayList;
import java.util.List;

import com.passwordmeter.formula.Formula;

/**
 * Class responsible for calculating the strength of a given password based on a list of
 * {@link Formula}s and return its strength percentage and complexity encapsulated in an
 * {@link ScoreResult}.
 * 
 * @author Luiz Guilherme Paro
 * 
 * @see Formula
 * @see Complexity
 * @see ScoreResult
 */
public class PasswordStrengthCalculator {
    private List<Formula> formulas = new ArrayList<>();
    
    public PasswordStrengthCalculator(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public ScoreResult calculateStrength(Password password) {
        int strength = 0;
        
        if(password.getPasswordLength() == 0) {
            return new ScoreResult(strength, Complexity.TOO_SHORT);
        }
        
        for (Formula formula : formulas) {
            BonusResult bonus = formula.calculateBonus(password);
            strength += bonus.getBonus();
        }
        
        return new ScoreResult(strength < 0 ? 0 : strength, this.calculteComplexity(strength));
    }

    private Complexity calculteComplexity(int strength) {
        if(strength < 20) {
            return Complexity.VERY_WEAK;
        }
        if(strength >= 20 && strength < 40) {
            return Complexity.WEAK;
        }
        if(strength >= 40 && strength < 60) {
            return Complexity.GOOD;
        }
        if(strength >= 60 && strength < 80) {
            return Complexity.STRONG;
        }
        return Complexity.VERY_STRONG;
    }
}