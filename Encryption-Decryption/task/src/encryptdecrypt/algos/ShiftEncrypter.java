package encryptdecrypt.algos;

public class ShiftEncrypter implements AlgorithmStrategy {
    private static final int NO_OF_ALPHABETS = 26;

//    private String message;
//    private int key;
//
//    ShiftEncrypter(String message, int key) {
//        this.message = message;
//        this.key = key;
//    }

    @Override
    public String getResult(String message, int key) {
        return encrypt(message, key);
    }

    private String encrypt(String message, int key) {

        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            // ch: original character
            char ch = message.charAt(i);

            // ech: encrypted character
            if (ch >= 'a' && ch <= 'z') {
                // Method 1: using % (but it only works for encryption). We needed to use another way for decryption.
                // So, now we're choosing another method that is applicable to both encryption and decryption
                // char ech = (char) ((((ch - 'a') + key) % NO_OF_ALPHABETS) + 'a');

                // Method 2: Works for both encryption and decryption
                char ech = (ch + key) > 'z' ? (char) ((ch + key) - NO_OF_ALPHABETS) : (char) (ch + key);
                cipherText.append(ech);
            } else if (ch >= 'A' && ch <= 'Z') {
                // Method 1: using % (but it only works for encryption). We needed to use another way for decryption.
                // So, now we're choosing another method that is applicable to both encryption and decryption
                // char ech = (char) ((((ch - 'A') + key) % NO_OF_ALPHABETS) + 'A');

                // Method 2: Works for both encryption and decryption
                char ech = (ch + key) > 'Z' ? (char) ((ch + key) - NO_OF_ALPHABETS) : (char) (ch + key);
                cipherText.append(ech);
            } else {
                // adding non-alphabetic characters without encryption (eg: spaces etc.)
                cipherText.append(ch);
            }
        }

        return cipherText.toString();
    }
}
