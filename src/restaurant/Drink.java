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
        calculateSellingPrice();
    }

    private void calculateProductionCost() {
        double baseCost = getIngredientsCostSum();

        if (isMilkshake) {
            setProductionCost(baseCost * 1.15);
        } else if (isJuice) {
            setProductionCost(baseCost * 1.10);
        } else {
            setProductionCost(baseCost); // default if neither milkshake nor juice
        }

        if (isAlcoholic) {
            setProductionCost(getProductionCost() + 400);
        }
    }

    private double getIngredientsCostSum() {
        double sum = 0;
        for (Ingredient ingredient : getIngredients()) {
            sum += ingredient.getPrice();
        }
        return sum;
    }

    private void calculateSellingPrice() {
        setSellingPrice(getProductionCost() + 1000);
    }

    @Override
    public boolean canBePrepared(List<Ingredient> inventory) {
        for (Ingredient ingredient : getIngredients()) {
            boolean found = false;
            for (Ingredient invIngredient : inventory) {
                if (invIngredient.getName().equalsIgnoreCase(ingredient.getName())
                        && invIngredient.getAmount() >= ingredient.getAmount()) {
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
        for (Ingredient ingredient : getIngredients()) {
            for (Ingredient invIngredient : inventory) {
                if (invIngredient.getName().equalsIgnoreCase(ingredient.getName())) {
                    invIngredient.setAmount(invIngredient.getAmount() - ingredient.getAmount());
                }
            }
        }
    }
}
