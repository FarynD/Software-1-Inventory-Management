package Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;

//import javax.swing.table.TableColumn;


import java.awt.*;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;



public class MainMenuController implements Initializable {

    private Inventory inv;
    //private ObservableList parts = FXCollections.observableArrayList();
    //private ObservableList products = FXCollections.observableArrayList();
    private ObservableList<Part> partSearchList;
    private ObservableList<Product> productSearchList;

    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Part, Integer> partsIdCol;
    @FXML
    private TableColumn<Part, String> partsNameCol;
    @FXML
    private TableColumn<Part, Integer> partsInventoryCol;
    @FXML
    private TableColumn<Part, Double> partsPriceCol;


    @FXML
    private TableColumn<Product, Integer> productsIdCol;
    @FXML
    private TableColumn<Product, String> productsNameCol;
    @FXML
    private TableColumn<Product, Integer> productsInventoryCol;
    @FXML
    private TableColumn<Product, Double> productsPriceCol;

    @FXML
    private TextField partSearchInput;
    @FXML
    private TextField productSearchInput;


    public MainMenuController(Inventory aInv)
    {
        inv = aInv;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<Part> test= inv.getAllProducts().get(0).getAllAssociatedParts();
        partSearchList = FXCollections.observableArrayList();
        productSearchList = FXCollections.observableArrayList();
        createTables();
    }

    public void onPartAddBtn(ActionEvent actionEvent)
    {
        try
        {
            AddPartFormController controller = new AddPartFormController(inv);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddPartForm.fxml"));
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root));
            stage.show();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onPartModifyBtn(ActionEvent actionEvent)
    {
        Part p = partsTable.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");

            alert.showAndWait();
        }
        else {
            try {
                ModifyPartFormController controller = new ModifyPartFormController(p, inv);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ModifyPartForm.fxml"));
                loader.setController(controller);
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Add Part");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onPartDeleteBtn(ActionEvent actionEvent)
    {
        Part p = partsTable.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");

            alert.showAndWait();
        }
        else {
                try{inv.deletePart(p);} catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }


    public void onPartSearchBtn(ActionEvent actionEvent)
    {
        if(partSearchInput.getText().isEmpty())
        {
            createTables();
            return;
        }
        partSearchList.clear();
        try
        {
            partSearchList.add(inv.lookupPart(Integer.parseInt(partSearchInput.getText())));
        }
        catch(Exception e)
        {
            partSearchList = inv.lookupPart(partSearchInput.getText());
            if (partSearchList.isEmpty())
            {
                emptySearchErrorMsg();
                return;
            }
        }
        partsTable.setItems(partSearchList);
        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.refresh();
    }



    public void onProductSearchBtn(ActionEvent actionEvent)
    {
        if(productSearchInput.getText().isEmpty())
        {
            createTables();
            return;
        }
        productSearchList.clear();
        try
        {
            productSearchList.add(inv.lookupProduct(Integer.parseInt(productSearchInput.getText())));
        }
        catch(Exception e)
        {
            productSearchList = inv.lookupProduct(productSearchInput.getText());
            if (productSearchList.isEmpty())
            {
                emptySearchErrorMsg();
                return;
            }
        }
        productsTable.setItems(productSearchList);
        productsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.refresh();
    }

    public void onProductAddBtn(ActionEvent actionEvent)
    {
        try
        {
            /*
            Parent root = FXMLLoader.load(getClass().getResource("../View/AddProductForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Part");
            stage.setScene(new Scene(root));
            stage.show();
             */
            AddProductFormController controller = new AddProductFormController(inv);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddProductForm.fxml"));
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Product");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onProductModifyBtn(ActionEvent actionEvent)
    {
        Product p = productsTable.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");

            alert.showAndWait();
            return;
        }
        try
        {
            ModifyProductFormController controller = new ModifyProductFormController(inv, p );
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddProductForm.fxml"));
            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Modify Product");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onProductDeleteBtn(ActionEvent actionEvent)
    {
        Product p = productsTable.getSelectionModel().getSelectedItem();
        if( p == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nothing Selected");
            alert.setHeaderText("Nothing Selected");
            alert.setContentText("Please select a part to Modify");

            alert.showAndWait();
        }
        else {
            try{inv.deleteProduct(p);} catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createTables()
    {
        partsTable.setItems(inv.getAllParts());
        productsTable.setItems(inv.getAllProducts());

        partsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTable.refresh();
        productsTable.refresh();
    }
    private <T> TableColumn<T, Double> formatPrice()
    {
        TableColumn<T, Double> costCol = new TableColumn("price");
        costCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        costCol.setCellFactory((TableColumn<T, Double> column) ->
        {
            return new TableCell<T, Double>()
            {
                @Override
                protected void updateItem(Double item, boolean empty)
                {
                    if(!empty)
                    {
                        setText("$" + String.format("%10.2f", item));
                    }
                }
            };
        });
        return costCol;

    }
    private boolean isInt(String s)
    {
        return true;
    }

    private void emptySearchErrorMsg()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Part Not Found");
        alert.setHeaderText("Part Not Found");
        alert.setContentText("There is no part with this name or ID");

        alert.showAndWait();
    }

}
