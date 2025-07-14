
import java.io.*;
import java.util.*;

public class PetDatabase {
    private static final int MAX_PETS = 5;
    private static final String FILE_NAME = "pets.txt";
    private static final Scanner input = new Scanner(System.in);
    private static final List<Pet> pets = new ArrayList<>();

    public static void main(String[] args) {
        loadPets();

        while (true) {
            System.out.println("\nPet Database Program.");
            System.out.println("1) View all pets");
            System.out.println("2) Add new pets");
            System.out.println("3) Remove a pet");
            System.out.println("4) Exit program");
            System.out.print("Your choice: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    showPets();
                    break;
                case "2":
                    addPets();
                    break;
                case "3":
                    removePet();
                    break;
                case "4":
                    savePets();
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void loadPets() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null && pets.size() < MAX_PETS) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    pets.add(new Pet(name, age));
                }
            }
        } catch (IOException e) {
        }
    }

    private static void savePets() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                writer.println(pet.getName() + " " + pet.getAge());
            }
        } catch (IOException e) {
            System.out.println("Error saving pets.");
        }
    }

    private static void showPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            System.out.printf("| %-2d | %-9s | %-3d |\n", i, pets.get(i).getName(), pets.get(i).getAge());
        }
        System.out.println("+----------------------+");
        System.out.println(pets.size() + " rows in set.");
    }

    private static void addPets() {
        int added = 0;
        while (pets.size() < MAX_PETS) {
            System.out.print("add pet (name, age): ");
            String line = input.nextLine().trim();

            if (line.equalsIgnoreCase("done")) break;

            String[] parts = line.split(" ");
            if (parts.length != 2) {
                System.out.println("Error: " + line + " is not a valid input.");
                continue;
            }

            String name = parts[0];
            int age;
            try {
                age = Integer.parseInt(parts[1]);
                if (age < 1 || age > 20) {
                    System.out.println("Error: " + age + " is not a valid age.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: " + line + " is not a valid input.");
                continue;
            }

            pets.add(new Pet(name, age));
            added++;

            if (pets.size() == MAX_PETS) {
                System.out.println("Error: Database is full.");
                break;
            }
        }
        System.out.println(added + " pets added.");
    }

    private static void removePet() {
        showPets();
        System.out.print("Enter the pet ID to remove: ");
        String inputId = input.nextLine().trim();
        try {
            int id = Integer.parseInt(inputId);
            if (id < 0 || id >= pets.size()) {
                System.out.println("Error: ID " + id + " does not exist.");
                return;
            }
            Pet removed = pets.remove(id);
            System.out.println(removed.getName() + " " + removed.getAge() + " is removed.");
        } catch (NumberFormatException e) {
            System.out.println("Error: " + inputId + " is not a valid ID.");
        }
    }

    static class Pet {
        private final String name;
        private final int age;

        public Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }
}