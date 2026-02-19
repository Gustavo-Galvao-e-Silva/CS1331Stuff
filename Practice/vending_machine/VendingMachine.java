import java.util.Scanner;

public class VendingMachine {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Delicious Vending Machine!");
        
        Item userItem = getItemFromConsole();
        String itemName = getItemName(userItem);
        double itemCost = getItemCost(userItem);

        double amount = getDollarAmount();
        double change = getChange(itemCost, amount);

        if (change < 0) {
            System.out.printf("Unfortunately you don't have the budget for a %s :(, as you are missing %.2f", itemName, change);
        } else {
            System.out.printf("Thanks for your purchase! Dispensing %s and returning change (%.2f)%n", itemName, change);
        }

        scan.close();
    }

    public static double getDollarAmount() {
        System.out.print("Insert the amount you will spend: ");
        double userAmount = scan.nextDouble();

        return userAmount;
    }

    public static Item getItemFromConsole() {
        while (true) {
            System.out.print("What snack do you want (Snickers, ButterFinger, WhiteMonster, SunChips): ");
            String userInput = scan.next();
            String formattedUserInput = userInput.strip().toUpperCase();
            try {
                Item userItem = Item.valueOf(formattedUserInput);
                return userItem;
            } catch (Exception e) {
                System.out.println("Invalid snack input! Choose one of the labelled options.");
                continue;
            }
        } 
    }

    public static String getItemName(Item item) {
        switch (item) {
            case SNICKERS:
                return "Snickers";
            case BUTTERFINGER:
                return "Butter Finger";
            case WHITEMONSTER:
                return "White Monster";
            case SUNCHIPS:
                return "Sun Chips";
            default:
                return "IDK";
        }
    }

    public static double getItemCost(Item userItem) {
        switch (userItem) {
            case SNICKERS:
                return 2.50;
            case BUTTERFINGER:
                return 2.75;
            case WHITEMONSTER:
                return 3.50;
            case SUNCHIPS:
                return 3.00;
            default:
                return 1.75;
        }
    }

    public static double getChange(double itemCost, double userAmount) {
        double exactChange = userAmount - itemCost;
        return (int)(exactChange * 100) / 100.0; 
    }

}
