package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class Numbers implements Formula {
    private static final int RATE = 4;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int digits = password.getQuantityOfDigits();

        if(digits == 0) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int passwordLength = password.getPasswordLength();
        StatusValidation status = digits == 1 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL;
        if ((digits - passwordLength) == 0) {
            return new BonusResult(0, status);
        }
        
        int bonus = digits * RATE;
        return new BonusResult(bonus, status);
    }
}