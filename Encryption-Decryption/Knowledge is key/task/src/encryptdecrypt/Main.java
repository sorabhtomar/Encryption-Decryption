package encryptdecrypt;

import java.util.Scanner;

public class Main {

    private static final int NO_OF_ALPHABETS = 26;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // message: original message/text
        final String message = scn.nextLine();
        final int key = scn.nextInt();

        // cipherText: encrypted message, using "key"
        String cipherText = encrypt(message, key);
        System.out.println(cipherText);
    }

    private static String encrypt(String message, int key) {
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                // ech: encrypted character
                // Steps:
                // 1. ch = ch - 'a' (i.e. ch -= 'a')
                // 2. ch = ch + key (i.e. ch += key)
                // 3. ch = ch % NO_OF_ALPHABETS (i.e. ch %= NO_OF_ALPHABETS)
                // 4. ch = ch + 'a' (i.e. ch += 'a')
                char ech = (char) ((((ch - 'a') + key) % NO_OF_ALPHABETS) + 'a');
                cipherText.append(ech);
            } else {
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }
}
