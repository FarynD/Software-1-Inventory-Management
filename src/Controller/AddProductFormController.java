/**
 * Controller Class for the add product form
 * @author Faryn Dumont
 */
package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {

    Inventory inv;
    ObservableList<Part> associatedParts;
    ObservableList<Part> searchList;

    //FXML data inputs
    @FXML
    private TextField idInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField invInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField maxInput;
    @FXML
    private TextField minInput;
    @FXML
    private TextField searchInput;

    //FXML Tables
    @FXML
    private TableView<Part> allPartsTbl;
    @FXML
    private TableView<Part> associatedPartsTbl;

    //FXML all parts table columns
    @FXML
    private TableColumn<Part, Integer> allPartIDCol;
    @FXML
    private TableColumn<Part, String> allPartNameCol;
    @FXML
    private TableColumn<Part, Integer> allInvCol;
    @FXML
    private TableColumn<Part, Double> allPriceCol;

    //FXML associated parts table columns
    @FXML
    private TableColumn<Part, Integer> associatedPartIDCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> associatedInvCol;
    @FXML
    private TableColumn<Part, Double> associatedPriceCol;

    //FXML buttons
    @FXML
    private Button saveBtn;

    /**
     * AddProductFormController Constructor
     * @param aInv the Inventory to be used
     */
    public AddProductFormController(Inventory aInv)
    {
        inv = aInv;
    }

    /**
     * Initializes controller, sets the ID, initializes associatedParts and searchList, and populates tables
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setID(inv.getAllProducts());
        associatedParts = FXCollections.observableArrayList();
        searchList  = FXCollections.observableArrayList();
        createTables();
    }

    //==================================================================
    //Button Actions
    //==================================================================

    /**
     * Runs when the search button is pressed and calls the search method
     * @param actionEvent event captured on button press
     */
    public void onSearchBtn(ActionEvent actionEvent)
    {
        search();
    }

    /**
     * Runs when the add part button is pressed  and adds selected part from allParts Table to associated parts
     * @param actionEvent event captured on button press
     */
    public void onAddPartBtn(ActionEvent actionEvent)
    {
        Part p = allPartsTbl.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            nothingSelectedErrorMsg();
            return;
        }
        associatedParts.add(p);
        createTables();
    }

    /**
     * Runs whe the remove part button is pressed and removes selected part from associatedParts Table
     * @param actionEvent event captured on button press
     */
    public void onRemovePartBtn(ActionEvent actionEvent)
    {
        Part p = associatedPartsTbl.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            nothingSelectedErrorMsg();
            return;
        }
        else if(confirmDelete(p.getName()))
        {
            associatedParts.remove(p);
        }
        
    }

    /**
     * Runs when save button is pressed and saves the new product and exits the form
     * @param actionEvent event captured on button press
     */
    public void onSaveBtn(ActionEvent actionEvent)
    {
        try{
            double totalPrice = 0;
            for(int x = 0; x < associatedParts.size(); x++)
            {
                totalPrice += associatedParts.get(x).getPrice();
            }
            if (idInput.getText().isBlank() || nameInput.getText().isBlank() || invInput.getText().isBlank() || priceInput.getText().isBlank() || maxInput.getText().isBlank() || minInput.getText().isBlank())
            {
                emptySearchErrorMsg();
                return;
            }
            if(Double.parseDouble(priceInput.getText()) < totalPrice)
            {
                wrongPriceErrorMsg();
                return;
            }
            Product p = new Product(Integer.parseInt(idInput.getText()), nameInput.getText(), Double.parseDouble(priceInput.getText()), Integer.parseInt(invInput.getText()), Integer.parseInt(minInput.getText()), Integer.parseInt(maxInput.getText()));
            if(!checkInvMinMax(p.getStock(),p.getMin(),p.getMax()))
            {
                invMinMaxErrorMsg();
                return;
            }
            for(int x = 0; x < associatedParts.size(); x++)
            {
                p.addAssociatedPart(associatedParts.get(x));
            }
            inv.addProduct(p);
            closeForm();

        } catch(Exception e){inputErrorMsg();}
    }

    /**
     * Runs when the cancel button is pressed and exits the form
     * @param actionEvent event captured on button press
     */
    public void onCancelBtn(ActionEvent actionEvent){closeForm();}

    //==================================================================
    //Other methods
    //==================================================================

    /**
     * Creates and Sets an unique ID for the new Product
     * @param allProducts list of all parts already created
     */
    public void setID(ObservableList<Product> allProducts)
    {
        int ID = 0;
        if(allProducts.size() > 0) {
            while (ID < allProducts.size() && ID == allProducts.get(ID).getId()) {ID++;}
        }
        idInput.setText(Integer.toString(ID));
    }

    /**
     * Closes the form
     */
    private void closeForm()
    {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Fills the tables with corresponding data
     */
    private void createTables()
    {
        allPartsTbl.setItems(inv.getAllParts());
        allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTbl.refresh();

        associatedPartsTbl.setItems(associatedParts);
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTbl.refresh();
    }

    /**
     * Searches the allParts table with input from searchInput and displays it in the allParts Table
     */
    private void search()
    {
        if(searchInput.getText().isEmpty())
        {
            createTables();
            return;
        }
        searchList.clear();
        try
        {
            searchList.add(inv.lookupPart(Integer.parseInt(searchInput.getText())));
        }
        catch(Exception e)
        {
            searchList = inv.lookupPart(searchInput.getText());
            if (searchList.isEmpty())
            {
                emptySearchErrorMsg();
                return;
            }

        }
        allPartsTbl.setItems(searchList);
        allPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        allInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTbl.refresh();
    }


    /**
     * Tells if Inventory, Max, and min have values that work.
     * @param inv amount of inventory
     * @param min the minimum amount
     * @param max the maximum amount
     * @return wether or not max is greater than or equal to inventory and inventory is greater than or equal to minimum
     */
    private boolean checkInvMinMax(int inv, int min, int max)
    {
        if(max >= inv && inv >= min)
        {
            return true;
        }
        return false;
    }

    /**
     * Brings up a dialog to confirm if the user wants a part deleted
     * @param name the name of the part the user wants deleted
     * @return true if the user choses yes. false if not.
     */
    private boolean confirmDelete(String name)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm removal");
        alert.setHeaderText("Confirm removal");
        alert.setContentText("Are you sure you want to delete " + name + "?");
        ButtonType yesBtn = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noBtn = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(yesBtn, noBtn);

        ButtonType anwser = alert.showAndWait().orElse(ButtonType.NO);
        if(anwser == yesBtn)
        {
            return true;
        }
        return false;
    }

    //==================================================================
    //Error Message Methods
    //==================================================================

    /**
     * Displays an error message for searching with an empty search bar
     */
    private void emptySearchErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Part Not Found");
        alert.setHeaderText("Part Not Found");
        alert.setContentText("There is no part with this name or ID");

        alert.showAndWait();
    }

    /**
     * Displays an error message for incorrect input
     */
    private void inputErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Please make sure the input is in the correct format");

        alert.showAndWait();
    }

    /**
     * Displays an error message for empty selection
     */
    private void nothingSelectedErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nothing Selected");
        alert.setHeaderText("Nothing Selected");
        alert.setContentText("Please select a part to Modify");
        alert.showAndWait();
    }

    /**
     * Displays an error message for incorrect input
     */
    private void invMinMaxErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Inventory, Min, or Max");
        alert.setHeaderText("Invalid Inventory, Min, or Max");
        alert.setContentText("Max must be greater than Min and inventory must be in between or equal to them");

        alert.showAndWait();
    }

    /**
     * Displays an error message for a wrong price
     */
    private void wrongPriceErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Price");
        alert.setHeaderText("Incorrect Price");
        alert.setContentText("Please make sure your total price is more than the sum of your part prices.");
        alert.showAndWait();
    }
}
