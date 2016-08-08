package com.passwordmeter.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordTest {

    @Test(expected = NullPointerException.class)
    public void shouldNotAcceptPasswordNull() {
        new Password(null);
    }
    
    @Test
    public void shouldGetTheQuantityOfUppercaseLettersInPassword() {
        Password password = new Password("aBCdEfGHi");
        Assert.assertEquals(5, password.getQuantityOfUppercaseLetters());
    }
    
    @Test
    public void shouldGetTheQuantityOfLowercaseLettersInPassword() {
        Password password = new Password("aBCdEfGHi");
        Assert.assertEquals(4, password.getQuantityOfLowercaseLetters());
    }
    
    @Test
    public void shouldGetZeroQuantityOfDigitsWhenPasswordIsBlank() {
        Password password = new Password("");
        Assert.assertEquals(0, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldGetTheQuantityOfDigitsInPassword() {
        Password password = new Password("aBCdEfGHi123Jkl");
        Assert.assertEquals(3, password.getQuantityOfDigits());
    }
    
    @Test
    public void shouldGetTheQuantityOfDigitsInMiddleOfPassword() {
        Password password = new Password("0aBCdEfGHi123Jkl4");
        Assert.assertEquals(3, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldGetZeroQuantityOfDigitsInMiddleWhenPasswordHasOneCharacter() {
        Password password = new Password("1");
        Assert.assertEquals(0, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldGetZeroQuantityOfDigitsInMiddleWhenPasswordHasTwoCharacters() {
        Password password = new Password("11");
        Assert.assertEquals(0, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldGetZeroQuantityOfSpecialCharactersWhenPasswordIsBlank() {
        Password password = new Password("");
        Assert.assertEquals(0, password.getQuantityOfSpecialCharactersInMiddle());
    }
    
    @Test
    public void shouldGetTheQuantityOfSpecialCharactersInPassword() {
        Password password = new Password("aBCdEfGHiJkl@#%&_$");
        Assert.assertEquals(5, password.getQuantityOfSpecialCharacters());
    }
    
    @Test
    public void shouldGetTheQuantityOfSpecialCharactersInMiddleOfPassword() {
        Password password = new Password("*aBCdEfGHiJkl@#%&_$*");
        Assert.assertEquals(5, password.getQuantityOfSpecialCharactersInMiddle());
    }
    
    @Test
    public void shouldGetZeroQuantityOfSpecialCharactersInMiddleWhenPasswordHasOneCharacter() {
        Password password = new Password("@");
        Assert.assertEquals(0, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldGetZeroQuantityOfSpecialCharactersInMiddleWhenPasswordHasTwoCharacters() {
        Password password = new Password("@%");
        Assert.assertEquals(0, password.getQuantityOfDigitsInMiddle());
    }
    
    @Test
    public void shouldReturnTrueWhenPasswordHasLettersOnly() {
        Password password = new Password("abcdefghij__");
        Assert.assertTrue(password.isLettersOnly());
    }
    
    @Test
    public void shouldReturnFalseWhenPasswordHasNotOnlyLetters() {
        Password password = new Password("56abcdefghij@");
        Assert.assertFalse(password.isLettersOnly());
    }
    
    @Test
    public void shouldReturnTrueWhenPasswordHasNumbersOnly() {
        Password password = new Password("123456789");
        Assert.assertTrue(password.isNumbersOnly());
    }
    
    @Test
    public void shouldReturnFalseWhenPasswordHasNotOnlyNumbers() {
        Password password = new Password("56abcdefghij@");
        Assert.assertFalse(password.isNumbersOnly());
    }
}