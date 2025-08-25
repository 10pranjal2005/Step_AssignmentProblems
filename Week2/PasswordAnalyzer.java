import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class PasswordAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Analyze user-provided passwords
        System.out.println("Enter a password to analyze (or type 'done' to finish):");
        while (true) {
            String password = scanner.nextLine();
            if (password.equalsIgnoreCase("done")) break;
            
            int[] analysis = analyzePassword(password);
            int score = calculateStrengthScore(password, analysis);
            String strength = getStrengthLevel(score);

            System.out.println("\n--- Analysis Report ---");
            System.out.printf("Password: %s%nLength: %d%nUppercase: %d%nLowercase: %d%nDigits: %d%nSpecial Chars: %d%nScore: %d%nStrength: %s%n",
                password, password.length(), analysis[0], analysis[1], analysis[2], analysis[3], score, strength);
        }

        // Generate a new strong password
        System.out.println("\nEnter desired length for a new strong password (e.g., 12):");
        int length = scanner.nextInt();
        String newPassword = generateStrongPassword(length);
        System.out.println("\nGenerated Strong Password: " + newPassword);

        scanner.close();
    }

    public static int[] analyzePassword(String password) {
        int[] counts = new int[4];
        for (int i = 0; i < password.length(); i++) {
            int ascii = (int) password.charAt(i);
            if (ascii >= 65 && ascii <= 90) counts[0]++;       // Uppercase
            else if (ascii >= 97 && ascii <= 122) counts[1]++; // Lowercase
            else if (ascii >= 48 && ascii <= 57) counts[2]++;  // Digits
            else if (ascii >= 33 && ascii <= 126) counts[3]++; // Special characters
        }
        return counts;
    }

    public static int calculateStrengthScore(String password, int[] analysis) {
        int score = 0;
        // Length points
        if (password.length() > 8) {
            score += (password.length() - 8) * 2;
        }
        // Character variety points
        if (analysis[0] > 0) score += 10; // Uppercase
        if (analysis[1] > 0) score += 10; // Lowercase
        if (analysis[2] > 0) score += 10; // Digits
        if (analysis[3] > 0) score += 10; // Special
        
        // Deduct points for common patterns
        if (password.toLowerCase().contains("123") || password.toLowerCase().contains("abc") || password.toLowerCase().contains("qwerty")) {
            score -= 15;
        }
        
        return Math.max(0, score); // Score can't be negative
    }

    public static String getStrengthLevel(int score) {
        if (score > 50) return "Strong";
        if (score > 20) return "Medium";
        return "Weak";
    }

    public static String generateStrongPassword(int length) {
        if (length < 8) length = 8; // Ensure minimum length

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        String allChars = upper + lower + digits + special;
        
        Random random = new Random();
        List<Character> passwordChars = new ArrayList<>();

        // Ensure at least one of each character type
        passwordChars.add(upper.charAt(random.nextInt(upper.length())));
        passwordChars.add(lower.charAt(random.nextInt(lower.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(special.charAt(random.nextInt(special.length())));

        // Fill the rest of the password with random characters
        for (int i = 4; i < length; i++) {
            passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        // Shuffle the characters for randomness
        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }
        return password.toString();
    }
}