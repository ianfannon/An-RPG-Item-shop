import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian James Fannon on 1/11/2017.
 */
public class Player extends Item {

    private String playerName;
    private int playersGold;
    private List<String> itemNames = new ArrayList<>();

    public Player() {
        playerName = "";
        playersGold = 0;
        itemNames = new ArrayList<>();
    }

    /**
     * The method initPlayer, initializes the player
     * @param playerName is the users name
     * @param playersGold is the starting amount of gold the user has.
     */
    public void initPlayer(String playerName, int playersGold) {
        this.playerName = playerName;
        this.playersGold = playersGold;
    }

    /**
     * The method addItemToFile adds and writes an item to a created file
     * @param itemName is the name of the item
     * @param itemPrice is the price of the item
     */
    public void addItemToFile(String itemName, int itemPrice) {
        itemNames.add(toString(itemName, itemPrice));
        File file = new File("playersItems.txt");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (int i = 0; i < itemNames.size(); i++) {
                if(itemNames.get(i) != null) {
                    String items = "";
                    writer.print(itemNames.get(i) + "\n");
                } else {
                    System.out.println("No Items");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.err.println("An IO error has occurred " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * The method readItemFromFile reads and displays the items that were written to the file
     */
    public void readItemFromFile() {
        File file = new File("playersItems.txt");
        String item = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            System.out.println("\n\n[ITEM]\t\t  [PRICE]  \t[PLAYERS GOLD - " + playersGold + "]\n**************************************");
            for (int i = 0; i < itemNames.size(); i++) {
                if (itemNames.get(i) != null) {
                    item = br.readLine();
                    System.out.println(item);
                }
            }
            br.close();
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println("File not found: " + Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            System.err.println("An IO error has occurred: " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * The method removeItemFromFile removes the specified item from the file
     * @param itemName is the name of the item
     * @param itemPrice is the price of the item
     */
    public void removeItemFromFile(String itemName, int itemPrice) {
        File file = new File("playersItems.txt");
        itemNames.remove(toString(itemName, itemPrice));
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < itemNames.size(); i++) {
                if(itemNames.get(i) != null) {
                    writer.print(itemNames.get(i) + "\n");
                } else {
                    System.out.println("No Items");
                }
            }
            writer.close();
            reader.close();
            br.close();
        } catch (IOException ex) {
            System.err.println("An IO error has occurred " + Arrays.toString(ex.getStackTrace()));
        }
    }

    /**
     * The method BuyItem buys an item from a shop and adds it to the players items
     * @param item is the item being bought
     * @return the item being bought
     */
    public String BuyItem(String item) {
        String[] s = item.split(" ");
        String itemName = s[0] + " " + s[1];
        int price = Integer.parseInt(s[2]);
        this.playersGold = playersGold - price;
        addItemToFile(itemName + ": ", price);
        return item;
    }

    /**
     * The method SellItem sell a specified players item to a shop and removes it from the players items
     * @param item is the item being sold
     * @return the item being sold
     */
    public String SellItem(String item) {
        String[] s = item.split(" ");
        String itemName = s[0] + " " + s[1];
        int price = Integer.parseInt(s[2]);
        String itemToSell = itemName + price;
        this.playersGold += price;
        removeItemFromFile(itemName, price);
        return item;
    }

    // Getters and Setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayersGold() {
        return playersGold;
    }

    public void setPlayersGold(int playersGold) {
        this.playersGold = playersGold;
    }

}
