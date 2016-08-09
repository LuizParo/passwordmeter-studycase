package com.passwordmeter.model;

import org.junit.Assert;
import org.junit.Test;

public class StatusValidationTest {

    @Test
    public void shouldFindStatusValidationBetween() {
        boolean isBetween = StatusValidation.EXCEPTIONAL.isBetween(StatusValidation.SUFFICIENT, StatusValidation.EXCEPTIONAL);
        Assert.assertTrue(isBetween);
    }
    
    @Test
    public void shouldNotFindStatusValidationBetween() {
        boolean isBetween = StatusValidation.FAILURE.isBetween(StatusValidation.SUFFICIENT, StatusValidation.EXCEPTIONAL);
        Assert.assertFalse(isBetween);
    }
}