package AssignmentProblems;
class Product {
    String productId, productName, category;
    double price;
    int stockQuantity;
    static int totalProducts = 0;

    public Product(String name, double price, String category, int qty) {
        this.productId = "P" + (++totalProducts);
        this.productName = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = qty;
    }
}

class ShoppingCart {
    String cartId, customerName;
    Product[] products = new Product[10];
    int[] quantities = new int[10];
    int count = 0;

    public ShoppingCart(String customer) {
        this.cartId = "CART" + customer;
        this.customerName = customer;
    }

    public void addProduct(Product p, int qty) {
        if (p.stockQuantity >= qty) {
            products[count] = p;
            quantities[count] = qty;
            p.stockQuantity -= qty;
            count++;
        }
    }

    public void displayCart() {
        double total = 0;
        System.out.println("Cart for " + customerName);
        for (int i = 0; i < count; i++) {
            double cost = products[i].price * quantities[i];
            total += cost;
            System.out.println(products[i].productName + " x" + quantities[i] + " = " + cost);
        }
        System.out.println("Total: " + total);
    }
}

public class ShoppingSystem {
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 50000, "Electronics", 5);
        Product p2 = new Product("Phone", 20000, "Electronics", 10);

        ShoppingCart cart = new ShoppingCart("Alice");
        cart.addProduct(p1, 1);
        cart.addProduct(p2, 2);

        cart.displayCart();
    }
}
