package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class NumbersOnly implements Formula {

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        if(password.isNumbersOnly()) {
            return new BonusResult(-(password.getPasswordLength()), StatusValidation.FAILURE);
        }
        return new BonusResult(0, StatusValidation.SUFFICIENT);
    }
}