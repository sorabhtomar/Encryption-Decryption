package encryptdecrypt;

public class Main {

    public static void main(String[] args) {
        // Initializing "mode", "key" and "data" with default values
        // (for when we don't get values for them, from the command line arguments)
        String mode = "enc";
        int key = 0;
        String data = "";

        // Process command line arguments
        for (int i = 0; i < args.length; i++) {
            // option: command line option/flag
            if (i % 2 == 0) {
                String option = args[i];

                switch (option) {
                    case "-mode":
                        mode = args[i + 1];
                        break;

                    case "-key":
                        key = Integer.parseInt(args[i + 1]);
                        break;

                    case "-data":
                        data = args[i + 1];
                        break;
                }
            }
        }

        // enc: for encryption using "key"
        // dec: for decryption using the same "key"
        switch (mode) {
            case "enc":
                // cipherText: encrypted message, using "key"
                String cipherText = encrypt(data, key);
                System.out.println(cipherText);
                break;

            case "dec":
                // message: decrypted message/text, using the same "key"
                String message = decrypt(data, key);
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
