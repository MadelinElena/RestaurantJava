package restaurant;

public class Ingredient {
    private String name;
    private double price;
    private int amount;

    public Ingredient(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getAmount() { return amount; }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addAmount(int quantity) {
        this.amount += quantity;
    }

    public void reduceAmount(int quantity) {
        this.amount -= quantity;
    }
}
