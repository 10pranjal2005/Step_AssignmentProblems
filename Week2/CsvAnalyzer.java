import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> lines = new ArrayList<>();
        System.out.println("Enter CSV data (e.g., Name,Age,Score). Type 'done' to finish:");
        while (true) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("done")) break;
            lines.add(line);
        }
        
        // Parse the CSV data
        List<List<String>> data = parseCsv(lines);
        
        // Format and display the data
        System.out.println("\n--- Formatted Data ---");
        displayFormatted(data);
        
        // Generate a summary report
        System.out.println("\n--- Data Summary Report ---");
        generateSummary(data);

        scanner.close();
    }
    
    public static List<List<String>> parseCsv(List<String> lines) {
        List<List<String>> data = new ArrayList<>();
        for (String line : lines) {
            List<String> row = new ArrayList<>();
            StringBuilder field = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == ',') {
                    row.add(field.toString().trim());
                    field.setLength(0);
                } else {
                    field.append(c);
                }
            }
            row.add(field.toString().trim());
            data.add(row);
        }
        return data;
    }
    
    public static void displayFormatted(List<List<String>> data) {
        if (data.isEmpty()) return;
        // Assuming the first row is the header
        List<String> header = data.get(0);
        StringBuilder headerStr = new StringBuilder("| ");
        for (String col : header) {
            headerStr.append(String.format("%-15s | ", col));
        }
        System.out.println(headerStr);
        
        // Print data rows
        for (int i = 1; i < data.size(); i++) {
            List<String> row = data.get(i);
            StringBuilder rowStr = new StringBuilder("| ");
            for (String cell : row) {
                rowStr.append(String.format("%-15s | ", cell));
            }
            System.out.println(rowStr);
        }
    }
    
    public static void generateSummary(List<List<String>> data) {
        if (data.size() <= 1) {
            System.out.println("Not enough data to generate a report.");
            return;
        }
        int totalRecords = data.size() - 1;
        System.out.println("Total Records Processed: " + totalRecords);
        
        List<String> headers = data.get(0);
        for (int col = 0; col < headers.size(); col++) {
            double sum = 0;
            int numericCount = 0;
            boolean isNumeric = true;
            for (int row = 1; row < data.size(); row++) {
                try {
                    double val = Double.parseDouble(data.get(row).get(col));
                    sum += val;
                    numericCount++;
                } catch (NumberFormatException e) {
                    isNumeric = false;
                    break;
                }
            }
            if (isNumeric && numericCount > 0) {
                System.out.printf("Statistics for '%s': Average = %.2f%n", headers.get(col), sum / numericCount);
            }
        }
    }
}