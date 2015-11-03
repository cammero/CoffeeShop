package com.cameo;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

    //dealing with magic numbers with help from Matt Rowe
    public static int DRINKNAME = 0;
    public static int COST = 1;
    public static int PRICE = 2;
    public static void main(String[] args) {

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("coffee.txt"));
            //BufferedWriter bufWriter = new BufferedWriter (new FileWriter("dailyCoffeeHouseSales.txt"));
            System.out.println("Coffee Shop Daily Sales Calculator Program");

            //variables that we'll need to calculate and write to the file
            double totalExpenses = 0;
            double totalRevenue = 0;
            double totalProfit = 0;

            //Read each line of the coffee.txt file, use ; as the delimiter. Store the first element of
            // each line as the key in a hashmap, and the second two in an ArrayList as the value of the key.
            HashMap<String, ArrayList> drinkInfo = new HashMap<>();
            ArrayList<Double> costAndPrice;
            String line = bufReader.readLine();
            while (line != null){
                costAndPrice = new ArrayList<>();
                String[] eachDrink = line.split(";");
                costAndPrice.add(Double.parseDouble(eachDrink[COST]));
                costAndPrice.add(Double.parseDouble(eachDrink[PRICE]));
                drinkInfo.put(eachDrink[DRINKNAME], costAndPrice);
                line = bufReader.readLine();
            }
            bufReader.close();

            //create keySet that includes all of the names of the drinks
            Set<String> keySet = drinkInfo.keySet();

            //loop through the keySet, ask user for number of cups of each drink sold, and store in hashMap
            HashMap<String, Integer> numberOfCupsSold = new HashMap<>();
            for (String key : keySet){
                int result = numberOfCups(key);
                numberOfCupsSold.put(key, result);
            }

            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("dailyCoffeeHouseSales.txt"));

            //for every drink (key) get the cost and price and perform calculations. Write them to the file.
            for (String key : keySet){
                ArrayList<Double> drinkArray = drinkInfo.get(key);
                Double cost = drinkArray.get(COST-1);
                Double price = drinkArray.get(PRICE-1);
                int cups = numberOfCupsSold.get(key);
                double expenses = cost * cups;
                double revenue = price * cups;
                double profit = revenue - expenses;
                bufWriter.write(key + ": Sold " + cups + ", Expenses $" + expenses + ", Revenue $" + revenue + ", Profit " + profit+ "\n");
                totalExpenses += expenses;
                totalRevenue += revenue;
                totalProfit += profit;
            }
            bufWriter.write("Totals: Expenses $" + totalExpenses + ", Revenue $" + totalRevenue + ", Profit " + totalProfit);
            bufWriter.close();
            double totalSales = 0;
        }
        catch (IOException ioe){
            System.out.println(ioe.toString());
        }
    }

    private static int numberOfCups(String drink) {
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
}