package HelperTools;

import java.util.*;

public class Encrypter{
    
    public static String scramble(String s){
        StringBuilder sb = new StringBuilder();
        Random r = new Random(s.hashCode());
        for(int i = 0; i < s.length(); i++){
            long c = ((long)s.charAt(i))+r.nextInt();
            while(c < 0){
                c += Character.MAX_VALUE;
            }
            while(c > Character.MAX_VALUE){
                c -= Character.MAX_VALUE;
            }
            sb.append((char)c);
        }
        StringBuilder add = new StringBuilder();
        add.append(s.hashCode());
        for(int i = add.length(); i < 15; i++){
            add.append((char)(i*42));
        }
        for(int i = 0; i < add.length(); i++)
            add.setCharAt(i, (char)(add.charAt(i)+50));
        sb.append(add.toString());
        return sb.toString();
    }
    
    public static String unscramble(String scrambled){
        StringBuilder sb = new StringBuilder();
        StringBuilder s = new StringBuilder();
        for(int i = scrambled.length()-15; i < scrambled.length(); i++){
            char c = (char)(scrambled.charAt(i)-50);
            if(Character.getType(c) == Character.DECIMAL_DIGIT_NUMBER)
                s.append(c);
        }
        int seed = Integer.parseInt(s.toString());
        Random r = new Random(seed);
        for(int i = 0; i < scrambled.length()-15; i++){
            int c = scrambled.charAt(i)-r.nextInt();
            while(c < 0){
                c += Character.MAX_VALUE;
            }
            while(c > Character.MAX_VALUE){
                c -= Character.MAX_VALUE;
            }
            sb.append((char)c);
        }
        return sb.toString();
    }
    
    public static String hashPassword(String s){
        StringBuilder sb = new StringBuilder();
        Random r = new Random(s.hashCode());
        for(int i = 0; i < s.length(); i++){
            long c = ((long)s.charAt(i))+r.nextInt();
            while(c < 0){
                c += Character.MAX_VALUE;
            }
            while(c > Character.MAX_VALUE){
                c -= Character.MAX_VALUE;
            }
            sb.append((char)c);
        }
        int MAX = sb.toString().hashCode()%10;
        for(int i = 0; i < MAX; i++){
            char c = (char)((i*r.nextInt())%Character.MAX_VALUE);
            sb.insert(r.nextInt(sb.length()-1), c);
        }
        return sb.toString();
    }
    
    public static void test(){
        String h = hashPassword("IchHabEinSehrLangesUndKompliziertesPasswort");
        String h2 = hashPassword("IchHabEinSehrLangesUndKompliziertesPasswort");
        System.out.println(h);
        System.out.println(h2);
        String h3 = scramble("IchHabEinSehrLangesUndKompliziertesPasswort");
        System.out.println(h3);
        System.out.println(unscramble(h3));
    }
    
}