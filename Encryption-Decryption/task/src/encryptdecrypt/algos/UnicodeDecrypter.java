package encryptdecrypt.algos;

public class UnicodeDecrypter implements AlgorithmStrategy {
//    private String cipherText;
//    private int key;
//
//    UnicodeDecrypter(String cipherText, int key) {
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
            char ch = (char) (ech - key);
            message.append(ch);
        }

        return message.toString();
    }
}
