package com.passwordmeter.model;

import java.util.Objects;

public class BonusResult {
    private final int bonus;
    private final StatusValidation status;
    
    public BonusResult(int bonus, StatusValidation status) {
        this.bonus = bonus;
        this.status = Objects.requireNonNull(status, "status cannot be null");
    }

    public int getBonus() {
        return bonus;
    }

    public StatusValidation getStatus() {
        return status;
    }
}