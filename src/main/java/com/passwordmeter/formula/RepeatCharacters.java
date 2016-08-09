package com.passwordmeter.formula;

import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@Bonus(type = BonusType.DEDUCTION)
public class RepeatCharacters implements Formula {

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        double repetedSequence = 0;
        int repetedCharacters = 0;
        int passwordLength = password.getPasswordLength();
        String[] arrayPassword = password.getPasswordAsArray();
        if(passwordLength > arrayPassword.length) {
            passwordLength = arrayPassword.length;
        }
        
        for(int i = 0; i < passwordLength; i++) {
            boolean bCharExists = false;
            for(int j = 0; j < passwordLength; j++) {
                if(arrayPassword[i].equals(arrayPassword[j]) && i != j) {
                    bCharExists = true;
                    repetedSequence += Math.abs(passwordLength / (j - i));
                }
            }
            
            if(bCharExists) {
                ++repetedCharacters;
                int unqChar = passwordLength - repetedCharacters;
                repetedSequence = unqChar != 0 ? Math.ceil(repetedSequence/unqChar) : Math.ceil(repetedSequence);
            }
        }
        
        if(repetedCharacters == 0) {
            return new BonusResult(0, StatusValidation.SUFFICIENT);
        }
        return new BonusResult(-(new Double(repetedSequence).intValue()), StatusValidation.WARNING);
    }
}