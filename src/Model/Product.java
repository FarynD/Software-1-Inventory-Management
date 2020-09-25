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
     * @param aId
     */
    public void setID(int aId){id = aId;}

    /**
     * Sets the name for the product
     * @param aName
     */
    public void setName(String aName){name = aName;}

    /**
     * Sets the price for the product
     * @param aPrice
     */
    public void setPrice(double aPrice){price = aPrice;}

    /**
     * Sets the stock of the product
     * @param aStock
     */
    public void setStock(int aStock){stock = aStock;}

    /**
     *
     * @param aMin
     */
    public void setMin(int aMin){min = aMin;}

    /**
     *
     * @param aMax
     */
    public void setMax(int aMax){max = aMax;}


    /**
     *
     * @return
     */
    public int getId(){return id;}

    /**
     *
     * @return
     */
    public String getName(){return name;}

    /**
     *
     * @return
     */
    public double getPrice(){return price;}

    /**
     *
     * @return
     */
    public int getStock(){return stock;}

    /**
     *
     * @return
     */
    public int getMin(){return min;}

    /**
     *
     * @return
     */
    public int getMax(){return max;}

    /**
     *
     * @param aAssociatedPart
     */
    public void addAssociatedPart(Part aAssociatedPart){associatedParts.add(aAssociatedPart);}

    /**
     *
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){return associatedParts.remove(selectedAssociatedPart);}

    /**
     *
     * @return
     */
    public ObservableList<Part> getAllAssociatedParts(){return associatedParts;}



}
