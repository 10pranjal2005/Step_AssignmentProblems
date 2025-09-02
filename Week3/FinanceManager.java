package AssignmentProblems;
class PersonalAccount {
    private String accountHolderName, accountNumber;
    private double currentBalance, totalIncome, totalExpenses;
    private static int totalAccounts = 0;
    private static String bankName;

    public PersonalAccount(String name, double initialDeposit) {
        accountHolderName = name;
        currentBalance = initialDeposit;
        accountNumber = generateAccountNumber();
        totalAccounts++;
    }

    public void addIncome(double amount, String desc) {
        totalIncome += amount;
        currentBalance += amount;
    }

    public void addExpense(double amount, String desc) {
        if (amount <= currentBalance) {
            totalExpenses += amount;
            currentBalance -= amount;
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println(accountNumber + " | " + accountHolderName +
            " | Balance: " + currentBalance +
            " | Savings: " + calculateSavings());
    }

    public static void setBankName(String name) { bankName = name; }
    public static int getTotalAccounts() { return totalAccounts; }
    private static String generateAccountNumber() {
        return "ACC" + String.format("%03d", totalAccounts + 1);
    }
}

public class FinanceManager {
    public static void main(String[] args) {
        PersonalAccount.setBankName("MyBank");
        PersonalAccount a1 = new PersonalAccount("Alice", 1000);
        PersonalAccount a2 = new PersonalAccount("Bob", 500);
        PersonalAccount a3 = new PersonalAccount("Charlie", 2000);

        a1.addIncome(500, "Salary");
        a2.addExpense(200, "Shopping");
        a3.addIncome(1000, "Freelance");

        a1.displayAccountSummary();
        a2.displayAccountSummary();
        a3.displayAccountSummary();
        System.out.println("Total Accounts: " + PersonalAccount.getTotalAccounts());
    }
}
