package restaurant;

import java.util.*;

public class Restaurant {
    private double currentMoney;
    private List<Ingredient> ingredientInventory;
    private List<Dish> dishes;

    public Restaurant() {
        this.currentMoney = 100000;
        this.ingredientInventory = initIngredients();
        this.dishes = initDishes();
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void printMoney() {
        System.out.println(" Current money: $" + currentMoney);
    }

    public void printIngredientInventory() {
        System.out.println(" Ingredient Inventory:");
        for (int i = 0; i < ingredientInventory.size(); i++) {
            Ingredient ing = ingredientInventory.get(i);
            System.out.println(i + ". " + ing.getName() + " - Price: $" + (int) ing.getPrice() + " - Amount: " + ing.getAmount());
        }
    }

    public void buyIngredient(int index, int quantity) {
        if (index < 0 || index >= ingredientInventory.size()) {
            System.out.println("Invalid ingredient index.");
            return;
        }

        Ingredient ing = ingredientInventory.get(index);
        double totalCost = ing.getPrice() * quantity;

        if (currentMoney >= totalCost) {
            ing.addAmount(quantity);
            currentMoney -= totalCost;
            System.out.println("Bought " + quantity + " of " + ing.getName());
        } else {
            System.out.println("Not enough money to buy.");
        }
    }

    public void printDishes() {
        System.out.println("Available Dishes:");
        for (int i = 0; i < dishes.size(); i++) {
            Dish d = dishes.get(i);
            System.out.println(i + ". " + d.getName() + " - Cost: $" + (int) d.getProductionCost() + " - Price: $" + (int) d.getSellingPrice());
        }
    }

    public void sellDish(int index) {
        if (index < 0 || index >= dishes.size()) {
            System.out.println("Invalid dish index.");
            return;
        }

        Dish d = dishes.get(index);
        currentMoney += d.getSellingPrice();
        System.out.println("Sold dish: " + d.getName() + " for $" + (int) d.getSellingPrice());
    }

    private List<Ingredient> initIngredients() {
        return new ArrayList<>(Arrays.asList(
                new Ingredient("Eggs", 28, 68),
                new Ingredient("Rice", 10, 120),
                new Ingredient("Milk", 10, 500),
                new Ingredient("Sugar", 10, 500),
                new Ingredient("Flour", 10, 500),
                new Ingredient("Vodka", 20, 50)
        ));
    }

    private List<Dish> initDishes() {
        List<Dish> initialDishes = new ArrayList<>();

        // Puedes clonar los ingredientes o usar otros (para simplificar aquí reutilizo)
        List<Ingredient> basic = List.of(
                ingredientInventory.get(0), // Eggs
                ingredientInventory.get(3)  // Sugar
        );

        initialDishes.add(new Food("Sweet Rice", basic, true, true));
        initialDishes.add(new Drink("Vodka Shot", List.of(ingredientInventory.get(5)), false, false, true));
        initialDishes.add(new Dessert("Milk Flan", List.of(ingredientInventory.get(2), ingredientInventory.get(3)), false, 250));

        return initialDishes;
    }
}
