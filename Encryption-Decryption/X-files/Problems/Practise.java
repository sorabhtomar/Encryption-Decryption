package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Practise {
    public static String filePath = "./Encryption-Decryption/task/src/encryptdecrypt/dataset_91069.txt";

    public static void worldPopulation(String filePath) {

        // maxDiff: maximum difference of population between any 2 consecutive years i.e. increase in population
        long maxDiff = Long.MIN_VALUE;
        // resYear: result year i.e. year with the largest increase in population compared to previous year
        // initialized with some invalid value
        int resYear = -1;

        try (Scanner scn = new Scanner(new File(filePath))) {
            // First line in file contains heading. Skipping it
            scn.nextLine();

            String[] secondLine = scn.nextLine().split("\\s+");
            // prevYear: previous year
//            int prevYear = Integer.parseInt(secondLine[0]);
            // prevPopulation: population of the previous year
            long prevPopulation = Long.parseLong(secondLine[1].replace(",", ""));

            // With second line in file, filling "previous year" values
            while (scn.hasNextLine()) {
                String[] line = scn.nextLine().split("\\s+");

                if (line.length < 2) {
                    continue;
                }

                int currYear = Integer.parseInt(line[0]);
                long currPopulation = Long.parseLong(line[1].replace(",", ""));

                if (currPopulation - prevPopulation > maxDiff) {
                    maxDiff = currPopulation - prevPopulation;
                    resYear = currYear;
                }

                prevPopulation = currPopulation;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println(resYear);
    }

    public static void countEvenNumbers(String filePath) {
        int count = 0;

        try (Scanner scn = new Scanner(new File(filePath))) {
            while (scn.hasNext()) {
                int num = scn.nextInt();
                if (num == 0) {
                    break;
                }

                if (num % 2 == 0) {
                    // even
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println(count);
    }

    public static void greatestNumber(String filePath) {
        int max = Integer.MIN_VALUE;
        try (Scanner scn = new Scanner(new File(filePath))) {
            while (scn.hasNext()) {
                max = Math.max(max, scn.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println(max);
    }

    public static void countNumbersByCondition(String filePath) {
        int count = 0;
        try (Scanner scn = new Scanner(new File(filePath))) {
            while (scn.hasNext()) {
                if (scn.nextInt() >= 9999) {
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        System.out.println(count);
    }

    public static void sumOfNumbers2(String filePath) {
        String fileContents = "";

        try {
            fileContents = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Can't read file: " + e.getMessage());
        }

        String[] numbers = fileContents.split("\\s+");

        int sum = 0;
        for (String num : numbers) {
            sum += Integer.parseInt(num);
        }

        System.out.println(sum);
    }

    public static void sumOfNumbers(String filePath) {
        File file = new File(filePath);

        int sum = 0;
        try (Scanner scn = new Scanner(file)) {
            while (scn.hasNext()) {
                sum += scn.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + e.getMessage());
        }

        System.out.println(sum);
    }
}
