package Controller;


import Model.InHouse;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class AddPartFormController implements Initializable {

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
    private TextField machineIdInput;
    @FXML
    private Label machineCompLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void setID(ObservableList<Part> allParts) {
        int ID = 0;
        if(allParts.size() > 0) {
            while (ID <= allParts.size() && ID == allParts.get(ID).getId()) {ID++;}
        }
        idInput.setText(Integer.toString(ID));
    }




    @FXML
    public void onSaveBtn(ActionEvent actionEvent)
    {

        if(nameInput.getText().isBlank() || invInput.getText().isBlank() || priceInput.getText().isBlank() || maxInput.getText().isBlank() || minInput.getText().isBlank() || machineIdInput.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Input");
            alert.setHeaderText("Empty Input");
            alert.setContentText("Please make sure there is information in all fields");

            alert.showAndWait();
        }
        else
        {
            if(machineCompLabel.getText() == "Machine ID")
            {
                try
                {
                    InHouse p = new InHouse(Integer.parseInt(idInput.getText()), nameInput.getText(), Double.parseDouble(priceInput.getText()), Integer.parseInt(invInput.getText()), Integer.parseInt(minInput.getText()), Integer.parseInt(maxInput.getText()), Integer.parseInt(machineIdInput.getText()));
                }
                catch (Exception e)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please make sure the input is in the correct format");

                    alert.showAndWait();
                }

            }
        }

    }


    public void onInHouseRadio(ActionEvent actionEvent)
    {
        machineCompLabel.setText("Machine ID");
    }

    public void onOutsourcedRadio(ActionEvent actionEvent)
    {
        machineCompLabel.setText("Company Name");
    }
}
