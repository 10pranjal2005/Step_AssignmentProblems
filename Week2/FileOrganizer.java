import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileOrganizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> fileNames = new ArrayList<>();
        
        System.out.println("Enter file names (e.g., 'report.docx', 'photo1.jpg'). Type 'done' to finish:");
        while(true) {
            String fileName = scanner.nextLine();
            if (fileName.equalsIgnoreCase("done")) break;
            fileNames.add(fileName);
        }

        // Process files and display the report
        displayOrganizationReport(fileNames);
        scanner.close();
    }
    
    public static String[] extractFileComponents(String fullFileName) {
        int dotIndex = fullFileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == 0) {
            return new String[]{fullFileName, ""}; // No extension
        }
        String name = fullFileName.substring(0, dotIndex);
        String extension = fullFileName.substring(dotIndex + 1);
        return new String[]{name, extension};
    }
    
    public static String categorizeFile(String extension) {
        switch (extension.toLowerCase()) {
            case "txt": case "doc": case "docx": case "pdf":
                return "Documents";
            case "jpg": case "jpeg": case "png": case "gif":
                return "Images";
            case "mp3": case "wav": case "aac":
                return "Audio";
            case "mp4": case "mov": case "avi":
                return "Videos";
            default:
                return "Unknown";
        }
    }
    
    public static String generateNewName(String category, String originalName, String extension) {
        StringBuilder newName = new StringBuilder();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        newName.append(category).append("_").append(date).append("_").append(originalName);
        if (!extension.isEmpty()) {
            newName.append(".").append(extension);
        }
        return newName.toString();
    }
    
    public static void displayOrganizationReport(List<String> fileNames) {
        System.out.println("\n--- File Organization Report ---");
        System.out.printf("%-25s | %-15s | %s%n", "Original Filename", "Category", "Suggested New Name");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        Map<String, Integer> categoryCounts = new HashMap<>();
        
        for (String fileName : fileNames) {
            String[] components = extractFileComponents(fileName);
            String name = components[0];
            String ext = components[1];
            
            String category = categorizeFile(ext);
            String newName = generateNewName(category, name, ext);
            
            System.out.printf("%-25s | %-15s | %s%n", fileName, category, newName);
            
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }
        
        System.out.println("\n--- Organization Statistics ---");
        System.out.println("Category-wise File Counts:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.printf("- %s: %d file(s)%n", entry.getKey(), entry.getValue());
        }
    }
}