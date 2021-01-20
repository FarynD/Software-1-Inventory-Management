/**
 * Main class for the application
 * @author Faryn Dumont
 */
package MainInventoryManagement;

import Model.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainMenu extends Application {

    /**
     * Starts the program
     * @param primaryStage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        Inventory inv = new Inventory();
        addTestData(inv);

        Controller.MainMenuController controller = new Controller.MainMenuController(inv);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainMenu.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    /**
     * Adds test data to the inventory
     * @param inv Inventory used by the main program.
     */
    private void addTestData(Inventory inv)
    {

        Part part1 = new InHouse(0, "part 0", .99, 4, 1,10,2021 );
        Part part2 = new InHouse(1, "part 1", 10.99, 6, 5,10,001 );
        Part part3 = new InHouse(2, "part 2", 1.00, 3, 1,12,20000 );
        inv.addPart(part1);
        inv.addPart(part2);
        inv.addPart(part3);
        inv.addPart(new InHouse(3, "part 3", 3.99, 4, 1,10,2021 ));
        inv.addPart(new InHouse(4, "part 4", 3.09, 12, 1,20,2021 ));

        Part part4 = new Outsourced(5, "part 6", 2.45, 1,1, 3, "Company A");
        Part part5 = new Outsourced(6, "part 7", 5.33, 3,1, 5, "Company B");
        inv.addPart(part4);
        inv.addPart(part5);
        inv.addPart(new Outsourced(7, "test 1", 2.45, 1,1, 3, "Company C"));
        inv.addPart(new Outsourced(8, "test 2", 2.45, 6,1, 6, "Company A"));
        inv.addPart(new Outsourced(9, "test 3", 2.45, 2,1, 3, "Company B"));

        Product prod1 = new Product(0, "product 0", 20.50, 3, 1, 10);
        Product prod2 = new Product(1, "product 1", 50.99, 7, 1, 10);
        Product prod3 = new Product(2, "test 1", 26.24, 3, 1, 10);

        prod1.addAssociatedPart(part1);
        prod1.addAssociatedPart(part4);
        prod2.addAssociatedPart(part2);
        prod2.addAssociatedPart(part3);
        prod3.addAssociatedPart(part3);
        prod3.addAssociatedPart(part5);

        inv.addProduct(prod1);
        inv.addProduct(prod2);
        inv.addProduct(prod3);
    }


}
