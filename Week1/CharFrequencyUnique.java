package Assignment_Problems;

import java.util.Scanner;

public class CharFrequencyUnique {

    // Method to find length without using length()
    public static int getLength(String text) {
        int count = 0;
        try {
            while (true) {
                text.charAt(count); // throws exception when index exceeds
                count++;
            }
        } catch (StringIndexOutOfBoundsException e) {
            return count;
        }
    }

    // a. Method to find unique characters using charAt() and nested loops
    public static char[] uniqueCharacters(String text) {
        int len = getLength(text);
        char[] temp = new char[len];
        int uniqueCount = 0;

        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;

            // check if already added
            for (int j = 0; j < uniqueCount; j++) {
                if (temp[j] == c) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                temp[uniqueCount] = c;
                uniqueCount++;
            }
        }

        // create final array with only unique characters
        char[] unique = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            unique[i] = temp[i];
        }
        return unique;
    }

    // b. Method to find frequency using unique characters
    public static String[][] findFrequency(String text) {
        int len = getLength(text);

        // i. Frequency array for ASCII chars
        int[] freq = new int[256];

        // ii. Count frequencies
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // iii. Get unique characters
        char[] uniques = uniqueCharacters(text);

        // iv. Create 2D array
        String[][] result = new String[uniques.length][2];

        // v. Fill with character + frequency
        for (int i = 0; i < uniques.length; i++) {
            result[i][0] = String.valueOf(uniques[i]);
            result[i][1] = String.valueOf(freq[uniques[i]]);
        }

        return result;
    }

    // Display table
    public static void displayTable(String[][] table) {
        System.out.printf("%-10s %-10s\n", "Character", "Frequency");
        System.out.println("----------------------");
        for (String[] row : table) {
            System.out.printf("%-10s %-10s\n", row[0], row[1]);
        }
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[][] freqTable = findFrequency(text);

        System.out.println("\nCharacter Frequency Table:");
        displayTable(freqTable);

        sc.close();
    }
}
