package com.cameo;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Coffee Shop Sales Calculator Program");

        double totalSales = 0;

        String coffee = "coffee";
        int cups = numberOfCups(coffee);
        double price = priceOfDrink(coffee);
        double drinkSales = sales(cups, price);
        totalSales = totalSales + drinkSales;

        String hotCoco = "hot chocolate";
        totalSales += sales (numberOfCups(hotCoco), priceOfDrink(hotCoco));

        String tea = "tea";
        totalSales += sales (numberOfCups(tea), priceOfDrink(tea));

        String capp = "cappuccino";
        totalSales += sales (numberOfCups(capp), priceOfDrink(capp));

        System.out.println("Total sales for the day are $"  + totalSales);
    }

    private static int numberOfCups(String drink) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many cups of " + drink + " did you sell today?");
        int cups = scanner.nextInt();
        if (cups < 0) {
            System.out.println("You have not entered a valid number of drinks sold. Your totals will not be accurate.");
        }
        return cups;
    }

    private static double priceOfDrink(String drink) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What does a cup of " + drink + " cost?");
        double price = scanner.nextDouble();
        if (price <= 0){
            System.out.println("You have not entered a valid price. Your totals will not be accurate.");
        }
        return price;
    }

    private static double sales(int numberOfItems, double price) {
        double dailySales = numberOfItems * price;
        return dailySales;
    }
}