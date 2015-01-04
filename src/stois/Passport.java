/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stois;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;
import sun.security.provider.SecureRandom;

/**
 *
 * @author Milhouse
 */
public class Passport {

    private static Random random = null;

    public String getHash(char[] password, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(Arrays.toString(password).getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getSalt() throws NoSuchAlgorithmException {
        int myMagicNuber = 16;
        String characters = "asdLKJ:ASDklkj!qweRZT@POITZU#?MNYXvnb$yxcvcxnm%^asd&nmb*LKJ'/.,€";
        random = new Random();
        char[] text = new char[myMagicNuber];
        for (int i = 0; i < myMagicNuber; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    public String generateRandomPassword(int length) {
        String characters = "QWEsdasdRTYnmbUIHowadkoVPRasLesezxvbcm,dla*OPlkjhasdfxcvbnm";
        random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }

    //
    public boolean isItSamePassword(char[] passwordFromField, String passwordSalt, String passwordFromDB) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        boolean jeTo = false;
        String hashPasswd = getHash(passwordFromField, passwordSalt);
        if (hashPasswd.equals(passwordFromDB)) {
            jeTo = true;
        }
        return jeTo;
    }
}
