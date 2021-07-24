/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.company.employee.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordHasher {

    private static final int HASHING_ITERATIONS = 128_000;
    private static final int SALT_LENGTH = 72;
    private static final int KEY_LENGTH = 72;

    private static final String HASHED_FORMAT = "%s:%d:%d:%s:%s"; //Hash-Algorithmus, Iterationen, Hash-Größe, Salt, Ergebnis

    public static String hashPassword(String pw) throws GeneralSecurityException {
        char[] password = pw.toCharArray();
        byte[] salt = generateSalt(SALT_LENGTH);

        byte[] hashed = pbkdf2(password, salt, HASHING_ITERATIONS, KEY_LENGTH);

        String saltEncoded = base64Encode(salt);
        String hashedEncoded = base64Encode(hashed);

        return String.format(HASHED_FORMAT, "sha1", HASHING_ITERATIONS, KEY_LENGTH, saltEncoded, hashedEncoded);
    }

    public static void fakeVerifyOperation() throws GeneralSecurityException {
        //TODO Should toCheck be randomized? If so, how could this be done without using significantly more time than a usual hash?
        verifyPassword("ExamplePassword", "sha1:128000:72:xOwVAp0P97lhgSinDNRbayZvpNTQT9q7d9SwXBYzz8GFVpUEObojwanFkFt6WiXftydJE4ZjckMk2Tj0q++1MYGlkZTCLFlQ:FdAeEZ2H00miBd5Dho4pet0ODWpLUsVhmqpcRB5gPkyBRjOTLj0+/LqJMizhU8rDoZ/9YKEwidv2vcIiWL01F4vCKD+tXPEV");
    }

    public static boolean verifyPassword(String toCheck, String hashed) throws GeneralSecurityException {
        String[] splitted = hashed.split(":");

        if (splitted.length != 5) {
            throw new GeneralSecurityException("Invalid hash, does not consist of five parts");
        }

        String algorithm = splitted[0];
        if (!algorithm.equalsIgnoreCase("sha1")) {
            throw new GeneralSecurityException("Invalid hash, hmac hashing function not supported");
        }

        int iterations;
        try {
            iterations = Integer.parseInt(splitted[1]);
        } catch (NumberFormatException e) {
            throw new GeneralSecurityException("Invalid hash, could not parse iteration count");
        }

        int keyLength;
        try {
            keyLength = Integer.parseInt(splitted[2]);
        } catch (NumberFormatException e) {
            throw new GeneralSecurityException("Invalid hash, could not parse key length");
        }

        String salt64 = splitted[3];
        String hash64 = splitted[4];

        byte[] salt = base64Decode(salt64);
        byte[] correctHash = base64Decode(hash64);

        byte[] testHash = pbkdf2(toCheck.toCharArray(), salt, iterations, keyLength);

        return slowEquals(testHash, correctHash);
    }

    public static byte[] generateSalt(int size) throws GeneralSecurityException {
        byte[] data = new byte[size];

        SecureRandom random = SecureRandom.getInstanceStrong();
        random.nextBytes(data);

        return data;
    }

    public static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws GeneralSecurityException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        SecretKey key = skf.generateSecret(spec);

        return key.getEncoded();
    }

    private static boolean slowEquals(byte[] one, byte[] two) {
        int diff = one.length ^ two.length;
        for (int i = 0; i < one.length && i < two.length; i++) {
            diff |= one[i] ^ two[i];
        }

        return diff == 0;
    }

    private static byte[] base64Decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    private static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
