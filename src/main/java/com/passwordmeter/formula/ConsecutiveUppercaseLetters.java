package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;
import com.passwordmeter.util.StringUtils;

public class ConsecutiveUppercaseLetters implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        String[] arrayPassword = password.getPasswordAsArray();
        int consecutiveUppercaseLetters = StringUtils.getNumberOfConsecutiveCharactersByPattern(arrayPassword, "[A-Z]+");
        
        if(consecutiveUppercaseLetters == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(consecutiveUppercaseLetters * RATE), StatusValidation.WARNING);
    }
}