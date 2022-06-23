package encryptdecrypt.algos;

public class UnicodeEncrypter implements AlgorithmStrategy {
//    private String message;
//    private int key;
//
//    UnicodeEncrypter(String message, int key) {
//        this.message = message;
//        this.key = key;
//    }

    @Override
    public String getResult(String message, int key) {
        return encrypt(message, key);
    }

    // Here, we've passed the required stuff ("data" and "key") in the arguments. We could just use the fields of the class;
    // but I just wanted to reuse the previous the logic I created in the previous step.
    private String encrypt(String message, int key) {
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
}
