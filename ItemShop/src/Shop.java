import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ian James Fannon on 1/11/2017.
 */
public class Shop extends Item {

    private List<String> items;
    private List<Object> shopsGold;
    private List<String> shopName;

    public Shop() {
        shopName = new ArrayList<>();
        shopsGold = new ArrayList<>();
        items = new ArrayList<>();
    }

    /**
     * The method initShop initializes the shops
     * @param shopName is the name of the shop
     * @param shopsGold is the amount of gold the shop has
     * @return the name of the shops
     */
    public String initShop(String shopName, int shopsGold) {
        String shops = "";
        this.shopName.add(shopName);
        this.shopsGold.add(shopsGold);
        for (int i = 0; i < this.shopName.size(); i++) {
            for (int j = 0; j < this.shopsGold.size(); j++) {
                shops = this.shopName.get(i) + " " + this.shopsGold.get(i).toString();
            }
        }
        return shops;
    }

    /**
     * The method addItemToFile add and item to the created file
     * @param itemName is the name of the item
     * @param itemPrice is the price of the item
     * @return the item being added
     */
    public String addItemToFile(String itemName, int itemPrice) {
        String shopItems = itemName + ":  " + itemPrice;
        items.add(shopItems);
        File file = new File("shopsItems.txt");
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i) != null) {
                    writer.print(items.get(i) + "\n");
                } else {
                    System.out.println("No Items");
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.err.println("An IO error has occurred " + Arrays.toString(ex.getStackTrace()));
        }
        return shopItems;
    }

    /**
     * The method readItemFromFile reads the items from the created file.
     */
    public void readItemFromFile() {
        File file = new File("shopsItems.txt");
        String item = "";
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) != null) {
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
     * The method removeItemFromFile removes an item from the created file.
     * @param itemName is the name of the item
     * @param itemPrice is the price of the item
     */
    public void removeItemFromFile(String itemName, int itemPrice) {
        File file = new File("shopsItems.txt");
        items.remove(toString(itemName, itemPrice));
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i) != null) {
                    writer.print(items.get(i) + "\n");
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
     * The method BuyItem purchases an item from a shop
     * @param item is the item being purchased
     * @param index is the index number of the item being purchased
     * @return the item that was purchased
     */
    public String BuyItem(String item, int index) {
        String[] s = item.split(" ");
        String itemName = s[0] + " " + s[1];
        int price = Integer.parseInt(s[2]);

        Object shopsPrice = this.shopsGold.get(index);
        int sp = (int) shopsPrice;
        sp -= price;

        shopsGold.set(index, sp);
        addItemToFile(itemName, price);
        return item;
    }

    /**
     * The method SellItem sells an item to the player
     * @param item is the item being sold
     * @param index is the index number of the item being sold
     * @return the item that was sold
     */
    public String SellItem(String item, int index) {
        String[] s = item.split(" ");
        String itemName = s[0] + " " + s[1];
        int price = Integer.parseInt(s[2]);

        Object shopsPrice = this.shopsGold.get(index);
        int sp = (int) shopsPrice;
        sp -= price;

        shopsGold.set(index, sp);
        removeItemFromFile(itemName, price);
        return item;
    }

    // Getters and Setters
    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<Object> getShopsGold() {
        return shopsGold;
    }

    public void setShopsGold(List<Object> shopsGold) {
        this.shopsGold = shopsGold;
    }

    public List<String> getShopName() {
        return shopName;
    }

    public void setShopName(List<String> shopName) {
        this.shopName = shopName;
    }
}
