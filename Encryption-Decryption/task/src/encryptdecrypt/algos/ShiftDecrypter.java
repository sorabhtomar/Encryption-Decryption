package encryptdecrypt.algos;

public class ShiftDecrypter implements AlgorithmStrategy {
    private static final int NO_OF_ALPHABETS = 26;

    //    private String cipherText;
//    private int key;
//
//    ShiftDecrypter(String cipherText, int key) {
//        this.cipherText = cipherText;
//        this.key = key;
//    }

    @Override
    public String getResult(String cipherText, int key) {
        return decrypt(cipherText, key);
    }

    private String decrypt(String cipherText, int key) {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            // ech: encrypted character
            char ech = cipherText.charAt(i);

            // ch: original character
            // Using Method 2, because Method 1 (using % doesn't work in this case)
            if (ech >= 'a' && ech <= 'z') {
                char ch = (ech - key) < 'a' ? (char) ((ech - key) + NO_OF_ALPHABETS) : (char) (ech - key);
                message.append(ch);
            } else if (ech >= 'A' && ech <= 'Z') {
                char ch = (ech - key) < 'A' ? (char) ((ech - key) + NO_OF_ALPHABETS) : (char) (ech - key);
                message.append(ch);
            } else {
                // adding non-alphabetic characters without decryption (eg: spaces), because these weren't encrypted in the first place
                message.append(ech);
            }
        }

        return message.toString();
    }
}
