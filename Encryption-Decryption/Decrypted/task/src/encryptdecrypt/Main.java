package encryptdecrypt;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        final String operation = scn.nextLine();
        final String input = scn.nextLine();
        final int key = scn.nextInt();

        // enc: for encryption using "key"
        // dec: for decryption using the same "key"
        switch (operation) {
            case "enc":
                // cipherText: encrypted message, using "key"
                String cipherText = encrypt(input, key);
                System.out.println(cipherText);
                break;

            case "dec":
                // message: decrypted message/text, using the same "key"
                String message = decrypt(input, key);
                System.out.println(message);
                break;

            default:
                System.out.println("Invalid operation");
                break;
        }
    }

    private static String encrypt(String message, int key) {
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            // ch: original character
            char ch = message.charAt(i);

            // ech: encrypted character
            char ech = (char) (ch + key);
            cipherText.append(ech);
        }

        return cipherText.toString();
    }

    private static String decrypt(String cipherText, int key) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            // ech: encrypted character
            char ech = cipherText.charAt(i);

            // ch: original character
            char ch = (char) (ech - key);
            message.append(ch);
        }

        return message.toString();
    }
}
