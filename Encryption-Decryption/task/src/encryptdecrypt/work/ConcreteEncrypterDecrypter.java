package encryptdecrypt.work;

import encryptdecrypt.algos.*;

public class ConcreteEncrypterDecrypter extends EncrypterDecrypter {

    private AlgorithmContext context;

    public ConcreteEncrypterDecrypter(AlgorithmContext context) {
        this.context = context;
    }

    @Override
    protected String encrypt(String algorithm, String message, int key) {
        switch (algorithm) {
            case "shift":
                context.setStrategy(new ShiftEncrypter());
                return context.getResult(message, key);

            case "unicode":
                context.setStrategy(new UnicodeEncrypter());
                return context.getResult(message, key);

            default:
                throw new IllegalArgumentException("Error! Unknown/Invalid algorithm name passed");
        }
    }

    @Override
    protected String decrypt(String algorithm, String cipherText, int key) {
        switch (algorithm) {
            case "shift":
                context.setStrategy(new ShiftDecrypter());
                return context.getResult(cipherText, key);

            case "unicode":
                context.setStrategy(new UnicodeDecrypter());
                return context.getResult(cipherText, key);

            default:
                throw new IllegalArgumentException("Error! Unknown/Invalid algorithm name passed");
        }
    }
}
