package Assignment_Problems;

import java.util.Scanner;

public class FirstNonRepeatingChar {

    // Method to find the length of a string without using length()
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

    // Method to find the first non-repeating character
    public static char findFirstNonRepeating(String text) {
        int len = getLength(text);

        // i. Frequency array for ASCII (256 characters)
        int[] freq = new int[256];

        // ii. Count frequency of each character
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            freq[c]++;
        }

        // iii. Find first non-repeating character
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (freq[c] == 1) {
                return c;
            }
        }

        return '\0'; // return null character if none found
    }

    // Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        char result = findFirstNonRepeating(text);

        if (result == '\0') {
            System.out.println("No non-repeating character found.");
        } else {
            System.out.println("First non-repeating character: " + result);
        }

        sc.close();
    }
}
