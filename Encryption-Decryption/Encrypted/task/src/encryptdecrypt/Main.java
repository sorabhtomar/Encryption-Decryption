package encryptdecrypt;

public class Main {
    // message: original message/text
    private static final String message = "we found a treasure!";

    public static void main(String[] args) {
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                // ech: encrypted character
                char ech = (char) ('z' - (ch - 'a'));
                cipherText.append(ech);
            } else {
                cipherText.append(ch);
            }
        }

        System.out.println(cipherText);
    }
}
