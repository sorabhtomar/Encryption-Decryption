package encryptdecrypt.work;

import encryptdecrypt.utils.Arguments;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

// Will apply "Template method" design pattern in this.
public abstract class EncrypterDecrypter {

    // Template method: It describes the common algorithm, while the subclasses implement steps of the algorithm.
    // This pattern lets the subclasses implement the steps of the algorithm without changing that algorithm's skeleton.
    public void work(Arguments args) {
        String inputText = "";

        // Some situations involving "-data" and "-in" (in command line arguments)
        // 1. Only "-data" or "-in" present: Use whatever is present ("-data" or "-in")
        // 2. None of "-data" or "-in" present: Use empty string "" as data (message/cipherText)
        // 3. Both of "-data" and "-in" present: Use "-data" as default
        if (!"".equals(args.getData()) && !"".equals(args.getIn())) {
            // Both "data" and "in" present
            inputText = args.getData();
        } else if (!"".equals(args.getData())) {
            // Only "data" present
            inputText = args.getData();
        } else if (!"".equals(args.getIn())) {
            // Only "in" present
            // in: contains the file path from where to read
            inputText = readFromFile(args.getIn());
        } else {
            // None of "data" or "in" present
            // inputText = ""; // already it's initialized with ""
        }


        // enc: for encryption using "key"
        // dec: for decryption using the same "key"
        switch (args.getMode()) {
            case "enc":
                // cipherText: encrypted message, using "key"
                String cipherText = encrypt(args.getAlg(), inputText, args.getKey());
                if ("".equals(args.getOut())) {
                    System.out.println(cipherText);
                } else {
                    writeToFile(args.getOut(), cipherText);
                }
                break;

            case "dec":
                // message: decrypted message/text, using the same "key"
                String message = decrypt(args.getAlg(), inputText, args.getKey());
                if ("".equals(args.getOut())) {
                    System.out.println(message);
                } else {
                    writeToFile(args.getOut(), message);
                }
                break;

            default:
                System.out.println("Invalid operation");
                break;
        }
    }


    // Primitive methods (with default implementation provided, for some steps).
    // This default implementation can be changed by the subclass if needed.
    private String readFromFile(String fileName) {
        String text = "";

        try {
            text = new String(Files.readAllBytes(Path.of(fileName)));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return text;
    }

    private void writeToFile(String out, String message) {
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.print(message);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // Primitive methods (abstract i.e. which need to be implemented by the concrete class)
    protected abstract String encrypt(String algorithm, String message, int key);
    protected abstract String decrypt(String algorithm, String cipherText, int key);
}
