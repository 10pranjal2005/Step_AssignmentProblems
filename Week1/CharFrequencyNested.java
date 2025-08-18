package Assignment_Problems;

import java.util.Scanner;

public class CharFrequencyNested {

    // Method to find frequency using nested loops
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray(); // i. Convert to char array
        int[] freq = new int[chars.length]; // frequency array
        int len = chars.length;

        // ii. Nested loop to calculate frequencies
        for (int i = 0; i < len; i++) {
            freq[i] = 1; // each char appears at least once
            if (chars[i] == '0') continue; // already counted

            for (int j = i + 1; j < len; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // mark duplicate as counted
                }
            }
        }

        // iii. Build result array (character + frequency)
        String[] result = new String[len];
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] != '0') {
                result[count] = chars[i] + " - " + freq[i];
                count++;
            }
        }

        // Trim array to actual size
        String[] finalResult = new String[count];
        for (int i = 0; i < count; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[] frequencies = findFrequency(text);

        System.out.println("\nCharacter Frequencies:");
        for (String entry : frequencies) {
            System.out.println(entry);
        }

        sc.close();
    }
}
