package com.passwordmeter.formula;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation defines if a {@link Formula} is responsible for calculating
 * an addition or deduction bonus for password strength.
 * 
 * @author Luiz Guilherme Paro
 * @see BonusType
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bonus {
    
    /**
     * Type of the bonus.
     * @return {@link BonusType} that defines if the bonus is an addition or deduction.
     */
    BonusType type() default BonusType.ADDITION;
}