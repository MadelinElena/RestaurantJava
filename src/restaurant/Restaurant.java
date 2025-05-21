package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private double currencyMoney;
    private List<Ingredient> ingredientInventory;
    private List<Dish> dishes;

    public Restaurant() {
        this.currencyMoney = 100000; // $100,000 inicial
        this.ingredientInventory = initIngredients();
        this.dishes = initDishes();
    }

    // Inicializa lista de ingredientes con cantidades y precios
    private List<Ingredient> initIngredients() {
        List<Ingredient> list = new ArrayList<>();
        list.add(new Ingredient("Eggs", 28, 68));
        list.add(new Ingredient("Rice", 10, 120));
        list.add(new Ingredient("Milk", 10, 500));
        list.add(new Ingredient("Sugar", 10, 500));
        list.add(new Ingredient("Flour", 10, 500));
        list.add(new Ingredient("Salt", 10, 500));
        list.add(new Ingredient("Vodka", 50, 100));
        list.add(new Ingredient("Beer", 60, 120));
        list.add(new Ingredient("Tequila", 70, 150));
        return list;
    }

    // Inicializa platos de ejemplo
    private List<Dish> initDishes() {
        List<Dish> list = new ArrayList<>();

        // Ingredientes para Food
        List<Ingredient> foodIngredients1 = new ArrayList<>();
        foodIngredients1.add(getIngredientByName("Eggs"));
        foodIngredients1.add(getIngredientByName("Rice"));
        foodIngredients1.add(getIngredientByName("Sugar"));
        Food food1 = new Food("Fried Rice", foodIngredients1, true, false);

        List<Ingredient> foodIngredients2 = new ArrayList<>();
        foodIngredients2.add(getIngredientByName("Eggs"));
        foodIngredients2.add(getIngredientByName("Salt"));
        Food food2 = new Food("Boiled Eggs", foodIngredients2, false, false);

        // Ingredientes para Drink
        List<Ingredient> drinkIngredients1 = new ArrayList<>();
        drinkIngredients1.add(getIngredientByName("Milk"));
        Drink drink1 = new Drink("Milkshake", drinkIngredients1, true, false, false);

        List<Ingredient> drinkIngredients2 = new ArrayList<>();
        drinkIngredients2.add(getIngredientByName("Vodka"));
        drinkIngredients2.add(getIngredientByName("Sugar"));
        Drink drink2 = new Drink("Vodka Mix", drinkIngredients2, false, false, true);

        // Ingredientes para Dessert
        List<Ingredient> dessertIngredients1 = new ArrayList<>();
        dessertIngredients1.add(getIngredientByName("Sugar"));
        dessertIngredients1.add(getIngredientByName("Flour"));
        Dessert dessert1 = new Dessert("Cold Cake", dessertIngredients1, true, 350);

        List<Ingredient> dessertIngredients2 = new ArrayList<>();
        dessertIngredients2.add(getIngredientByName("Sugar"));
        dessertIngredients2.add(getIngredientByName("Flour"));
        Dessert dessert2 = new Dessert("Hot Pie", dessertIngredients2, false, 500);

        list.add(food1);
        list.add(food2);
        list.add(drink1);
        list.add(drink2);
        list.add(dessert1);
        list.add(dessert2);

        return list;
    }

    // Busca un ingrediente por nombre
    private Ingredient getIngredientByName(String name) {
        for (Ingredient ing : ingredientInventory) {
            if (ing.getName().equalsIgnoreCase(name)) {
                return ing;
            }
        }
        return null;
    }

    // Imprime el dinero actual
    public void printCurrentMoney() {
        System.out.println("Current money: $" + (int) currencyMoney);
    }

    // Imprime inventario de ingredientes
    public void printIngredientInventory() {
        System.out.println("Ingredients Inventory:");
        for (int i = 0; i < ingredientInventory.size(); i++) {
            Ingredient ing = ingredientInventory.get(i);
            System.out.println(i + ". " + ing.getName() + " - Price: $" + ing.getPrice() + ", Amount: " + ing.getAmount());
        }
    }

    // Imprime lista de platos
    public void printDishes() {
        if (dishes.isEmpty()) {
            System.out.println("No dishes available.");
            return;
        }
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            // Aquí corriges el getPrice() por getSellingPrice()
            System.out.println(i + ". " + dish.getName() + " - Price: $" + (int) dish.getSellingPrice());
        }
    }

    // Compra ingredientes: recibe índice y cantidad
    public void buyIngredient(int ingredientIndex, int amount) {
        if (ingredientIndex < 0 || ingredientIndex >= ingredientInventory.size()) {
            System.out.println("Invalid ingredient selection.");
            return;
        }
        Ingredient ingredient = ingredientInventory.get(ingredientIndex);
        double totalPrice = ingredient.getPrice() * amount;
        if (totalPrice > currencyMoney) {
            System.out.println("Not enough money to buy.");
            return;
        }
        ingredient.setAmount(ingredient.getAmount() + amount);
        currencyMoney -= totalPrice;
        System.out.println("Bought " + amount + " of " + ingredient.getName());
    }

    // Verifica si se puede preparar plato
    private boolean canPrepareDish(Dish dish) {
        for (Ingredient ingNeeded : dish.getIngredients()) {
            Ingredient inventoryIng = getIngredientByName(ingNeeded.getName());
            if (inventoryIng == null || inventoryIng.getAmount() < 1) {
                return false;
            }
        }
        return true;
    }

    // Prepara plato (reduce ingredientes)
    public void makeDish(int dishIndex) {
        if (dishIndex < 0 || dishIndex >= dishes.size()) {
            System.out.println("Invalid dish selection.");
            return;
        }
        Dish dish = dishes.get(dishIndex);
        if (canPrepareDish(dish)) {
            for (Ingredient ingNeeded : dish.getIngredients()) {
                Ingredient inventoryIng = getIngredientByName(ingNeeded.getName());
                inventoryIng.setAmount(inventoryIng.getAmount() - 1);
            }
            System.out.println("Dish prepared: " + dish.getName());
        } else {
            System.out.println("Not enough ingredients to prepare " + dish.getName());
        }
    }

    // Vende plato, suma dinero
    public void sellDish(int dishIndex) {
        if (dishIndex < 0 || dishIndex >= dishes.size()) {
            System.out.println("Invalid dish selection.");
            return;
        }
        Dish dish = dishes.get(dishIndex);
        currencyMoney += dish.getSellingPrice();
        System.out.println("Sold dish: " + dish.getName() + ", gained $" + dish.getSellingPrice());
    }
}
