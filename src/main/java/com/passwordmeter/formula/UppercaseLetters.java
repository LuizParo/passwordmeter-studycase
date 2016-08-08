package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class UppercaseLetters implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int uppercaseLetters = password.getQuantityOfUppercaseLetters();
        
        if(uppercaseLetters == 0) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int passwordLength = password.getPasswordLength();
        int bonus = (passwordLength - uppercaseLetters) * RATE;
        return new BonusResult(bonus, uppercaseLetters == 1 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL);
    }
}