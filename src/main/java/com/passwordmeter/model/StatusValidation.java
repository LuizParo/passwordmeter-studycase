package com.passwordmeter.model;

public enum StatusValidation {
    EXCEPTIONAL,
    SUFFICIENT,
    WARNING,
    FAILURE;

    public boolean isBetween(StatusValidation... statuses) {
        for (StatusValidation statusValidation : statuses) {
            if(this.equals(statusValidation)) {
                return true;
            }
        }
        return false;
    }
}