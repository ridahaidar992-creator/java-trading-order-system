package app;

import java.util.Scanner;
import model.Order;
import service.OrderService;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        OrderService orderService = new OrderService();
        
        int orderId = orderService.getNextOrderId();
        int choice = 0;

        System.out.println("Welcome to the Trading Order System!");

        while (choice != 10) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Create Order");
            System.out.println("2. View All Orders");
            System.out.println("3. Search Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Update Order");
            System.out.println("6. Show Statistics");
            System.out.println("7. Sort Orders by Price");
            System.out.println("8. Export Orders to CSV");
            System.out.println("9. Portfolio Summary");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number from 1 to 10.");
                input.nextLine();
                continue;
            }

            if (choice == 1) {
                System.out.print("Enter order type (BUY/SELL): ");
                String orderType = input.nextLine();

                if (!orderType.equalsIgnoreCase("BUY") && !orderType.equalsIgnoreCase("SELL")) {
                    System.out.println("Invalid order type. Only BUY or SELL is allowed.");
                    continue;
                }

                System.out.print("Enter symbol: ");
                String symbol = input.nextLine();

                if (symbol.isEmpty()) {
                    System.out.println("Symbol cannot be empty.");
                    continue;
                }

                int quantity;
                try {
                    System.out.print("Enter quantity: ");
                    quantity = input.nextInt();
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid quantity. Please enter a whole number.");
                    input.nextLine();
                    continue;
                }

                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                    continue;
                }

                double price;
                try {
                    System.out.print("Enter price: ");
                    price = input.nextDouble();
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid price. Please enter a valid number.");
                    input.nextLine();
                    continue;
                }

                if (price <= 0) {
                    System.out.println("Price must be greater than 0.");
                    continue;
                }

                Order order = new Order(orderId, orderType, symbol, quantity, price);
                orderService.addOrder(order);
               
                System.out.println("\nOrder created successfully.");
                order.displayOrderSummary();

                orderId++;
            }
            else if (choice == 2) {
                orderService.viewAllOrders();
            }
            else if (choice == 3) {
                int searchOrderId;
                try {
                    System.out.print("Enter order ID to search: ");
                    searchOrderId = input.nextInt();
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid order ID.");
                    input.nextLine();
                    continue;
                }

                Order foundOrder = orderService.searchOrder(searchOrderId);
                if (foundOrder != null) {
                    foundOrder.displayOrderSummary();
                } else {
                    System.out.println("Order with ID " + searchOrderId + " not found.");
                }
            }
            else if (choice == 4) {
                int deleteOrderId;
                try {
                    System.out.print("Enter order ID to delete: ");
                    deleteOrderId = input.nextInt();
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid order ID.");
                    input.nextLine();
                    continue;
                }

                orderService.deleteOrder(deleteOrderId);
            }
            else if (choice == 5) {
                int updateOrderId;
                try {
                    System.out.print("Enter order ID to update: ");
                    updateOrderId = input.nextInt();
                    input.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid order ID.");
                    input.nextLine();
                    continue;
                }

                Order orderToUpdate = orderService.searchOrder(updateOrderId);

                if (orderToUpdate != null) {
                    System.out.print("Enter new symbol: ");
                    String newSymbol = input.nextLine();

                    if (newSymbol.isEmpty()) {
                        System.out.println("Symbol cannot be empty.");
                        continue;
                    }

                    int newQuantity;
                    try {
                        System.out.print("Enter new quantity: ");
                        newQuantity = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid quantity.");
                        input.nextLine();
                        continue;
                    }

                    if (newQuantity <= 0) {
                        System.out.println("Quantity must be greater than 0.");
                        continue;
                    }

                    double newPrice;
                    try {
                        System.out.print("Enter new price: ");
                        newPrice = input.nextDouble();
                        input.nextLine();
                    } catch (Exception e) {
                        System.out.println("Invalid price.");
                        input.nextLine();
                        continue;
                    }

                    if (newPrice <= 0) {
                        System.out.println("Price must be greater than 0.");
                        continue;
                    }

                    orderService.updateOrder(updateOrderId, newSymbol, newQuantity, newPrice);
                } else {
                    System.out.println("Order with ID " + updateOrderId + " not found.");
                }
            }
            else if (choice == 6) {
                orderService.showStatistics();
            }
            else if (choice == 7) {
                orderService.sortOrdersByPrice();
            }
            else if (choice == 8) {
                orderService.exportOrdersToCSV();
            }
            else if (choice == 9) {
                orderService.showPortfolioSummary();
            }
            else if (choice == 10) {
                System.out.println("System closed.");
            }
            else {
                System.out.println("Invalid choice. Please enter 1, 2, 3, 4, 5, 6, 7, 8, 9, or 10.");
            }
        }

        input.close();
    }
}