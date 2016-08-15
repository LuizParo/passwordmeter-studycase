package com.passwordmeter.vo;

import com.passwordmeter.model.ScoreResult;

/**
 * A Value Object class for {@link ScoreResult}.
 * 
 * @author Luiz Guilherme Paro
 * 
 * @see ScoreResult
 */
public final class ScoreResultVO {
    private final int strength;
    private final String complexity;
    
    public ScoreResultVO(int strength, String complexity) {
        this.strength = strength;
        this.complexity = complexity;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public String getComplexity() {
        return complexity;
    }
}