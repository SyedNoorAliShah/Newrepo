import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Product {
    String name;
    double price;
    
    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class ECommerceSystem {
    private JFrame frame;
    private DefaultListModel<Product> productList;
    private DefaultListModel<Product> cartList;
    
    ECommerceSystem() {
        frame = new JFrame("E-Commerce System");
        productList = new DefaultListModel<>();
        cartList = new DefaultListModel<>();
        
        productList.addElement(new Product("Laptop", 999.99));
        productList.addElement(new Product("Phone", 499.99));
        productList.addElement(new Product("Headphones", 79.99));
        
        JList<Product> productListView = new JList<>(productList);
        JList<Product> cartListView = new JList<>(cartList);
        
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = productListView.getSelectedValue();
                if (selectedProduct != null) {
                    cartList.addElement(selectedProduct);
                }
            }
        });
        
        JButton removeFromCartButton = new JButton("Remove from Cart");
        removeFromCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product selectedProduct = cartListView.getSelectedValue();
                if (selectedProduct != null) {
                    cartList.removeElement(selectedProduct);
                }
            }
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                for (int i = 0; i < cartList.getSize(); i++) {
                    total += cartList.getElementAt(i).price;
                }
                JOptionPane.showMessageDialog(frame, "Total: $" + total);
            }
        });
        
        frame.setLayout(new GridLayout(2, 2));
        frame.add(new JScrollPane(productListView));
        frame.add(new JScrollPane(cartListView));
        frame.add(addToCartButton);
        frame.add(removeFromCartButton);
        frame.add(checkoutButton);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new ECommerceSystem();
    }
}