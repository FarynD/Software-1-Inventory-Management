import javafx.collections.ObservableList;


public class Inventory {

    ObservableList<Part> allParts;
    ObservableList<Product> allProducts;

    public Inventory() {

    }

    public void addPart(Part newPart){allParts.addAll(newPart);}

    public void addProduct(Product newProduct){allProducts.addAll(newProduct);}

    //public Part lookupPart(int partId){}

    //public ObservableList<Part> lookupPart(String partName){}

    //public Product lookupProduct(int productID){}

    //public ObservableList<Product> lookupProduct(String productName){}

    public void updatePart(int index, Part selectedPart){}

    public void updateProduct(int index, Product newProduct){}

    //public boolean deletePart(Part selectedPart){}

    //public boolean deleteProduct(Product selectedProduct){}

    //public ObservableList<Part>getAllParts(){}

    //public ObservableList<Product>getAllProducts(){}

}
