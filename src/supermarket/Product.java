package supermarket;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Product {

    private String productId;
    private String productName;
    private LocalDate productEntryDate;
    private int quantity;
    private ActivityQueue activities;

    public Product(String productId, String productName, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productEntryDate = LocalDate.now();
        this.quantity = quantity;
        this.activities = new ActivityQueue();
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public LocalDate getProductEntryDate() {
        return productEntryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public Activity[] getLastActivities() {
        return activities.getAll();
    }

    public void addToStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity to add must be positive.");
        }

        quantity += amount;

        Activity activity = new Activity(
                generateActivityId(),
                "AddToStock",
                amount,
                LocalDateTime.now()
        );

        activities.enqueue(activity);
    }

    public void removeFromStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Quantity to remove must be positive.");
        }

        if (amount > quantity) {
            throw new IllegalArgumentException("Not enough stock available.");
        }

        quantity -= amount;

        Activity activity = new Activity(
                generateActivityId(),
                "RemoveFromStock",
                amount,
                LocalDateTime.now()
        );

        activities.enqueue(activity);
    }

    private int generateActivityId() {
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + productId + '\'' +
                ", Name='" + productName + '\'' +
                ", EntryDate=" + productEntryDate +
                ", Quantity=" + quantity +
                '}';
    }
}

