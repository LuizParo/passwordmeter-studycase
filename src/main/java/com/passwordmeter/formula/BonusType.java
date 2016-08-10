package com.passwordmeter.formula;

/**
 * Enum that defines the two types of Bonus calculation a {@link Formula} can have.
 * 
 * @author Luiz Guilherme Paro
 */
public enum BonusType {
    /**
     * Represents those {@link Formula}s that increase the strength of a password.
     */
    ADDITION,
    
    /**
     * Represents those {@link Formula}s that decrease the strength of a password.
     */
    DEDUCTION;
}