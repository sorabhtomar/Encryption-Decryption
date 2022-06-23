package encryptdecrypt;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        // Initializing "mode", "key" and "data" with default values
        // (for when we don't get values for them, from the command line arguments)
        String mode = "enc";
        int key = 0;
        String data = "";

        String in = "";
        String out = "";

        // If "-out" is NOT present (in the command line arguments): Use Standard Output

        // Process command line arguments
        // position in the command line arguments
        int position = 0;
        while (position < args.length) {
            // option: command line option/flag
            String option = args[position];

            switch (option) {
                case "-mode":
                    mode = args[position + 1];
                    position += 2;
                    break;

                case "-key":
                    key = Integer.parseInt(args[position + 1]);
                    position += 2;
                    break;

                case "-data":
                    data = args[position + 1];
                    position += 2;
                    break;

                case "-in":
                    in = args[position + 1];
                    position += 2;
                    break;

                case "-out":
                    out = args[position + 1];
                    position += 2;
                    break;

                default:
                    // When the argument "option" is not one of the defined ones (i.e. "-mode", "-key" etc.)
                    // Then, increment "position" by 1.
                    position++;

                    // We could throw an exception here, instead of letting the program continue here (by increasing "position" by 1).
                    // We reaching here, means that our input is wrong somehow. Either some key (i.e. command line option/flag) was missing
                    // or the value for a command line option/flag was missing. In any case, we should just stop the program because the input is wrong.

                    // throw new Exception("Error! There was some issue in the command line option(s)/flag(s) or value(s)");

                    // System.out.println("Error! There was some issue in the command line option(s)/flag(s) or value(s)");
                    // return;
            }
        }

        process(mode, key, data, in, out);
    }

    private static void process(String mode, int key, String data, String in, String out) {
        String inputText = "";

        // Some situations involving "-data" and "-in" (in command line arguments)
        // 1. Only "-data" or "-in" present: Use whatever is present ("-data" or "-in")
        // 2. None of "-data" or "-in" present: Use empty string "" as data (message/cipherText) i.e. inputText
        // 3. Both of "-data" and "-in" present: Use "-data" as default
        if (!"".equals(data) && !"".equals(in)) {
            // Both "data" and "in" present
            inputText = data;
        } else if (!"".equals(data)) {
            // Only "data" present
            inputText = data;
        } else if (!"".equals(in)) {
            // Only "in" present
            // in: contains the file path from where to read
            inputText = readFromFile(in);
        } else {
            // None of "data" or "in" present
            // inputText = ""; // already it's initialized with ""
        }


        // enc: for encryption using "key"
        // dec: for decryption using the same "key"
        switch (mode) {
            case "enc":
                // cipherText: encrypted message, using "key"
                String cipherText = encrypt(inputText, key);
                if ("".equals(out)) {
                    System.out.println(cipherText);
                } else {
                    writeToFile(out, cipherText);
                }
                break;

            case "dec":
                // message: decrypted message/text, using the same "key"
                String message = decrypt(inputText, key);
                if ("".equals(out)) {
                    System.out.println(message);
                } else {
                    writeToFile(out, message);
                }
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

    private static String readFromFile(String fileName) {
        String text = "";

        try {
            text = new String(Files.readAllBytes(Path.of(fileName)));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return text;
    }

    private static void writeToFile(String out, String message) {
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.print(message);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
