package com.passwordmeter.util;

public final class StringUtils {
    
    private StringUtils(){}
    
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
    
    public static int getNumberOfSequentialLettersFromSequence(String word, String sequence) {
        int sequenceLetters = 0;
        
        for(int i = 0; i < sequence.length() - 3; i++) {
            String strForward = sequence.substring(i, i + 3);
            String strReverse = revertString(strForward);
            
            if(word.contains(strForward) || word.contains(strReverse)) {
                ++sequenceLetters;
            }
        }
        return sequenceLetters;
    }
    
    public static String revertString(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}