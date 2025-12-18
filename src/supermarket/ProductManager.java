package supermarket;

import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        if (findProductById(product.getProductId()) != null) {
            return false;
        }
        products.add(product);
        return true;
    }

    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.println("Total products: " + products.size());
    }

    public Product findProductById(String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    public boolean deleteProductById(String productId) {
        Product product = findProductById(productId);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }
}

