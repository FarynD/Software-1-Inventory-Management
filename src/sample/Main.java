package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Inventory Management System");

        GridPane mainGridPane = new GridPane();
        mainGridPane.setPadding(new Insets(5));
        mainGridPane.setHgap(10);
        mainGridPane.setVgap(10);

        Label titleLabel = new Label("Inventory Management System");

        //Parts Pane
        GridPane partsPane = new GridPane();
        HBox partsTop = new HBox();
        HBox partsBottom = new HBox();
        partsPane.setMinWidth(350);
        partsPane.setMaxWidth(350);
        partsPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        Label partsLabel = new Label("Parts");
        TextField partsSearch = new TextField("Search by Part ID or Name");
        TableView partsTable = new TableView();
        Button partsAdd = new Button("Add");
        Button partsModify = new Button("Modify");
        Button partsDelete = new Button("Delete");

        partsTable.getColumns().addAll(new TableColumn("Part ID"),new TableColumn("Part Name" ), new TableColumn("Inventroy Level"), new TableColumn("Price/ Cost per Unit"));
        partsPane.setPadding(new Insets(15,12,15,12));
        partsTop.setSpacing(115);
        partsBottom.setPadding(new Insets(15, 12,15,150));
        partsBottom.setSpacing(10);

        partsTop.getChildren().addAll(partsLabel, partsSearch);
        partsBottom.getChildren().addAll(partsAdd, partsModify, partsDelete);

        partsPane.add(partsTop, 0,0);
        partsPane.add(partsTable, 0, 1);
        partsPane.add(partsBottom,0,2);


        //Products Pane
        GridPane productsPane = new GridPane();
        HBox productsTop = new HBox();
        HBox productsBottom = new HBox();
        productsPane.setMinWidth(350);
        productsPane.setMaxWidth(350);
        productsPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderWidths.DEFAULT)));

        Label productsLabel = new Label("Products");
        TextField productsSearch = new TextField("Search by Product ID or Name");
        TableView productsTable = new TableView();
        Button productsAdd = new Button("Add");
        Button productsModify = new Button("Modify");
        Button productsDelete = new Button("Delete");

        productsTable.getColumns().addAll(new TableColumn("Product ID"),new TableColumn("Product Name" ), new TableColumn("Inventroy Level"), new TableColumn("Price/ Cost per Unit"));
        productsPane.setPadding(new Insets(15,12,15,12));
        productsTop.setSpacing(115);
        productsBottom.setPadding(new Insets(15, 12,15,150));
        productsBottom.setSpacing(10);

        productsTop.getChildren().addAll(productsLabel, productsSearch);
        productsBottom.getChildren().addAll(productsAdd, productsModify, productsDelete);

        productsPane.add(productsTop, 0,0);
        productsPane.add(productsTable, 0, 1);
        productsPane.add(productsBottom,0,2);

        mainGridPane.add(titleLabel, 0, 0);
        mainGridPane.add(partsPane, 0, 1);
        mainGridPane.add(productsPane, 1,1);
        mainGridPane.setHgap(20);
        primaryStage.setScene(new Scene(mainGridPane, partsPane.getMaxWidth()+productsPane.getMaxWidth() + 50, 275));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }


}
