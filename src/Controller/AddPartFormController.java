/**
 * Controller Class for the add part form
 * @author Faryn Dumont
 */
package Controller;


import Model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class AddPartFormController implements Initializable {

    Inventory inv;

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
    private TextField machineIdInput;
    @FXML
    private Label machineCompLabel;

    //FXML Buttons and Radio
    @FXML
    private Button saveBtn;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    @FXML
    private ToggleGroup radioGroup;

    /**
     * AddPartFormController Constructor
     * @param aInv the Inventory to be used
     */
    public AddPartFormController(Inventory aInv)
    {
        inv = aInv;
    }

    /**
     * Initializes controller, sets the ID and toggles the radio
     * @param location The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setID(inv.getAllParts());
        radioGroup.selectToggle(inHouseRadio);
    }

    //==================================================================
    //Button and Radio Actions
    //==================================================================

    /**
     * Runs when save button is pressed and saves the new part and exits the form
     * @param actionEvent event captured on button press
     */
    public void onSaveBtn(ActionEvent actionEvent)
    {

        if (nameInput.getText().isBlank() || invInput.getText().isBlank() || priceInput.getText().isBlank() || maxInput.getText().isBlank() || minInput.getText().isBlank() || machineIdInput.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Input");
            alert.setHeaderText("Empty Input");
            alert.setContentText("Please make sure there is information in all fields");
            alert.showAndWait();
        }
        else {
            if (radioGroup.getSelectedToggle() == inHouseRadio) {

                try
                {
                    InHouse p = new InHouse(Integer.parseInt(idInput.getText()), nameInput.getText(), Double.parseDouble(priceInput.getText()), Integer.parseInt(invInput.getText()), Integer.parseInt(minInput.getText()), Integer.parseInt(maxInput.getText()), Integer.parseInt(machineIdInput.getText()));
                    inv.addPart(p);
                    closeForm();
                }
                catch (Exception e) { inputErrorMsg();}


            } else if (radioGroup.getSelectedToggle() == outsourcedRadio) {

                try
                {
                    Outsourced p = new Outsourced(Integer.parseInt(idInput.getText()), nameInput.getText(), Double.parseDouble(priceInput.getText()), Integer.parseInt(invInput.getText()), Integer.parseInt(minInput.getText()), Integer.parseInt(maxInput.getText()), machineIdInput.getText());
                    inv.addPart(p);
                    closeForm();
                }
                catch (Exception e) { inputErrorMsg();}
            }
        }
    }

    /**
     * Runs when the cancel button is pressed and exits the form
     * @param actionEvent event captured on button press
     */
    public void onCancelBtn(ActionEvent actionEvent)
    {
        closeForm();
    }

    /**
     * Runs when the inHouseRadio is pressed and changes the last label to Machine ID
     * @param actionEvent event captured on radio press
     */
    public void onInHouseRadio(ActionEvent actionEvent)
    {
        machineCompLabel.setText("Machine ID");
    }

    /**
     * runs when the inHouseRadio is pressed and changes the last label to Company Name
     * @param actionEvent event captured on radio press
     */
    public void onOutsourcedRadio(ActionEvent actionEvent)
    {
        machineCompLabel.setText("Company Name");
    }

    //==================================================================
    //Other methods
    //==================================================================

    /**
     * Creates and Sets an unique ID for the new part
     * @param allParts list of all parts already created
     */
    public void setID(ObservableList<Part> allParts)
    {
        int ID = 0;
        if(allParts.size() > 0) {
            while (ID < allParts.size() && ID == allParts.get(ID).getId()) {ID++;}
        }
        idInput.setText(Integer.toString(ID));
    }

    /**
     * method to close the form
     */
    private void closeForm()
    {
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.close();
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
}
