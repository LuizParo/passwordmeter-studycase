package com.passwordmeter.formula;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

@Bonus(type = BonusType.ADDITION)
public class Requirements implements Formula {
    private static final int RATE = 2;
    private List<Formula> formulas = new ArrayList<>();

    public Requirements(List<Formula> formulas) {
        this.formulas = formulas;
    }

    @Override
    public BonusResult calculateBonus(Password password) {
        Objects.requireNonNull(password, "password cannot be null");
        
        int requirements = 0;
        for (Formula formula : this.formulas) {
            if(formula instanceof MiddleNumberOrSymbols) {
                continue;
            }
            
            BonusResult bonus = formula.calculateBonus(password);
            if(bonus.getStatus().isBetween(StatusValidation.SUFFICIENT, StatusValidation.EXCEPTIONAL)) {
                ++requirements;
            }
        }
        
        if(requirements < 4) {
            return new BonusResult(0, StatusValidation.FAILURE);
        }
        
        int bonus = requirements * RATE;
        return new BonusResult(bonus, requirements == 4 ? StatusValidation.SUFFICIENT : StatusValidation.EXCEPTIONAL);
    }
}