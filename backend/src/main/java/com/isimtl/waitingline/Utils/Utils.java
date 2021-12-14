package com.isimtl.waitingline.Utils;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Random;

public class Utils {

    public static String getOTP(){
        int len = 6;
        String numbers = "0123456789";

        Random rndm_method = new Random();

        char[] otp = new char[len];

        for (int i = 0; i < len; i++)
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        return String.valueOf(otp);
    }


    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static Boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }
}
