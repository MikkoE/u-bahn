/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import helper.Train;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import u.bahn.UBahn;

/**
 *
 * @author mikko
 */
public class Gui {
    ObservableList<String> options = 
        FXCollections.observableArrayList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
    );
    
    Button startButton = new Button("Start new Simulation");
    Button stopButton = new Button("Stop current Simulation");
    Button stopTrainButton = new Button ("Stop train");
    Button resetButton = new Button( "reset");
    
    Label trainsLabel = new Label("Anzah Züge:");
    
    TableView table = new TableView();
    
    TableColumn trainNumberColumn = new TableColumn<>("Zugnummer");
    TableColumn currentStationColumn = new TableColumn<>("Aktuelle Station");
    TableColumn speedColumn = new TableColumn<>("Geschwindigkeit");
    TableColumn brokenColumn = new TableColumn<>("Zustand");
    
    ArrayList<TableColumn> columns = new ArrayList<>();
    
    
    final ComboBox trainsChoose = new ComboBox(options);
    final ComboBox trainsKill = new ComboBox(options);
    

    public Gui(StackPane root, ObservableList<Train> trainDataList, UBahn ubahn) {
        columns.add(trainNumberColumn);
        columns.add(currentStationColumn);
        columns.add(speedColumn);
        columns.add(brokenColumn);
        
        startButton.setOnAction((ActionEvent e) -> {
            ubahn.startTrains(trainsChoose.getSelectionModel().getSelectedItem().toString());
        });
        
        stopButton.setOnAction((ActionEvent e) -> {
            ubahn.stopSimulation();
        });
        
        stopTrainButton.setOnAction((ActionEvent e) -> {
            ubahn.stopTrain(trainsKill.getSelectionModel().getSelectedItem().toString());
        });
        
        resetButton.setOnAction((ActionEvent e) -> {
            ubahn.getTrainDataList().removeAll(ubahn.getTrainDataList());
        });
        
        
        table.setEditable(true);
        
        trainNumberColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        trainNumberColumn.setCellValueFactory(new PropertyValueFactory<>("trainNum"));
        trainNumberColumn.setMinWidth(200);
        
        currentStationColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        currentStationColumn.setCellValueFactory(new PropertyValueFactory<>("stationNum"));
        currentStationColumn.setMinWidth(200);
        
        speedColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        speedColumn.setMinWidth(200);
        
        brokenColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.25));
        brokenColumn.setCellValueFactory(new PropertyValueFactory<>("broken"));
        brokenColumn.setMinWidth(200);
        
        table.setItems(trainDataList);
        table.getColumns().addAll(trainNumberColumn, currentStationColumn, speedColumn, brokenColumn);
        
        
        trainsChoose.getSelectionModel().selectFirst();
        trainsKill.getSelectionModel().selectFirst();
        
        HBox trains = new HBox();
        trains.getChildren().addAll(trainsLabel, trainsChoose);
        trains.setSpacing(10);
        
        HBox stopper = new HBox();
        stopper.getChildren().addAll(trainsKill, stopTrainButton);
        stopper.setSpacing(10);
        
        GridPane grid = new GridPane();
        //grid.setGridLinesVisible(true);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(startButton, 1, 1);
        grid.add(trains, 2, 1);
        grid.add(stopButton, 1, 2);
        grid.add(stopper, 4, 1);
        grid.add(table, 1, 3, 6, 1);
        grid.add(resetButton, 6, 1);
        
        root.getChildren().add(grid);
    }

    public TableView getTable() {
        return table;
    }
    
    public static void refreshTableView(TableView tableView, List<TableColumn> columns, List rows) {        
        tableView.getColumns().clear();
        tableView.getColumns().addAll(columns);

        ObservableList<String> list = FXCollections.observableArrayList(rows);
        tableView.setItems(list);
}
    
    
    
    
}
