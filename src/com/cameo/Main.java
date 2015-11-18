package com.cameo;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("coffee.txt"));
            System.out.println("Coffee Shop Daily Sales Calculator Program");

            //variables that we'll need to calculate and write totals to the file
            double totalExpenses = 0;
            double totalRevenue = 0;
            double totalProfit = 0;

            //Read each line of the coffee.txt file, use ; as the delimiter and store in an array.
            ArrayList<Drink> allDrinks = new ArrayList<>();
            String line = bufReader.readLine();
            while (line != null){
                String[] drinkInfo = line.split(";");
                //Initialize each Drink object using the element 0 as name, 1 as cost, 2 as price
                Drink everyDrink = new Drink(drinkInfo[0], Double.parseDouble(drinkInfo[1]), Double.parseDouble(drinkInfo[2]));
                allDrinks.add(everyDrink);
                line = bufReader.readLine();
            }
            bufReader.close();

            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("dailyCoffeeHouseSales.txt")); //file to write to

            //for every drink, perform calculations. Write them to the file.
            for(Drink everyDrink : allDrinks){
                //call numberOfCupsSold method, then send that to expenses, revenue and profit methods
                String nameOfDrink = everyDrink.getName();
                int cups = Drink.numberOfCupsSold(nameOfDrink);
                double expenses = everyDrink.dailyExpensesMethod(cups);
                double revenue = everyDrink.dailyRevenueMethod(cups);
                double profit = everyDrink.dailyProfitMethod(revenue, expenses);
                bufWriter.write(everyDrink.getName() + ": Sold " + cups + ", Expenses $" + expenses + ", Revenue $" + revenue + ", Profit " + profit+ "\n");
                totalExpenses += expenses;
                totalRevenue += revenue;
                totalProfit += profit;
            }
            //Write all of the grand totals to the file
            bufWriter.write("Totals: Expenses $" + totalExpenses + ", Revenue $" + totalRevenue + ", Profit " + totalProfit);
            bufWriter.close();

        }
        catch (IOException ioe){
            System.out.println(ioe.toString());
        }
    }
}