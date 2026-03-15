
package model;

public class Order {

    private int orderId;
    private String orderType;
    private String symbol;
    private int quantity;
    private double price;
    private double totalValue;

    public Order(int orderId, String orderType, String symbol, int quantity, double price) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.totalValue = quantity * price;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalValue = this.quantity * this.price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.totalValue = this.quantity * this.price;
    }

    public void displayOrderSummary() {
        System.out.println("\n---- ORDER SUMMARY ----");
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Type: " + orderType);
        System.out.println("Symbol: " + symbol);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: " + price);
        System.out.println("Total Value: " + totalValue);
    }
}