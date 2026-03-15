package service;

import model.Order;
import repository.OrderRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class OrderService {

    private ArrayList<Order> orders;
    private OrderRepository orderRepository;

    public OrderService() {
        orderRepository = new OrderRepository();
        orders = orderRepository.loadOrders();
    }

    public void addOrder(Order order) {
        orders.add(order);
        orderRepository.saveOrders(orders);
    }

    public void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("\nNo orders stored yet.");
        } else {
            System.out.println("\n===== ALL ORDERS =====");
            for (Order order : orders) {
                order.displayOrderSummary();
            }
        }
    }

    public String getAllOrdersAsText() {
        if (orders.isEmpty()) {
            return "No orders stored yet.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("===== ALL ORDERS =====\n\n");

        for (Order order : orders) {
            sb.append("Order ID: ").append(order.getOrderId()).append("\n");
            sb.append("Order Type: ").append(order.getOrderType()).append("\n");
            sb.append("Symbol: ").append(order.getSymbol()).append("\n");
            sb.append("Quantity: ").append(order.getQuantity()).append("\n");
            sb.append("Price: ").append(order.getPrice()).append("\n");
            sb.append("Total Value: ").append(order.getTotalValue()).append("\n");
            sb.append("-----------------------------\n");
        }

        return sb.toString();
    }

    public Order searchOrder(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public void deleteOrder(int orderId) {
        Order orderToRemove = searchOrder(orderId);

        if (orderToRemove != null) {
            orders.remove(orderToRemove);
            orderRepository.saveOrders(orders);
        }
    }

    public void updateOrder(int orderId, String newSymbol, int newQuantity, double newPrice) {
        Order orderToUpdate = searchOrder(orderId);

        if (orderToUpdate != null) {
            orderToUpdate.setSymbol(newSymbol);
            orderToUpdate.setQuantity(newQuantity);
            orderToUpdate.setPrice(newPrice);
            orderRepository.saveOrders(orders);
        }
    }

    public int getNextOrderId() {
        int maxId = 1000;

        for (Order order : orders) {
            if (order.getOrderId() > maxId) {
                maxId = order.getOrderId();
            }
        }

        return maxId + 1;
    }

    public void sortOrdersByPrice() {
        Collections.sort(orders, Comparator.comparingDouble(Order::getPrice));
        orderRepository.saveOrders(orders);
    }

    public void exportOrdersToCSV() {
        orderRepository.exportOrdersToCSV(orders);
    }

    public void showStatistics() {
        System.out.println(getStatisticsAsText());
    }

    public String getStatisticsAsText() {
        int totalOrders = orders.size();
        int buyOrders = 0;
        int sellOrders = 0;
        int totalQuantity = 0;
        double highestValue = 0;

        for (Order order : orders) {
            if (order.getOrderType().equalsIgnoreCase("BUY")) {
                buyOrders++;
            } else if (order.getOrderType().equalsIgnoreCase("SELL")) {
                sellOrders++;
            }

            totalQuantity += order.getQuantity();

            if (order.getTotalValue() > highestValue) {
                highestValue = order.getTotalValue();
            }
        }

        return "===== ORDER STATISTICS =====\n\n" +
               "Total Orders: " + totalOrders + "\n" +
               "BUY Orders: " + buyOrders + "\n" +
               "SELL Orders: " + sellOrders + "\n" +
               "Total Quantity: " + totalQuantity + "\n" +
               "Highest Order Value: " + highestValue;
    }

    public void showPortfolioSummary() {
        System.out.println(getPortfolioSummaryAsText());
    }

    public String getPortfolioSummaryAsText() {
        HashMap<String, Integer> portfolio = new HashMap<>();

        for (Order order : orders) {
            String symbol = order.getSymbol();
            int quantity = order.getQuantity();

            if (order.getOrderType().equalsIgnoreCase("BUY")) {
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
            } else if (order.getOrderType().equalsIgnoreCase("SELL")) {
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) - quantity);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("===== PORTFOLIO SUMMARY =====\n\n");

        if (portfolio.isEmpty()) {
            sb.append("No portfolio data available.");
        } else {
            for (String symbol : portfolio.keySet()) {
                sb.append(symbol).append(" : ").append(portfolio.get(symbol)).append("\n");
            }
        }

        return sb.toString();
    }
}