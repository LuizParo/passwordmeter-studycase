package com.passwordmeter.formula;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;

@FunctionalInterface
public interface Formula {

    public BonusResult calculateBonus(Password password);
}