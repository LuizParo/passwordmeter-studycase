package com.passwordmeter.model;

public final class ScoreResult {
    private final int strength;
    private Complexity complexity;
    
    public ScoreResult(int strength, Complexity complexity) {
        this.strength = strength;
        this.complexity = complexity;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public Complexity getComplexity() {
        return complexity;
    }
}