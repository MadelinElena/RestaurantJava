package restaurant;

import java.util.List;

public class Food extends Dish {
    private boolean hasSugar;
    private boolean isHot;

    public Food(String name, List<Ingredient> ingredients, boolean hasSugar, boolean isHot) {
        super(name, ingredients);
        this.hasSugar = hasSugar;
        this.isHot = isHot;
        calculateProductionCost();
    }

    @Override
    public void calculateProductionCost() {
        double total = ingredients.stream().mapToDouble(i -> i.getPrice()).sum();

        if (isHot) {
            productionCost = total * 1.20;
        } else {
            productionCost = total * 1.10;
        }

        if (hasSugar) {
            productionCost += 400;
        }

        sellingPrice = productionCost + 1000;
    }
}
