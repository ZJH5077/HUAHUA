package com.example.demo.common;

import java.util.*;
public class test {
    public static void main(String[] args) {
        String[] list = new String[3];
        for(int i = 0;i < 3;i++){
            list[i] = "0123";
        }
        int m = list[0].length();
        for(int i = 0; i < m;i++){
            StringBuffer sb = new StringBuffer();
            for(int j = 0; j < 3;j++) { //行
                sb.append(list[j].charAt(i));
                System.out.println("sb"+ sb);
            }
            String res = deleteZero(sb);
            System.out.println(res);
        }
    }

    public static String deleteZero(StringBuffer sb){
        int i = 0;
        int length = sb.length();
        String s = sb.toString();
        while(s.charAt(i)=='0'){
            i++;
        }
        return sb.substring(i+1,length);
    }
}