package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;
import com.passwordmeter.util.StringUtils;

public class ConsecutiveLowercaseLetters implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        String[] arrayPassword = password.getPasswordAsArray();
        
        int consecutiveLowercaseLetters = StringUtils.getNumberOfConsecutiveCharactersByPattern(arrayPassword, "[a-z]+");
        if(consecutiveLowercaseLetters == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(consecutiveLowercaseLetters * RATE), StatusValidation.WARNING);
    }
}