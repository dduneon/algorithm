package com.nhnacademy.day0904;

/**
 * Hello world!
 *
 */
public class Main
{
    public static String solution(String input) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<input.length(); i++) {
            char at = input.charAt(i);
            if(at >= 65 && at <= 90) {
                at += 32;
            } else if(at >= 97 && at <= 122) {
                at -= 32;
            }
            sb.append(at);
        }
        return sb.toString();
    }
}
