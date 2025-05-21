package restaurant;

import java.util.List;

public abstract class Dish {
    private String name;
    private List<Ingredient> ingredients;
    private double productionCost;
    private double sellingPrice;

    public Dish(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    // Getters
    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    // Setters protegidos para uso interno o por subclases
    protected void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    protected void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    // Métodos abstractos que las subclases deben implementar
    // Devuelven si puede prepararse con el inventario y preparan la comida descontando ingredientes
    public abstract boolean canBePrepared(List<Ingredient> inventory);

    public abstract void prepare(List<Ingredient> inventory);
}
