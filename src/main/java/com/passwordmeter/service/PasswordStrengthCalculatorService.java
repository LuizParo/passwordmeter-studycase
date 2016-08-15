package com.passwordmeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordmeter.formula.Formula;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.PasswordStrengthCalculator;
import com.passwordmeter.model.ScoreResult;
import com.passwordmeter.util.FormulaUtil;
import com.passwordmeter.vo.ScoreResultVO;

/**
 * Service that receives the calls from controller to calculate the password strength and
 * delegates them to {@link PasswordStrengthCalculator}. After receiving the final calculus,
 * it encapsulates it in a {@link ScoreResultVO}.
 * 
 * @author Luiz Guilherme Paro
 *
 * @see Password
 * @see Formula
 * @see FormulaUtil
 * @see PasswordStrengthCalculator
 * @see ScoreResult
 * @see ScoreResultVO
 */
@Service
public class PasswordStrengthCalculatorService {

    /**
     * Attribute used to instantiate all {@link Formula}s objects defined in application.
     */
    @Autowired
    private FormulaUtil formulaUtil;
    
    /**
     * Method that encapsulates the raw password in a {@link Password} object and passes it to
     * {@link PasswordStrengthCalculator#calculateStrength(Password)} to calculate the password
     * strength.
     * 
     * @param rawPassword - The string password typed in the application's main page.
     * @return the result encapsulated in a {@link ScoreResultVO}.
     */
    public ScoreResultVO calculateStrength(String rawPassword) {
        Password password = new Password(rawPassword);
        
        PasswordStrengthCalculator passwordCalculator = new PasswordStrengthCalculator(this.formulaUtil.allFormulas());
        ScoreResult scoreResult = passwordCalculator.calculateStrength(password);
        
        return new ScoreResultVO(scoreResult.getStrength(), scoreResult.getComplexity().getAlias());
    }
}