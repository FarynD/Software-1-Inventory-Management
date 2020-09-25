package Controller;


import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class AddPartFormController implements Initializable {

    @FXML
    private TextField nameInput;
    @FXML
    private TextField invInput;
    private TextField priceInput;
    private TextField maxInput;
    private TextField minInput;
    private TextField machineIdInput;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML
    public void onSaveBtn(ActionEvent actionEvent)
    {
        if(nameInput.getText() == "")
        {
           System.out.println("empty name");
        }
        if(invInput.getText() == "")
        {
            System.out.println("empty Inventory");
        }

    }

    public Part getPart()
    {

    }
}
