package com.passwordmeter.model;

import java.util.Objects;

import com.passwordmeter.formula.Formula;

/**
 * Immutable class that encapsulates the attributes bonus and status
 * returned in a {@link Formula}'s calculation. Their results are part
 * of the total password strength.
 * 
 * @author Luiz Guilherme Paro
 * 
 * @see Formula
 * @see StatusValidation
 */
public final class BonusResult {
    private final int bonus;
    private final StatusValidation status;
    
    /**
     * Constructor that receives both bonus and status from a calculus.
     * 
     * @param bonus - Quantity of bonus that will make part of total password strengths
     * @param status - Status of the validation, if its failure, sufficient or exceptional
     */
    public BonusResult(int bonus, StatusValidation status) {
        this.bonus = bonus;
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    /**
     * Get the calculated bonus.
     * 
     * @return bonus
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Get the validation status.
     * 
     * @return status
     */
    public StatusValidation getStatus() {
        return status;
    }
}