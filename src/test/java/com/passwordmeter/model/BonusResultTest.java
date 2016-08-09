package com.passwordmeter.model;

import org.junit.Test;

public class BonusResultTest {

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptStatusValidationNull() {
        new BonusResult(0, null);
    }
}