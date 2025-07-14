// Tyson Radke
// CSC 422
// 07/10/2025

import java.util.ArrayList;
import java.util.Scanner;

public class PetDatabase {
    private static ArrayList<Pet> pets = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Pet Database Program.");

        do {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            System.out.println("3) Update an existing pet");
            System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit program");
            System.out.print("Your choice: ");
            choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1 -> viewPets();
                case 2 -> addPets(input);
                case 3 -> updatePet(input);
                case 4 -> removePet(input);
                case 5 -> searchByName(input);
                case 6 -> searchByAge(input);
                case 7 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 7);
    }

    public static void viewPets() {
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            System.out.printf("| %-3d | %-10s | %-4d |\n", i, p.getName(), p.getAge());
        }
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", pets.size());
    }

    public static void addPets(Scanner input) {
        int count = 0;
        while (true) {
            System.out.print("add pet (name, age): ");
            String line = input.nextLine();
            if (line.equalsIgnoreCase("done")) {
                break;
            }
            String[] parts = line.split(" ");
            if (parts.length == 2) {
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                pets.add(new Pet(name, age));
                count++;
            } else {
                System.out.println("Invalid input.");
            }
        }
        System.out.printf("%d pets added.\n", count);
    }

    public static void updatePet(Scanner input) {
        viewPets();
        System.out.print("Enter the pet ID to update: ");
        int id = Integer.parseInt(input.nextLine());
        if (id >= 0 && id < pets.size()) {
            Pet pet = pets.get(id);
            System.out.print("Enter new name and new age: ");
            String[] parts = input.nextLine().split(" ");
            if (parts.length == 2) {
                String oldName = pet.getName();
                int oldAge = pet.getAge();
                pet.setName(parts[0]);
                pet.setAge(Integer.parseInt(parts[1]));
                System.out.printf("%s %d changed to %s %d.\n", oldName, oldAge, pet.getName(), pet.getAge());
            } else {
                System.out.println("Invalid input.");
            }
        } else {
            System.out.println("Invalid ID.");
        }
    }

    public static void removePet(Scanner input) {
        viewPets();
        System.out.print("Enter the pet ID to remove: ");
        int id = Integer.parseInt(input.nextLine());
        if (id >= 0 && id < pets.size()) {
            Pet removed = pets.remove(id);
            System.out.printf("%s %d is removed.\n", removed.getName(), removed.getAge());
        } else {
            System.out.println("Invalid ID.");
        }
    }

    public static void searchByName(Scanner input) {
        System.out.print("Enter a name to search: ");
        String name = input.nextLine().toLowerCase();
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.printf("| %-3d | %-10s | %-4d |\n", i, p.getName(), p.getAge());
                count++;
            }
        }
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count);
    }

    public static void searchByAge(Scanner input) {
        System.out.print("Enter age to search: ");
        int age = Integer.parseInt(input.nextLine());
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        int count = 0;
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (p.getAge() == age) {
                System.out.printf("| %-3d | %-10s | %-4d |\n", i, p.getName(), p.getAge());
                count++;
            }
        }
        System.out.println("+----------------------+");
        System.out.printf("%d rows in set.\n", count);
    }
}
