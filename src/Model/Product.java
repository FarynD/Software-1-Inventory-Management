/**
 * Class for products
 * @author Faryn Dumont
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    ObservableList<Part> associatedParts= FXCollections.observableArrayList();
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;

    /**
     * Creates a product
     * @param aId unique ID for the product
     * @param aName products name
     * @param aPrice price of the product
     * @param aStock stock of the product
     * @param aMin
     * @param aMax
     */
    public Product(int aId, String aName, double aPrice, int aStock, int aMin, int aMax)
    {
        id = aId;
        name = aName;
        price = aPrice;
        stock = aStock;
        min = aMin;
        max = aMax;
    }

    /**
     * Sets the ID for the product
     * @param aId int identification number for the product
     */
    public void setID(int aId){id = aId;}

    /**
     * Sets the name for the product
     * @param aName name for the product
     */
    public void setName(String aName){name = aName;}

    /**
     * Sets the price for the product
     * @param aPrice price for the product
     */
    public void setPrice(double aPrice){price = aPrice;}

    /**
     * Sets the stock of the product
     * @param aStock how much stock is there of product
     */
    public void setStock(int aStock){stock = aStock;}

    /**
     * Sets the minimum amount of stock for the product
     * @param aMin
     */
    public void setMin(int aMin){min = aMin;}

    /**
     * Sets the maximum amount of stock for the product
     * @param aMax
     */
    public void setMax(int aMax){max = aMax;}


    /**
     * returns the products id
     * @return products id
     */
    public int getId(){return id;}

    /**
     * returns the game of the product
     * @return product name
     */
    public String getName(){return name;}

    /**
     * returns the products price
     * @return products price
     */
    public double getPrice(){return price;}

    /**
     * returns the number of stock of the product
     * @return products stock
     */
    public int getStock(){return stock;}

    /**
     * returns the minimum amount of stock of the product
     * @return the minimum amount of stock
     */
    public int getMin(){return min;}

    /**
     * returns the maximum amount of stock of the product
     * @return the maximum amount of stock
     */
    public int getMax(){return max;}

    /**
     * Adds new associated parts
     * @param aAssociatedPart parts the product uses
     */
    public void addAssociatedPart(Part aAssociatedPart){associatedParts.add(aAssociatedPart);}

    /**
     * gets rid of the selected part in the associated parts list
     * @param selectedAssociatedPart the part to be deleted
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){return associatedParts.remove(selectedAssociatedPart);}

    /**
     * returns all associated parts
     * @return all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){return associatedParts;}



}
