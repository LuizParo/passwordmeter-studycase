package com.passwordmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordmeter.formula.FormulaUtil;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.PasswordStrengthCalculator;
import com.passwordmeter.model.ScoreResult;
import com.passwordmeter.vo.ScoreResultVO;

@Service
public class PasswordStrengthCalculatorService {

    @Autowired
    private FormulaUtil formulaUtil;
    
    public ScoreResultVO calculateStrength(String rawPassword) {
        Password password = new Password(rawPassword);
        
        PasswordStrengthCalculator passwordCalculator = new PasswordStrengthCalculator(this.formulaUtil.allFormulas());
        ScoreResult scoreResult = passwordCalculator.calculateStrength(password);
        
        return new ScoreResultVO(scoreResult.getStrength(), scoreResult.getComplexity().getAlias());
    }
}