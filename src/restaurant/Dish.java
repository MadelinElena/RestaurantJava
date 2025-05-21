package restaurant;

import java.util.List;

public abstract class Dish {
    protected String name;
    protected List<Ingredient> ingredients;
    protected double productionCost;
    protected double sellingPrice;

    public Dish(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
        calculateProductionCost();
        this.sellingPrice = productionCost + 1000;
    }

    public abstract void calculateProductionCost();

    public String getName() { return name; }
    public double getProductionCost() { return productionCost; }
    public double getSellingPrice() { return sellingPrice; }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
