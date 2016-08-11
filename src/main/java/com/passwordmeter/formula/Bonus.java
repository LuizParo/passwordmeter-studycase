package com.passwordmeter.formula;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.passwordmeter.util.FormulaUtil;

/**
 * This annotation defines if a {@link Formula} is responsible for calculating
 * an addition or deduction bonus for password strength.
 * 
 * <strong>This annotation is essential for auto-instantiation of the {@link Formula}
 * by {@link FormulaUtil#allFormulas()}, without it, the {@link Formula} will have to
 * be instantiated manually.</strong>
 * 
 * @author Luiz Guilherme Paro
 * 
 * @see BonusType
 * @see FormulaUtil#allFormulas()
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