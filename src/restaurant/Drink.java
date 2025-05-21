package restaurant;

import java.util.List;

public class Drink extends Dish {
    private boolean isMilkshake;
    private boolean isJuice;
    private boolean isAlcoholic;

    public Drink(String name, List<Ingredient> ingredients, boolean isMilkshake, boolean isJuice, boolean isAlcoholic) {
        super(name, ingredients);
        this.isMilkshake = isMilkshake;
        this.isJuice = isJuice;
        this.isAlcoholic = isAlcoholic;
        calculateProductionCost();
    }

    @Override
    public void calculateProductionCost() {
        double total = ingredients.stream().mapToDouble(i -> i.getPrice()).sum();

        if (isMilkshake) {
            productionCost = total * 1.15;
        } else if (isJuice) {
            productionCost = total * 1.10;
        } else {
            productionCost = total;
        }

        if (isAlcoholic) {
            productionCost += 400;
        }

        sellingPrice = productionCost + 1000;
    }
}
