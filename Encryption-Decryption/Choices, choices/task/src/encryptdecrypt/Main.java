package encryptdecrypt;

import encryptdecrypt.algos.AlgorithmContext;
import encryptdecrypt.utils.Arguments;
import encryptdecrypt.work.ConcreteEncrypterDecrypter;
import encryptdecrypt.work.EncrypterDecrypter;

public class Main {

    public static void main(String[] args) {
        // Initializing "mode", "key", "data" and "alg" with default values
        // (for when we don't get values for them, from the command line arguments)
        Arguments arguments = new Arguments();

        arguments.setMode("enc"); // values: enc/dec
        arguments.setKey(0);
        arguments.setData("");
        arguments.setAlg("shift"); // values: shift/unicode

        // Also initializing "in" and "out"
        arguments.setIn("");
        arguments.setOut("");

        // If "-out" is NOT present (in the command line arguments): Use Standard Output

        // Process command line arguments (and there are total 6 of them)
        // position in the command line arguments
        int position = 0;
        while (position < args.length) {
            // option: command line option/flag
            String option = args[position];

            switch (option) {
                case "-mode":
                    arguments.setMode(args[position + 1]);
                    position += 2;
                    break;

                case "-key":
                    arguments.setKey(Integer.parseInt(args[position + 1]));
                    position += 2;
                    break;

                case "-data":
                    arguments.setData(args[position + 1]);
                    position += 2;
                    break;

                case "-in":
                    arguments.setIn(args[position + 1]);
                    position += 2;
                    break;

                case "-out":
                    arguments.setOut(args[position + 1]);
                    position += 2;
                    break;

                case "-alg":
                    arguments.setAlg(args[position + 1]);
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

        // We'll use the following design patterns, here: Template Method and Strategy
        EncrypterDecrypter processor = new ConcreteEncrypterDecrypter(new AlgorithmContext());
        processor.work(arguments);
    }
}
