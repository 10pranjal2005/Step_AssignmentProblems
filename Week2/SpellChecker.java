import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class SpellChecker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // A simple dictionary of correct words
        String[] dictionary = {
            "hello", "world", "java", "program", "spell", "checker", "sentence", 
            "correct", "misspelled", "suggestion", "distance"
        };

        System.out.println("Enter a sentence to spell check:");
        String sentence = scanner.nextLine();

        // Split the sentence into words
        List<String> words = splitIntoWords(sentence.toLowerCase());

        // Prepare lists to store results for display
        List<String> originalWords = new ArrayList<>();
        List<String> statuses = new ArrayList<>();
        List<String> suggestions = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        // Process each word
        for (String word : words) {
            boolean isCorrect = false;
            for (String dictWord : dictionary) {
                if (word.equals(dictWord)) {
                    isCorrect = true;
                    break;
                }
            }

            originalWords.add(word);
            if (isCorrect) {
                statuses.add("Correct");
                suggestions.add("-");
                distances.add(0);
            } else {
                statuses.add("Misspelled");
                String suggestion = findClosestWord(word, dictionary);
                suggestions.add(suggestion.isEmpty() ? "No suggestion" : suggestion);
                distances.add(suggestion.isEmpty() ? -1 : calculateStringDistance(word, suggestion));
            }
        }

        // Display the spell check report
        displayResults(originalWords, statuses, suggestions, distances);
        scanner.close();
    }

    public static List<String> splitIntoWords(String sentence) {
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            // Consider a character part of a word if it's a letter
            if (c >= 'a' && c <= 'z') {
                currentWord.append(c);
            } else {
                if (currentWord.length() > 0) {
                    words.add(currentWord.toString());
                    currentWord.setLength(0);
                }
            }
        }
        // Add the last word if the sentence doesn't end with a space
        if (currentWord.length() > 0) {
            words.add(currentWord.toString());
        }
        return words;
    }

    public static int calculateStringDistance(String word1, String word2) {
        // If lengths are different, distance is the difference in length (simplification)
        if (word1.length() != word2.length()) {
            return Math.abs(word1.length() - word2.length());
        }
        // If lengths are the same, count differing characters
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

    public static String findClosestWord(String word, String[] dictionary) {
        String bestSuggestion = "";
        int minDistance = Integer.MAX_VALUE;
        int acceptableThreshold = 2;

        for (String dictWord : dictionary) {
            int distance = calculateStringDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                bestSuggestion = dictWord;
            }
        }

        // Return the suggestion only if it's within the acceptable range
        if (minDistance <= acceptableThreshold) {
            return bestSuggestion;
        } else {
            return "";
        }
    }

    public static void displayResults(List<String> words, List<String> statuses, List<String> suggestions, List<Integer> distances) {
        System.out.println("\n--- Spell Check Report ---");
        System.out.printf("%-15s | %-12s | %-15s | %s%n", "Original Word", "Status", "Suggestion", "Distance Score");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < words.size(); i++) {
            String distanceStr = distances.get(i) == -1 ? "N/A" : String.valueOf(distances.get(i));
            System.out.printf("%-15s | %-12s | %-15s | %s%n", words.get(i), statuses.get(i), suggestions.get(i), distanceStr);
        }
    }
}