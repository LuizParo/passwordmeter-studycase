package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;
import com.passwordmeter.util.StringUtils;

public class SequentialLetters implements Formula {
    private static final int RATE = 3;
    private static final String SEQUENCE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        String stringPassword = password.getPasswordLowercase();
        int sequenceLetters = StringUtils.getNumberOfSequentialLettersFromSequence(stringPassword, SEQUENCE_ALPHABET);

        if(sequenceLetters == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(sequenceLetters * RATE), StatusValidation.WARNING);
    }
}