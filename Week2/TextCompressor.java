import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TextCompressor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String originalText = scanner.nextLine();

        // Compress the text
        String compressedText = compress(originalText);
        
        // Decompress the text
        String decompressedText = decompress(compressedText);
        
        // Display analysis
        displayAnalysis(originalText, compressedText, decompressedText);

        scanner.close();
    }
    
    public static String compress(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        StringBuilder compressed = new StringBuilder();
        int count = 1;
        for (int i = 0; i < text.length(); i++) {
            // Check if it's the last character or if the next character is different
            if (i == text.length() - 1 || text.charAt(i) != text.charAt(i + 1)) {
                compressed.append(count);
                compressed.append(text.charAt(i));
                count = 1; // Reset count for the new character
            } else {
                count++;
            }
        }
        return compressed.toString();
    }

    public static String decompress(String compressedText) {
        if (compressedText == null || compressedText.isEmpty()) {
            return "";
        }
        StringBuilder decompressed = new StringBuilder();
        StringBuilder countStr = new StringBuilder();
        for (int i = 0; i < compressedText.length(); i++) {
            char c = compressedText.charAt(i);
            // If the character is a digit, append to the count string
            if (c >= '0' && c <= '9') {
                countStr.append(c);
            } else { // If it's a character to be repeated
                int count = Integer.parseInt(countStr.toString());
                for (int j = 0; j < count; j++) {
                    decompressed.append(c);
                }
                countStr.setLength(0); // Reset count for the next sequence
            }
        }
        return decompressed.toString();
    }

    public static void displayAnalysis(String original, String compressed, String decompressed) {
        System.out.println("\n--- Compression Analysis ---");
        System.out.println("Original Text: " + original);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);
        System.out.println("------------------------------");
        System.out.println("Original Size: " + original.length() + " chars");
        System.out.println("Compressed Size: " + compressed.length() + " chars");
        
        boolean isValid = original.equals(decompressed);
        System.out.println("Decompression Validation: " + (isValid ? "Successful" : "Failed"));
        
        if (original.length() > 0) {
            double ratio = (double) compressed.length() / original.length();
            double efficiency = (1 - ratio) * 100;
            System.out.printf("Compression Efficiency: %.2f%%%n", efficiency);
        }
    }
}