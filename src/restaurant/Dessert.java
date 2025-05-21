package restaurant;

import java.util.List;

public class Dessert extends Dish {
    private boolean isHot;
    private int calories;

    public Dessert(String name, List<Ingredient> ingredients, boolean isHot, int calories) {
        super(name, ingredients);
        this.isHot = isHot;
        this.calories = calories;
        calculateProductionCost();
    }

    @Override
    public void calculateProductionCost() {
        double total = ingredients.stream().mapToDouble(i -> i.getPrice()).sum();

        if (isHot) {
            productionCost = total * 1.20;
        } else {
            productionCost = total * 1.12;
        }

        sellingPrice = productionCost + 1000;
    }

    public int getCalories() {
        return calories;
    }
}
