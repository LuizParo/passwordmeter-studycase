package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;
import com.passwordmeter.util.StringUtils;

@Bonus(type = BonusType.DEDUCTION)
public class ConsecutiveNumbers implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");

        String[] arrayPassword = password.getPasswordAsArray();
        int consecutiveNumbers = StringUtils.getNumberOfConsecutiveCharactersByPattern(arrayPassword, "[\\d]+");
        
        if(consecutiveNumbers == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(consecutiveNumbers * RATE), StatusValidation.WARNING);
    }
}