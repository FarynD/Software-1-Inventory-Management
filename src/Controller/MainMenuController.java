package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    private ObservableList parts = FXCollections.observableArrayList();
    private ObservableList products = FXCollections.observableArrayList();







    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void onPartSearchTxtField(ActionEvent actionEvent)
    {
    }

    public void onPartAddBtn(ActionEvent actionEvent)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../View/AddPartForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Model.Part");
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
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../View/ModifyPartForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Model.Part");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void onPartDeleteBtn(ActionEvent actionEvent)
    {

    }


    public void onProductSearchTxtField(ActionEvent actionEvent)
    {
    }

    public void onProductAddBtn(ActionEvent actionEvent)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../View/AddProductForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Model.Part");
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
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("../View/ModifyProductForm.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add Model.Part");
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
    }


}
