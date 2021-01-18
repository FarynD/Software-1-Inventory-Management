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

    @FXML
    private TableView<Part> allPartsTbl;
    @FXML
    private TableView<Part> chosenPartsTbl;

    @FXML
    private TableColumn<Part, Integer> allPartIDCol;
    @FXML
    private TableColumn<Part, String> allPartNameCol;
    @FXML
    private TableColumn<Part, Integer> allInvCol;
    @FXML
    private TableColumn<Part, Double> allPriceCol;

    @FXML
    private TableColumn<Part, Integer> chosenPartIDCol;
    @FXML
    private TableColumn<Part, String> chosenPartNameCol;
    @FXML
    private TableColumn<Part, Integer> chosenInvCol;
    @FXML
    private TableColumn<Part, Double> chosenPriceCol;

    @FXML
    private Button saveBtn;


    public AddProductFormController(Inventory aInv)
    {
        inv = aInv;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setID(inv.getAllProducts());
        associatedParts = FXCollections.observableArrayList();
        searchList  = FXCollections.observableArrayList();
        createTables();
    }

    /**
     * Calls search method
     * @param actionEvent
     */
    public void onSearchBtn(ActionEvent actionEvent)
    {
        search();
    }

    /**
     * Adds selected part from allParts Table to associated parts
     * @param actionEvent
     */
    public void onAddPartBtn(ActionEvent actionEvent)
    {
        Part p = allPartsTbl.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");
            alert.showAndWait();
            return;
        }
        associatedParts.add(p);
        createTables();
    }

    /**
     * Removes selected part from chosenParts Table
     * @param actionEvent
     */
    public void onRemovePartBtn(ActionEvent actionEvent)
    {
        Part p = chosenPartsTbl.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");
            alert.showAndWait();
            return;
        }
        associatedParts.remove(p);
    }

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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Input");
                alert.setHeaderText("Empty Input");
                alert.setContentText("Please make sure there is information in all fields");
                alert.showAndWait();
                return;
            }
            if(Double.parseDouble(priceInput.getText()) < totalPrice)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Total Price");
                alert.setHeaderText("Total Price");
                alert.setContentText("Please make sure the Total price is greater than or equal to the total price of all associated parts");
                alert.showAndWait();
                return;
            }
            Product p = new Product(Integer.parseInt(idInput.getText()), nameInput.getText(), Double.parseDouble(priceInput.getText()), Integer.parseInt(invInput.getText()), Integer.parseInt(minInput.getText()), Integer.parseInt(maxInput.getText()));
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
     * @param actionEvent
     */
    public void onCancelBtn(ActionEvent actionEvent){closeForm();}

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
     * Method to close the Form
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

        chosenPartsTbl.setItems(associatedParts);
        chosenPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        chosenPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        chosenInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        chosenPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        chosenPartsTbl.refresh();
    }

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

    private void inputErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Please make sure the input is in the correct format");

        alert.showAndWait();
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
}
