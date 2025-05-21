package restaurant;

import java.util.List;

public class Dessert extends Dish {
    private boolean isCold;
    private double weight;

    public Dessert(String name, List<Ingredient> ingredients, boolean isCold, double weight) {
        super(name, ingredients);
        this.isCold = isCold;
        this.weight = weight;

        // Suponiendo que calculas costos y precio aquí:
        double cost = calculateCost(ingredients);
        setProductionCost(cost);
        setSellingPrice(cost * 2);  // Por ejemplo, margen 100%
    }

    private double calculateCost(List<Ingredient> ingredients) {
        double total = 0;
        for (Ingredient ing : ingredients) {
            total += ing.getPrice();
        }
        return total;
    }

    @Override
    public boolean canBePrepared(List<Ingredient> inventory) {
        for (Ingredient needed : getIngredients()) {
            boolean found = false;
            for (Ingredient inv : inventory) {
                if (inv.getName().equalsIgnoreCase(needed.getName()) && inv.getAmount() >= needed.getAmount()) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    @Override
    public void prepare(List<Ingredient> inventory) {
        if (!canBePrepared(inventory)) {
            System.out.println("No se puede preparar el postre: " + getName());
            return;
        }
        // descontar ingredientes del inventario
        for (Ingredient needed : getIngredients()) {
            for (Ingredient inv : inventory) {
                if (inv.getName().equalsIgnoreCase(needed.getName())) {
                    inv.setAmount(inv.getAmount() - needed.getAmount());
                }
            }
        }
        System.out.println("Preparado el postre: " + getName());
    }
}
