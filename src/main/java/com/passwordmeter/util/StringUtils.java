package com.passwordmeter.util;

import com.passwordmeter.formula.ConsecutiveLowercaseLetters;
import com.passwordmeter.formula.ConsecutiveNumbers;
import com.passwordmeter.formula.ConsecutiveUppercaseLetters;
import com.passwordmeter.formula.Formula;
import com.passwordmeter.formula.SequentialLetters;
import com.passwordmeter.formula.SequentialNumbers;
import com.passwordmeter.formula.SequentialSymbols;

/**
 * Utility class used by {@link Formula}s instances to manipulate {@link String} objects.
 * 
 * @author LuizGuilherme
 */
public final class StringUtils {
    
    private StringUtils(){}
    
    /**
     * Method that searches for consecutive characters in an array of words
     * according with a specified regex and return the number of occurrences
     * in it.
     * 
     * <p>Example:<p>
     * 
     * <p>Words: [A, A, A]</p>
     * <p>Regex: [A-Z]+</p>
     * <p>Return: 3 occurrences of letter A</p>
     * 
     * @param words - Words that need to be matched with
     *                the given regex.
     * 
     * @param regex - The regex pattern that defines what
     *                characters should be matched.
     * 
     * @return number of consecutive characters defined by
     *         the given pattern(regex).
     * 
     * @see ConsecutiveLowercaseLetters
     * @see ConsecutiveUppercaseLetters
     * @see ConsecutiveNumbers
     */
    public static int getNumberOfConsecutiveCharactersByPattern(String[] words, String regex) {
        int previousIndex = -1;
        int consecutiveCharacters = 0;
        
        for(int i = 0; i < words.length; i++) {
            if(words[i].matches(regex)) {
                if(previousIndex != -1) {
                    if((previousIndex + 1) == i) {
                        ++consecutiveCharacters;
                    }
                }
                previousIndex = i;
            }
        }
        return consecutiveCharacters;
    }
    
    /**
     * Method that check if a given {@link String} contains a sequence of characters
     * defined in a given {@link String}, representing, for example, the entire alphabet
     * or the numbers from 0 to 9 and return the number of occurrences -2 (The first two
     * characters are ignored in the return).
     * 
     * <p>Example:</p>
     * 
     * <p>Word: abcdefgh</p>
     * <p>Sequence: abcdefghijklmnopqrstuvwxyz</p>
     * <p>Return: 6 occurrences</p>
     * 
     * @param word - Word that needs to be checked for consecutive characters
     *               in the given sequence of {@link String}s.
     *               
     * @param sequence - A {@link String} representing the sequence that needs
     *                   to be verified.
     * 
     * @return number of occurrences found. 
     * 
     * @see SequentialLetters
     * @see SequentialNumbers
     * @see SequentialSymbols
     */
    public static int getNumberOfSequentialLettersFromSequence(String word, String sequence) {
        int sequenceLetters = 0;
        
        for(int i = 0; i < sequence.length() - 3; i++) {
            String strForward = sequence.substring(i, i + 3);
            String strReverse = reverseString(strForward);
            
            if(word.contains(strForward) || word.contains(strReverse)) {
                ++sequenceLetters;
            }
        }
        return sequenceLetters;
    }
    
    /**
     * Helper method to reverses the given {@link String} parameter.
     * 
     * @param string - {@link String} that need to be reversed.
     * @return the reversed {@link String}
     */
    public static String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}