import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Product class to represent items for sale
class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return name + " - $" + price;
    }
}

// Main GUI application
public class OnlineShoppingApp {
    private JFrame frame;
    private JComboBox<Product> productBox;
    private JTextArea cartArea;
    private ArrayList<Product> cart = new ArrayList<>();

    public OnlineShoppingApp() {
        // Create frame
        frame = new JFrame("Online Shopping System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Product list
        Product[] products = {
            new Product("Laptop", 999.99),
            new Product("Phone", 499.99),
            new Product("Headphones", 79.99),
            new Product("Keyboard", 49.99),
            new Product("Mouse", 29.99)
        };

        // Product selection panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        productBox = new JComboBox<>(products);
        JButton addToCartBtn = new JButton("Add to Cart");

        topPanel.add(new JLabel("Select Product:"));
        topPanel.add(productBox);
        topPanel.add(addToCartBtn);

        frame.add(topPanel, BorderLayout.NORTH);

        // Cart display
        cartArea = new JTextArea(10, 40);
        cartArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Checkout button
        JPanel bottomPanel = new JPanel();
        JButton checkoutBtn = new JButton("Checkout");
        bottomPanel.add(checkoutBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        addToCartBtn.addActionListener(e -> {
            Product selectedProduct = (Product) productBox.getSelectedItem();
            if (selectedProduct != null) {
                cart.add(selectedProduct);
                updateCartArea();
            }
        });

        checkoutBtn.addActionListener(e -> {
            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Your cart is empty!");
            } else {
                double total = cart.stream().mapToDouble(p -> p.price).sum();
                JOptionPane.showMessageDialog(frame, "Total Price: $" + total);
                cart.clear();
                updateCartArea();
            }
        });

        frame.setVisible(true);
    }

    // Refresh cart display
    private void updateCartArea() {
        cartArea.setText("Cart Contents:\n");
        for (Product p : cart) {
            cartArea.append(p + "\n");
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineShoppingApp::new);
    }
}
