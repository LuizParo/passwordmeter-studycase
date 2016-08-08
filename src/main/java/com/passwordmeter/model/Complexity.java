package com.passwordmeter.model;

public enum Complexity {
    TOO_SHORT("Too Short"),
    VERY_WEAK("Very Weak"),
    WEAK("Weak"),
    GOOD("Good"),
    STRONG("Strong"),
    VERY_STRONG("Very Strong");
    
    private String alias;

    private Complexity(String alias) {
        this.alias = alias;
    }
    
    public String getAlias() {
        return alias;
    }
}