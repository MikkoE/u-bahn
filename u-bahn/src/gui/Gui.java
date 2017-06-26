/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import helper.Train;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.adapter.JavaBeanBooleanProperty;
import javafx.beans.property.adapter.JavaBeanBooleanPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanDoubleProperty;
import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import u.bahn.UBahn;

/**
 *
 * @author mikko
 */
public class Gui {
    ObservableList<String> options = 
        FXCollections.observableArrayList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "15", "20", "25", "30", "40", "50"
    );
    
    Button startButton = new Button("Start new Simulation");
    Button stopButton = new Button("Stop current Simulation");
    Button stopTrainButton = new Button ("Stop Train");
    Button resetButton = new Button( "Reset");
    Button deleteButton = new Button( "Repair Train");
    
    Label trainsLabel = new Label("Anzah Züge:");
    
    TableView<Train> table = new TableView<>();
    
    TableColumn trainNumberColumn = new TableColumn<>("Zugnummer");
    TableColumn currentStationColumn = new TableColumn<>("Aktuelle Station");
    TableColumn speedColumn = new TableColumn<>("Geschwindigkeit");
    TableColumn brokenColumn = new TableColumn<>("Zustand");
    TableColumn timeColumn = new TableColumn<Train, Double>("Zeitplan");
    
    ArrayList<TableColumn> columns = new ArrayList<>();
    
    
    final ComboBox trainsChoose = new ComboBox(options);
    final ComboBox trainsKill = new ComboBox(options);
    
    private final PropertyChangeSupport propertySupport ;
    
    
    public Gui(StackPane root, ObservableList<Train> trainDataList, UBahn ubahn) {
        columns.add(trainNumberColumn);
        columns.add(currentStationColumn);
        columns.add(speedColumn);
        columns.add(brokenColumn);
        columns.add(timeColumn);
        
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
        
        deleteButton.setOnAction((ActionEvent e) -> {
            ubahn.deleteTrain(trainsKill.getSelectionModel().getSelectedItem().toString());
        });
        
        
        table.setEditable(true);
        
        trainNumberColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        //trainNumberColumn.setCellValueFactory(new PropertyValueFactory<>("trainNum"));
        trainNumberColumn.setMinWidth(200);
        trainNumberColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Train, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Train, Number> param) {
                try {
		final JavaBeanIntegerProperty valueProperty = new JavaBeanIntegerPropertyBuilder().bean(param.getValue()).name("trainNumber").build();
		return valueProperty;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		}
            }
        });
        
        currentStationColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        //currentStationColumn.setCellValueFactory(new PropertyValueFactory<>("stationNum"));
        currentStationColumn.setMinWidth(200);
        currentStationColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Train, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Train, Number> param) {
                try {
		final JavaBeanIntegerProperty valueProperty = new JavaBeanIntegerPropertyBuilder().bean(param.getValue()).name("stationNum").build();
		return valueProperty;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		}
            }
        });
        
        speedColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        //speedColumn.setCellValueFactory(new PropertyValueFactory<>("speed"));
        speedColumn.setMinWidth(200);
        speedColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Train, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Train, Number> param) {
                try {
		final JavaBeanDoubleProperty valueProperty = new JavaBeanDoublePropertyBuilder().bean(param.getValue()).name("speed").build();
		return valueProperty;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		}
            }
        });
        
        brokenColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        //brokenColumn.setCellValueFactory(new PropertyValueFactory<>("broken"));
        brokenColumn.setMinWidth(200);
        brokenColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Train, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Train, Boolean> param) {
                try {
		final JavaBeanBooleanProperty valueProperty = new JavaBeanBooleanPropertyBuilder().bean(param.getValue()).name("broken").build();
		return valueProperty;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		}
            }
        });
        
        timeColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        //timeColumn.setCellValueFactory(new PropertyValueFactory<>("approachingTime"));
        timeColumn.setMinWidth(200);
        timeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Train, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Train, Number> param) {
                try {
		final JavaBeanDoubleProperty valueProperty = new JavaBeanDoublePropertyBuilder().bean(param.getValue()).name("approachingTime").build();
		return valueProperty;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		}
            }
        });
        
        table.setItems(trainDataList);
        table.getColumns().addAll(trainNumberColumn, currentStationColumn, speedColumn, brokenColumn, timeColumn);
        
        
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
        grid.add(table, 1, 3, 8, 2);
        grid.add(resetButton, 6, 1);
        grid.add(deleteButton, 4, 2);
        
        root.getChildren().add(grid);
        
        propertySupport = new PropertyChangeSupport(this);
        //addPropertyChangeListener(listener);
        
    }

    public TableView getTable() {
        return table;
    }

    public ArrayList<TableColumn> getColumns() {
        return columns;
    }
    
    public void addTableData(){
        
    }
    
    public static void refreshTableView(TableView tableView, List<TableColumn> columns, List rows) {        
        tableView.getColumns().clear();
        tableView.getColumns().addAll(columns);

        ObservableList<String> list = FXCollections.observableArrayList(rows);
        tableView.setItems(list);
}
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertySupport.addPropertyChangeListener(listener);
		}
    
    
}
