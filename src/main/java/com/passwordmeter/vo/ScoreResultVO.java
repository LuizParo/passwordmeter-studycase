package com.passwordmeter.vo;

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