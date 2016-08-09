package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;
import com.passwordmeter.util.StringUtils;

@Bonus(type = BonusType.DEDUCTION)
public class SequentialNumbers implements Formula {
    private static final int RATE = 3;
    private static final String SEQUENCE_NUMBERS = "01234567890";

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");

        String stringPassword = password.getPasswordLowercase();
        int sequenceNumbers = StringUtils.getNumberOfSequentialLettersFromSequence(stringPassword, SEQUENCE_NUMBERS);
        
        if(sequenceNumbers == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(sequenceNumbers * RATE), StatusValidation.WARNING);
    }
}