package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

public class Symbols implements Formula {
    private static final int RATE = 6;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int specialCharacters = password.getQuantityOfSpecialCharacters();
        
        if(specialCharacters == 0) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int bonus = specialCharacters * RATE;
        return new BonusResult(bonus, specialCharacters == 1 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL);
    }
}