package supermarket;

import java.util.Scanner;

public class Main {

    private static ProductManager productManager = new ProductManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    productManager.displayAllProducts();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    addActivityToProduct();
                    break;
                case 5:
                    displaySortedActivities();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Supermarket Management System ---");
        System.out.println("1. Add new product");
        System.out.println("2. Display all products");
        System.out.println("3. Delete product by ID");
        System.out.println("4. Add activity to product");
        System.out.println("5. Display last four activities (sorted)");
        System.out.println("6. Exit");
    }

    private static void createProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int quantity = readInt("Enter initial quantity: ");
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative.");
            return;
        }

        Product product = new Product(id, name, quantity);

        if (!productManager.addProduct(product)) {
            System.out.println("Error: A product with this ID already exists.");
        } else {
            System.out.println("Product added successfully.");
        }
    }


    private static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        String id = scanner.nextLine();

        System.out.print("Are you sure you want to delete this product? (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();

        if (!confirmation.equals("Y")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        if (productManager.deleteProductById(id)) {
            System.out.println("Product successfully deleted.");
        } else {
            System.out.println("Error: Product not found.");
        }
    }



    private static void addActivityToProduct() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        Product product = productManager.findProductById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("1. Add to stock");
        System.out.println("2. Remove from stock");
        int choice = readInt("Choose activity type: ");

        int amount = readInt("Enter quantity: ");

        try {
            if (choice == 1) {
                product.addToStock(amount);
                System.out.println("Stock added.");
            } else if (choice == 2) {
                product.removeFromStock(amount);
                System.out.println("Stock removed.");
            } else {
                System.out.println("Invalid activity choice.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displaySortedActivities() {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        Product product = productManager.findProductById(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        Activity[] activities = product.getLastActivities();
        if (activities.length == 0) {
            System.out.println("No activities recorded.");
            return;
        }

        bubbleSortByQuantity(activities);

        System.out.println("Last activities sorted by quantity (ascending):");
        for (Activity activity : activities) {
            System.out.println(activity);
        }
    }

    private static void bubbleSortByQuantity(Activity[] activities) {
        for (int i = 0; i < activities.length - 1; i++) {
            for (int j = 0; j < activities.length - 1 - i; j++) {
                if (activities[j].getActivityQuantity() > activities[j + 1].getActivityQuantity()) {
                    Activity temp = activities[j];
                    activities[j] = activities[j + 1];
                    activities[j + 1] = temp;
                }
            }
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

