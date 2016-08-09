package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@Bonus(type = BonusType.ADDITION)
public class MiddleNumberOrSymbols implements Formula {
    private static final int RATE = 2;

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        int characters = password.getQuantityOfDigitsInMiddle() + password.getQuantityOfSpecialCharactersInMiddle();
        
        if(characters == 0) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int bonus = characters * RATE;
        return new BonusResult(bonus, characters == 1 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL);
    }
}