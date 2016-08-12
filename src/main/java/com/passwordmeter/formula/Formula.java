package com.passwordmeter.formula;

import com.passwordmeter.model.BonusResult;
import com.passwordmeter.model.Password;
import com.passwordmeter.model.StatusValidation;

/**
 * Interface responsible for defining a contract for all {@link Formula}s in the application.
 * A {@link Formula} takes a {@link Password} object and calculates its bonus according to a
 * criteria defined in each implementation, like sequential letters, sequential numbers, etc.
 * 
 * @author Luiz Guilherme Paro
 */
@FunctionalInterface
public interface Formula {

    /**
     * Method that receives a {@link Password} and check its internal state to calculate
     * the bonus and status. The return consists in a {@link BonusResult} object containing
     * both informations.
     * 
     * @param password - The password that needs to be checked against the criteria
     * @return a {@link BonusResult} containing the calculus result.
     * 
     * @see BonusResult
     * @see StatusValidation
     */
    public BonusResult calculateBonus(Password password);
}