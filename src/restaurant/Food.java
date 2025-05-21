package restaurant;

import java.util.List;

public class Food extends Dish {
    private boolean isHot;
    private boolean containsSugar;

    public Food(String name, List<Ingredient> ingredients, boolean isHot, boolean containsSugar) {
        super(name, ingredients);
        this.isHot = isHot;
        this.containsSugar = containsSugar;
        calculateCosts();
    }

    private void calculateCosts() {
        double sumIngredients = 0;
        for (Ingredient ing : getIngredients()) {
            sumIngredients += ing.getPrice();
        }

        double cost = sumIngredients * 1.10; // 110% base

        if (containsSugar) {
            cost += 400;
        }
        if (isHot) {
            cost = sumIngredients * 1.20; // 120% if hot overrides 110%
        }

        setProductionCost(cost);
        setSellingPrice(cost + 1000);
    }

    // Implementación del método abstracto canBePrepared
    @Override
    public boolean canBePrepared(List<Ingredient> inventory) {
        // Verifica que haya suficiente cantidad de cada ingrediente
        for (Ingredient ingDish : getIngredients()) {
            boolean found = false;
            for (Ingredient ingInv : inventory) {
                if (ingInv.getName().equalsIgnoreCase(ingDish.getName()) && ingInv.getAmount() >= ingDish.getAmount()) {
                    found = true;
                    break;
                }
            }
            if (!found) return false;
        }
        return true;
    }

    // Implementación del método abstracto prepare
    @Override
    public void prepare(List<Ingredient> inventory) {
        // Descuenta la cantidad usada en la preparación del inventario
        for (Ingredient ingDish : getIngredients()) {
            for (Ingredient ingInv : inventory) {
                if (ingInv.getName().equalsIgnoreCase(ingDish.getName())) {
                    ingInv.setAmount(ingInv.getAmount() - ingDish.getAmount());
                }
            }
        }
    }
}
