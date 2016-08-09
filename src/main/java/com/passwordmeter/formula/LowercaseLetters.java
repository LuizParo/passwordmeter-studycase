package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@Bonus(type = BonusType.ADDITION)
public class LowercaseLetters implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int lowercaseLetters = password.getQuantityOfLowercaseLetters();
        
        if(lowercaseLetters == 0) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int passwordLength = password.getPasswordLength();
        int bonus = (passwordLength - lowercaseLetters) * RATE;
        return new BonusResult(bonus, lowercaseLetters == 1 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL);
    }
}