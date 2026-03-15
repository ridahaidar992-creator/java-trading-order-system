package repository;

import model.Order;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OrderRepository {

    private final String FILE_NAME = "orders.txt";

    public void saveOrders(ArrayList<Order> orders) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);

            for (Order order : orders) {
                writer.write(
                    order.getOrderId() + "," +
                    order.getOrderType() + "," +
                    order.getSymbol() + "," +
                    order.getQuantity() + "," +
                    order.getPrice() + "\n"
                );
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving orders.");
        }
    }

    public ArrayList<Order> loadOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return orders;
            }

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    int orderId = Integer.parseInt(parts[0]);
                    String orderType = parts[1];
                    String symbol = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);

                    Order order = new Order(orderId, orderType, symbol, quantity, price);
                    orders.add(order);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Orders file not found.");
        }

        return orders;
    }

    public void exportOrdersToCSV(ArrayList<Order> orders) {
        try {
            FileWriter writer = new FileWriter("orders_export.csv");

            writer.write("OrderID,OrderType,Symbol,Quantity,Price,TotalValue\n");

            for (Order order : orders) {
                writer.write(
                    order.getOrderId() + "," +
                    order.getOrderType() + "," +
                    order.getSymbol() + "," +
                    order.getQuantity() + "," +
                    order.getPrice() + "," +
                    order.getTotalValue() + "\n"
                );
            }

            writer.close();
            System.out.println("Orders exported to orders_export.csv");
        } catch (IOException e) {
            System.out.println("Error exporting orders.");
        }
    }
}