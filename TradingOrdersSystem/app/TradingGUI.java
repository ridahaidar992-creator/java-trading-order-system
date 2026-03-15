package app;

import model.Order;
import service.OrderService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TradingGUI extends JFrame {

    private JTextField orderTypeField;
    private JTextField symbolField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField orderIdField;

    private JTextArea outputArea;
    private JLabel statusLabel;

    private OrderService orderService;
    private int nextOrderId;

    public TradingGUI() {
        orderService = new OrderService();
        nextOrderId = orderService.getNextOrderId();

        setTitle("Trading Order Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        UIManager.put("Button.focus", new Color(0, 0, 0, 0));

        Font titleFont = new Font("SansSerif", Font.BOLD, 24);
        Font sectionFont = new Font("SansSerif", Font.BOLD, 15);
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 14);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 13);
        Font outputFont = new Font("Monospaced", Font.PLAIN, 14);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        JLabel titleLabel = new JLabel("Trading Order Management System", SwingConstants.CENTER);
        titleLabel.setFont(titleFont);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(1, 2, 15, 15));
        centerPanel.add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder(null, "Order Entry", TitledBorder.LEFT, TitledBorder.TOP, sectionFont));
        topPanel.add(formPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel orderTypeLabel = new JLabel("Order Type (BUY/SELL):");
        orderTypeLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(orderTypeLabel, gbc);

        orderTypeField = new JTextField();
        orderTypeField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        formPanel.add(orderTypeField, gbc);

        JLabel symbolLabel = new JLabel("Symbol:");
        symbolLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(symbolLabel, gbc);

        symbolField = new JTextField();
        symbolField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        formPanel.add(symbolField, gbc);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(quantityLabel, gbc);

        quantityField = new JTextField();
        quantityField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        formPanel.add(quantityField, gbc);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        formPanel.add(priceLabel, gbc);

        priceField = new JTextField();
        priceField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        formPanel.add(priceField, gbc);

        JLabel orderIdLabel = new JLabel("Order ID:");
        orderIdLabel.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        formPanel.add(orderIdLabel, gbc);

        orderIdField = new JTextField();
        orderIdField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1;
        formPanel.add(orderIdField, gbc);

        JPanel quickInfoPanel = new JPanel(new BorderLayout());
        quickInfoPanel.setBorder(new TitledBorder(null, "Tips", TitledBorder.LEFT, TitledBorder.TOP, sectionFont));
        JTextArea tipsArea = new JTextArea();
        tipsArea.setEditable(false);
        tipsArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tipsArea.setLineWrap(true);
        tipsArea.setWrapStyleWord(true);
        tipsArea.setBackground(getBackground());
        tipsArea.setText(
                "• Use BUY or SELL for order type.\n" +
                "• Quantity must be a whole number.\n" +
                "• Price must be greater than 0.\n" +
                "• Use Order ID for search, delete, and update.\n" +
                "• Symbols are automatically saved in uppercase."
        );
        quickInfoPanel.add(tipsArea, BorderLayout.CENTER);
        topPanel.add(quickInfoPanel);

        JPanel actionPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        actionPanel.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEFT, TitledBorder.TOP, sectionFont));
        centerPanel.add(actionPanel, BorderLayout.CENTER);

        JButton createButton = createStyledButton("Create Order", buttonFont);
        JButton viewButton = createStyledButton("View All Orders", buttonFont);
        JButton searchButton = createStyledButton("Search Order", buttonFont);
        JButton deleteButton = createStyledButton("Delete Order", buttonFont);
        JButton updateButton = createStyledButton("Update Order", buttonFont);
        JButton statisticsButton = createStyledButton("Show Statistics", buttonFont);
        JButton sortButton = createStyledButton("Sort by Price", buttonFont);
        JButton portfolioButton = createStyledButton("Portfolio Summary", buttonFont);
        JButton exportButton = createStyledButton("Export CSV", buttonFont);
        JButton clearButton = createStyledButton("Clear Fields", buttonFont);

        actionPanel.add(createButton);
        actionPanel.add(viewButton);
        actionPanel.add(searchButton);
        actionPanel.add(deleteButton);
        actionPanel.add(updateButton);
        actionPanel.add(statisticsButton);
        actionPanel.add(sortButton);
        actionPanel.add(portfolioButton);
        actionPanel.add(exportButton);
        actionPanel.add(clearButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(outputFont);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setMargin(new Insets(12, 12, 12, 12));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(new TitledBorder(null, "System Output", TitledBorder.LEFT, TitledBorder.TOP, sectionFont));
        scrollPane.setPreferredSize(new Dimension(950, 350));
        centerPanel.add(scrollPane, BorderLayout.SOUTH);

        statusLabel = new JLabel(" Ready");
        statusLabel.setBorder(BorderFactory.createEtchedBorder());
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        createButton.addActionListener(e -> createOrder());
        viewButton.addActionListener(e -> viewOrders());
        searchButton.addActionListener(e -> searchOrder());
        deleteButton.addActionListener(e -> deleteOrder());
        updateButton.addActionListener(e -> updateOrder());
        statisticsButton.addActionListener(e -> showStatistics());
        sortButton.addActionListener(e -> sortOrders());
        portfolioButton.addActionListener(e -> showPortfolioSummary());
        exportButton.addActionListener(e -> exportOrders());
        clearButton.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private JButton createStyledButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setFocusPainted(false);
        return button;
    }

    private void createOrder() {
        try {
            String orderType = orderTypeField.getText().trim().toUpperCase();
            String symbol = symbolField.getText().trim().toUpperCase();
            int quantity = Integer.parseInt(quantityField.getText().trim());
            double price = Double.parseDouble(priceField.getText().trim());

            if (!orderType.equals("BUY") && !orderType.equals("SELL")) {
                setMessage("Invalid order type. Use BUY or SELL.");
                return;
            }

            if (symbol.isEmpty()) {
                setMessage("Symbol cannot be empty.");
                return;
            }

            if (quantity <= 0) {
                setMessage("Quantity must be greater than 0.");
                return;
            }

            if (price <= 0) {
                setMessage("Price must be greater than 0.");
                return;
            }

            Order order = new Order(nextOrderId, orderType, symbol, quantity, price);
            orderService.addOrder(order);
            nextOrderId = orderService.getNextOrderId();

            outputArea.setText(
                    "Order created successfully!\n\n" +
                    formatOrder(order)
            );
            statusLabel.setText(" Order created successfully.");
            clearInputOnly();

        } catch (NumberFormatException ex) {
            setMessage("Quantity must be an integer and price must be a valid number.");
        }
    }

    private void viewOrders() {
        outputArea.setText(orderService.getAllOrdersAsText());
        statusLabel.setText(" Displayed all orders.");
    }

    private void searchOrder() {
        try {
            int orderId = Integer.parseInt(orderIdField.getText().trim());
            Order order = orderService.searchOrder(orderId);

            if (order != null) {
                outputArea.setText("Order found!\n\n" + formatOrder(order));
                statusLabel.setText(" Order found.");
            } else {
                setMessage("Order not found.");
            }

        } catch (NumberFormatException ex) {
            setMessage("Please enter a valid Order ID.");
        }
    }

    private void deleteOrder() {
        try {
            int orderId = Integer.parseInt(orderIdField.getText().trim());
            Order order = orderService.searchOrder(orderId);

            if (order != null) {
                orderService.deleteOrder(orderId);
                nextOrderId = orderService.getNextOrderId();
                outputArea.setText("Order deleted successfully.\n\nDeleted Order ID: " + orderId);
                statusLabel.setText(" Order deleted.");
            } else {
                setMessage("Order not found.");
            }

        } catch (NumberFormatException ex) {
            setMessage("Please enter a valid Order ID.");
        }
    }

    private void updateOrder() {
        try {
            int orderId = Integer.parseInt(orderIdField.getText().trim());
            Order existingOrder = orderService.searchOrder(orderId);

            if (existingOrder == null) {
                setMessage("Order not found.");
                return;
            }

            String newSymbol = symbolField.getText().trim().toUpperCase();
            String quantityText = quantityField.getText().trim();
            String priceText = priceField.getText().trim();

            if (newSymbol.isEmpty()) {
                newSymbol = existingOrder.getSymbol();
            }

            int newQuantity = quantityText.isEmpty() ? existingOrder.getQuantity() : Integer.parseInt(quantityText);
            double newPrice = priceText.isEmpty() ? existingOrder.getPrice() : Double.parseDouble(priceText);

            if (newQuantity <= 0) {
                setMessage("Quantity must be greater than 0.");
                return;
            }

            if (newPrice <= 0) {
                setMessage("Price must be greater than 0.");
                return;
            }

            orderService.updateOrder(orderId, newSymbol, newQuantity, newPrice);

            Order updatedOrder = orderService.searchOrder(orderId);
            outputArea.setText("Order updated successfully!\n\n" + formatOrder(updatedOrder));
            statusLabel.setText(" Order updated.");

        } catch (NumberFormatException ex) {
            setMessage("Please enter valid numeric values for Order ID, Quantity, and Price.");
        }
    }

    private void showStatistics() {
        outputArea.setText(orderService.getStatisticsAsText());
        statusLabel.setText(" Statistics displayed.");
    }

    private void showPortfolioSummary() {
        outputArea.setText(orderService.getPortfolioSummaryAsText());
        statusLabel.setText(" Portfolio summary displayed.");
    }

    private void sortOrders() {
        orderService.sortOrdersByPrice();
        outputArea.setText(orderService.getAllOrdersAsText());
        statusLabel.setText(" Orders sorted by price.");
    }

    private void exportOrders() {
        orderService.exportOrdersToCSV();
        outputArea.setText("Orders exported successfully to orders_export.csv");
        statusLabel.setText(" CSV export completed.");
    }

    private void clearFields() {
        clearInputOnly();
        outputArea.setText("");
        statusLabel.setText(" Fields cleared.");
    }

    private void clearInputOnly() {
        orderTypeField.setText("");
        symbolField.setText("");
        quantityField.setText("");
        priceField.setText("");
        orderIdField.setText("");
    }

    private void setMessage(String message) {
        outputArea.setText(message);
        statusLabel.setText(" " + message);
    }

    private String formatOrder(Order order) {
        return "Order ID: " + order.getOrderId() + "\n" +
               "Order Type: " + order.getOrderType() + "\n" +
               "Symbol: " + order.getSymbol() + "\n" +
               "Quantity: " + order.getQuantity() + "\n" +
               "Price: " + order.getPrice() + "\n" +
               "Total Value: " + order.getTotalValue();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TradingGUI::new);
    }
}