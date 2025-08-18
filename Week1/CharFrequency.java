package Assignment_Problems;

import java.util.Scanner;

public class CharFrequency {

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

    // Method to find frequency of characters and return 2D array
    public static String[][] findFrequency(String text) {
        int len = getLength(text);

        // i. Frequency array for ASCII characters
        int[] freq = new int[256];

        // ii. Count frequencies
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // iii. Count how many unique characters exist
        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) uniqueCount++;
        }

        // iv. Create 2D array [character, frequency]
        String[][] result = new String[uniqueCount][2];
        int index = 0;

        // v. Fill result array
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (freq[c] > 0) {
                result[index][0] = String.valueOf(c);
                result[index][1] = String.valueOf(freq[c]);
                freq[c] = 0; // reset to avoid duplicates
                index++;
            }
        }

        return result;
    }

    // Method to display result
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
