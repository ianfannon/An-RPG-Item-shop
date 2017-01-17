import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ian James Fannon on 1/11/2017.
 */
public class Main {

    private static Shop shop;
    private static Player player;
    private static List<String> shoplist = new LinkedList<>(); // The list of shops
    private static List<String> shop1 = new LinkedList<>(); // The first shop
    private static List<String> shop2 = new LinkedList<>(); // The second shop
    private static List<String> shop3 = new LinkedList<>(); // The third shop

    public static void main(String[] args) {
        player = new Player();
        shop = new Shop();
        // Initializes the player
        setUpPlayer();
        // displays the shops to buy from or sell to
        displayShops();
    }

    /**
     * The method setUpPlayer initializes the player and adds the initial items to the player
     */
    private static void setUpPlayer() {
        player.initPlayer("Ian", 500);
        player.addItemToFile("Iron Sword", 100);
    }

    /**
     * The method setUpShop1 add elements to the shop
     * @return the List shop1
     */
    private static List<String> setUpShop1() {
        shop1.add(0, shop.addItemToFile("Steel Sword", 145));
        shop1.add(1, shop.addItemToFile("Dwarven Sword", 165));
        shop1.add(2, shop.addItemToFile("Steel Bow", 130));
        return shop1;
    }

    /**
     * The method setUpShop2 adds element to the shop
     * @return the List shop2
     */
    private static List<String> setUpShop2() {
        shop2.add(shop.addItemToFile("Potion Healing", 140));
        shop2.add(shop.addItemToFile("Potion Magika", 120));
        shop2.add(shop.addItemToFile("Potion CurePoison", 86));
        return shop2;
    }

    /**
     * The method setUpShop3 adds elements to the shop
     * @return the List shop3
     */
    private static List<String> setUpShop3() {
        shop3.add(shop.addItemToFile("Steel Armour", 195));
        shop3.add(shop.addItemToFile("Steel Helmet", 95));
        shop3.add(shop.addItemToFile("Steel Gauntlets", 120));
        return shop3;
    }

    /**
     * The method displayShops prints out each shop and goes through a switch statement
     * depending on which shop the user wants to buy or sell to.
     */
    private static void displayShops() {
        shoplist.add(shop.initShop("Fire & Stone", 1200));
        shoplist.add(shop.initShop("Author's Item Shop", 800));
        shoplist.add(shop.initShop("The Best Defense", 1000));
        String buySell = "";
        String buy = "";
        String sell = "";

        setUpShop1();
        setUpShop2();
        setUpShop3();

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("\n**** [SHOPS] **** [SHOPS GOLD] ****");
            for (int i = 0; i < shoplist.size(); i++) {
                System.out.println((i + 1) + ") " + shoplist.get(i));
            }
            System.out.println(4 + ") Exit Shops");
            //Display players items
            player.readItemFromFile();
            // Choice of which shops to buy from.
            System.out.print("\nWhich shop would you like to purchase from: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Print Shops items
                    System.out.println("\n[SHOP - " + shop.getShopName().get(0) + "]");
                    System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(0) + "]\n**************************************");
                    for (int i = 0; i < shop1.size(); i++) {
                        System.out.println((i + 1) + ") " + shop1.get(i));
                    }
                    System.out.print("\nWould you like to buy or sell (B/S): ");
                    buySell = input.next();
                    if (buySell.equalsIgnoreCase("B")) {
                        System.out.print("Which item would you like to Buy: ");
                        buy = input.nextLine();
                        if (input.hasNext()) {
                            buy = input.nextLine();
                        }
                        // Player buys an item
                        player.BuyItem(buy);
                        // Shop sells an item
                        shop.SellItem(buy, 0);
                        // Display players items
                        player.readItemFromFile();
                        // Remove item from shop
                        shop1.remove(buy);
                        // Print shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(0) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(0) + "]\n**************************************");
                        for (int i = 0; i < shop1.size(); i++) {
                            System.out.println((i + 1) + ") " + shop1.get(i));
                        }
                    } else if (buySell.equalsIgnoreCase("S")) {
                        System.out.print("Which item would you like to sell: ");
                        sell = input.nextLine();
                        if (input.hasNext()) {
                            sell = input.nextLine();
                        }
                        // Player sells an item
                        player.SellItem(sell);
                        // Shop buys a players item
                        shop.BuyItem(sell, 0);
                        // Display players items
                        player.readItemFromFile();
                        // Add Item to shop
                        shop1.add(sell);
                        // Print Shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(0) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(0) + "]\n**************************************");
                        for (int i = 0; i < shop1.size(); i++) {
                            System.out.println((i + 1) + ") " + shop1.get(i));
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n[SHOP - " + shop.getShopName().get(1) + "]");
                    System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(1) + "]\n**************************************");
                    for (int i = 0; i < shop2.size(); i++) {
                        System.out.println((i + 1) + ") "  + shop2.get(i));
                    }
                    System.out.print("\nWould you like to buy or sell (B/S): ");
                    buySell = input.next();
                    if (buySell.equalsIgnoreCase("B")) {
                        System.out.print("Which item would you like to Buy: ");
                        buy = input.nextLine();
                        if (input.hasNext()) {
                            buy = input.nextLine();
                        }
                        // Player buys an item
                        player.BuyItem(buy);
                        // Shop sells an item
                        shop.SellItem(buy, 1);
                        // Display players items
                        player.readItemFromFile();
                        // Remove item from shop
                        shop2.remove(buy);
                        // Print shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(1) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(1) + "]\n**************************************");
                        for (int i = 0; i < shop2.size(); i++) {
                            System.out.println((i + 1) + ") " + shop2.get(i));
                        }
                    } else if (buySell.equalsIgnoreCase("S")) {
                        System.out.print("Which item would you like to sell: ");
                        sell = input.nextLine();
                        if (input.hasNext()) {
                            sell = input.nextLine();
                        }
                        // Player sells an item
                        player.SellItem(sell);
                        // Shop buys a players item
                        shop.BuyItem(sell, 1);
                        // Display players items
                        player.readItemFromFile();
                        // Add Item to shop
                        shop2.add(sell);
                        // Print Shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(1) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(1) + "]\n**************************************");
                        for (int i = 0; i < shop2.size(); i++) {
                            System.out.println((i + 1) + ") " + shop2.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n[SHOP - " + shop.getShopName().get(2) + "]");
                    System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(2) + "]\n**************************************");
                    for (int i = 0; i < shop3.size(); i++) {
                        System.out.println((i + 1) + ") " + shop3.get(i));
                    }
                    System.out.print("\nWould you like to buy or sell (B/S): ");
                    buySell = input.next();
                    player.readItemFromFile();
                    if (buySell.equalsIgnoreCase("B")) {
                        System.out.print("Which item would you like to Buy: ");
                        buy = input.nextLine();
                        if (input.hasNext()) {
                            buy = input.nextLine();
                        }
                        // Player buys an item
                        player.BuyItem(buy);
                        // Shop sells an item
                        shop.SellItem(buy, 2);
                        // Display players items
                        player.readItemFromFile();
                        // Remove item from shop
                        shop3.remove(buy);
                        // Print shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(2) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(2) + "]\n**************************************");
                        for (int i = 0; i < shop3.size(); i++) {
                            System.out.println((i + 1) + ") " + shop3.get(i));
                        }
                    } else if (buySell.equalsIgnoreCase("S")) {
                        System.out.print("Which item would you like to sell: ");
                        sell = input.nextLine();
                        if (input.hasNext()) {
                            sell = input.nextLine();
                        }
                        // Player sells an item
                        player.SellItem(sell);
                        // Shop buys a players item
                        shop.BuyItem(sell, 2);
                        // Display players items
                        player.readItemFromFile();
                        // Add Item to shop
                        shop3.add(sell);
                        // Print Shop
                        System.out.println("\n[SHOP - " + shop.getShopName().get(2) + "]");
                        System.out.println("[ITEM]\t\t  [PRICE]  \t[SHOPS GOLD - " + shop.getShopsGold().get(2) + "]\n**************************************");
                        for (int i = 0; i < shop3.size(); i++) {
                            System.out.println((i + 1) + ") " + shop3.get(i));
                        }
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}