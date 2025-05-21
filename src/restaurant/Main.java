package restaurant;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to the Restaurant Manager");

        do {
            System.out.println("\nMenu:\n1. Show current money\n2. Show ingredients\n3. Buy ingredients\n4. Show dishes\n5. Sell dish\n6. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> restaurant.printCurrentMoney();
                case 2 -> restaurant.printIngredientInventory();
                case 3 -> {
                    restaurant.printIngredientInventory();
                    System.out.print("Enter ingredient index: ");
                    int idx = scanner.nextInt();
                    System.out.print("Enter quantity to buy: ");
                    int qty = scanner.nextInt();
                    restaurant.buyIngredient(idx, qty);  // Cambiado para que reciba índice en vez de nombre
                }
                case 4 -> restaurant.printDishes();
                case 5 -> {
                    restaurant.printDishes();
                    System.out.print("Enter dish index to sell: ");
                    int idx = scanner.nextInt();
                    restaurant.sellDish(idx);
                }
                case 6 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid option.");
            }
        } while (choice != 6);
    }
}
