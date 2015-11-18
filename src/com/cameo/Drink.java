package com.cameo;
import java.util.Scanner;

/**
 * Created by Cameo on 11/2/2015.
 */
//I named the class "Drink" instead of "Coffee"
public class Drink {

    private String name;
    private double cost;
    private double price;
    private int numSold;

    public Drink(String name, double cost, double price){
        this.name = name;
        this.price = price;
        this.cost = cost;
    }

    public double dailyExpensesMethod(double cups) {
        double dailyExpenses = cost * cups;
        return dailyExpenses;
    }

    public double dailyRevenueMethod(double cups) {
        double dailyRevenue = price * cups;
        return dailyRevenue;
    }

    public double dailyProfitMethod(double revenue, double expenses) {
        double dailyProfit = revenue - expenses;
        return dailyProfit;
    }

    public static int numberOfCupsSold(String drink) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many cups of " + drink + " did you sell today?");
        String cups = scanner.nextLine();
        while (true) {
            try {
                //make sure a valid non-negative number has been entered.
                int numCups = Integer.parseInt(cups);
                if (numCups < 0) {
                    System.out.println("Please enter a valid number of cups.");
                    cups = scanner.nextLine();
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid number of cups.");
                cups = scanner.nextLine();
            }
        }
        Integer numberOfCups = Integer.parseInt(cups);

        return numberOfCups;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNumSold() {
        return numSold;
    }
    public void setNumSold(int numSold) {
        this.numSold = numSold;
    }
}
