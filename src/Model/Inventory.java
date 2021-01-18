/**
 * Class for Inventory
 * @author Faryn Dumont
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {

    ObservableList<Part> allParts = FXCollections.observableArrayList();
    ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Adds a new part to the parts list.
     * @param newPart part to be added to the list.
     */
    public void addPart(Part newPart){allParts.addAll(newPart);}

    /**
     * Adds a new product to the product list.
     * @param newProduct product to be added to the list.
     */
    public void addProduct(Product newProduct){allProducts.addAll(newProduct);}

    /**
     * Looks up a part from is unique ID.
     * @param partId the parts unique ID.
     * @return the parts associated with the ID given.
     */
    public Part lookupPart(int partId)
    {
        int x = 0;
        while(allParts.get(x).getId() != partId && x < allParts.size()){x++;}
        if(x >= allParts.size()){return null;}
        return allParts.get(x);
    }

    /**
     * Looks up a part from its name.
     * @param partName the name of the part being searched for.
     * @return a list of all parts with that name.
     */
    public ObservableList<Part> lookupPart(String partName)
    {
        ObservableList<Part> partList = FXCollections.observableArrayList();
        for(int x = 0; x<allParts.size();x++)
        {
            if(allParts.get(x).getName().equals(partName))
            {
                partList.add(allParts.get(x));
            }
        }
        return partList;
    }

    /**
     * Looks up a product from its unique ID.
     * @param productID the products unique ID.
     * @return The product associated with the ID given.
     */
    public Product lookupProduct(int productID)
    {
        int x = 0;
        while(allProducts.get(x).getId() != productID && x < allProducts.size()){x++;}
        if(x >= allProducts.size()){return null;}
        return allProducts.get(x);
    }

    /**
     * Looks up a product from its name.
     * @param productName the name of the product being searched for.
     * @return a list of all parts with that name.
     */
    public ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        for(int x = 0; x < allProducts.size(); x++)
        {
            if(allProducts.get(x).getName().equals(productName))
            {
                productList.add(allProducts.get(x));
            }
        }
        return productList;
    }

    /**
     * Updates a part.
     * @param index index where the part is in the list.
     * @param selectedPart part that is being updated.
     */
    public void updatePart(int index, Part selectedPart){ allParts.set(index, selectedPart);}

    /**
     * Updates a product.
     * @param index index where the product is in the list.
     * @param newProduct product that is being updated.
     */
    public void updateProduct(int index, Product newProduct){allProducts.set(index, newProduct);}

    /**
     * Deletes the given part.
     * @param selectedPart the part to be deleted.
     * @return
     */
    public boolean deletePart(Part selectedPart) {return allParts.remove(selectedPart);}

    /**
     * Deletes the given product.
     * @param selectedProduct the product to be deleted.
     * @return
     */
    public boolean deleteProduct(Product selectedProduct){return allProducts.remove(selectedProduct);}

    /**
     * returns the list of all the parts.
     * @return list of all the parts.
     */
    public ObservableList<Part>getAllParts(){return allParts;}

    /**
     * Returns the list of all the products.
     * @return list of all the products
     */
    public ObservableList<Product>getAllProducts(){return allProducts;}

}
