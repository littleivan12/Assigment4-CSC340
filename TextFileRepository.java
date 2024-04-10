import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public crass TextFileRepository {
    private final String filePath;

    public TextFileRepository(String filePath) {
        this.filePath = filePath;
    }
// Creating classes for menu options functions . 
    public void create(String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void update(int index, String newData) {
        List<String> lines = readAll();
        if (index >= 0 && index < lines.size()) {
            lines.set(index, newData);
            writeAll(lines);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void delete(int index) {
        List<String> lines = readAll();
        if (index >= 0 && index < lines.size()) {
            lines.remove(index);
            writeAll(lines);
        } else {
            System.out.println("Invalid index");
        }
    }

    private void writeAll(List<String> lines) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // creating scanner and text file to hold the content
        Scanner sc = new Scanner(System.in);
        TextFileRepository repository = new TextFileRepository("data.txt");
// Create loop to access menu options until the user exits the program
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Create new data");
            System.out.println("2. Read existing data");
            System.out.println("3. Update existing data");
            System.out.println("4. Delete existing data");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            
            switch (choice) {

                // Enter new data
                case 1:
                     System.out.print("Enter new data: ");
                    String newData = sc.nextLine();
                    repository.create(newData);
                    break;

                // Read Data that Exist
                case 2:
                    List<String> allData = repository.readAll();
                    System.out.println("Existing data:");
                    for (int i = 0; i < allData.size(); i++) {
                        System.out.println(i + ": " + allData.get(i));
                    }
                    break;

                // Update data
                case 3:
                    System.out.print("Enter index of data to update: ");
                    int indexToUpdate = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter new data: ");
                    String updatedData = sc.nextLine();
                    repository.update(indexToUpdate, updatedData);
                    break;
                //Delete Data
                case 4:
                    System.out.print("Enter index of data to delete: ");
                    int indexToDelete = sc.nextInt();
                    sc.nextLine(); 
                    repository.delete(indexToDelete);
                    break;

                // Exit the program
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
