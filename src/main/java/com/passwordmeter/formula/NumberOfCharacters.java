package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@Bonus(type = BonusType.ADDITION)
public class NumberOfCharacters implements Formula {
    private static final int RATE = 4;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int length = password.getPasswordLength();
        
        if(length < 8) {
            return new BonusResult(length * RATE, StatusValidation.FAILURE) ;
        }
        return new BonusResult(length * RATE, length == 8 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL) ;
    }
}