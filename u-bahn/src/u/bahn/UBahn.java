/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u.bahn;

import helper.StationList;
import defines.Station;
import gui.Gui;
import gui.GuiTick;
import helper.Train;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author mikko
 */
public class UBahn extends Application {
    
    public static final int HEIGHT = 1025;
    public static final int WIDTH = 500;
    
    public StationList stationList = new StationList();
    public Station startStation = new Station("1", 0, 10, false);
    
    public ArrayList<Train> trainList = new ArrayList();
    
    private final ObservableList<Train> trainDataList =
        FXCollections.observableArrayList();
    
    Gui gui;
    GuiTick guiTick;
    Thread guiTickT;
    
    
 
    
    @Override
    public void start(Stage primaryStage) { 
        
        stationList.initStations(startStation);
          
        StackPane root = new StackPane();
        gui = new Gui(root, trainDataList, this);
        guiTick = new GuiTick(this, trainDataList, trainList);
        
        Scene scene = new Scene(root, HEIGHT, WIDTH);
        
        primaryStage.setTitle("U-Bahn Linie");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }

    private void initTrains(Station startStation, int trainsNum) {
        newTrain(0, startStation);
        for (int i = 1; i < trainsNum; i++) {
            int next = (stationList.getSize()/trainsNum);
            //System.out.println("Züge: " + stationList.getSize() + " Anzahl: " + trainsNum + " Next: " + next);
            int k = (next * i) +1;
            Station station = stationList.getStation(k);
            newTrain(i, stationList.getNextStation(station));
            //System.out.println("set train on station number: " + k);
            
        }
    }
    
    private void newTrain(int trainNum, Station station){
        Train train = new Train(trainNum, station, stationList);
            trainList.add(train);
            new Thread(train).start();
            trainDataList.add(train);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    
    public void startTrains(String trainsNum){
        guiTick.setSimulate(true);
        System.err.println("start  all trains");
        trainDataList.removeAll(trainDataList);
        trainList.removeAll(trainList);
        int trains = Integer.parseInt(trainsNum);
        initTrains(startStation, trains);
        System.out.println(trainDataList);
        gui.getTable().setItems(trainDataList);
        guiTickT = new Thread(guiTick);
        guiTickT.start();
        
    }
    
    public void stopSimulation() {
        guiTick.setSimulate(false);
        System.err.println("Stopping simulation");
        for (int i = 0; i < trainList.size(); i++) {
            trainList.get(i).terminate();
        }
        
        
        //Ausgabe der Distanz welche die züge gefahren sind im MAtlab format
        try(  PrintWriter out = new PrintWriter( "trainDist"+ trainList.size() +".txt" )  ){
    

        for (int i = 0; i < trainList.size(); i++) {
            System.out.println(trainList.get(i).distToString()); 
            out.println(trainList.get(i).distToString());
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UBahn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Ausgabe des Delays in Datei schreiben
        try(  PrintWriter out = new PrintWriter( "trainApproach"+ trainList.size() +".txt" )  ){
    

        for (int i = 0; i < trainList.size(); i++) {
            System.out.println(trainList.get(i).approachToString()); 
            out.println(trainList.get(i).approachToString());
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UBahn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopTrain(String trainNum){
        int num = Integer.parseInt(trainNum);
        trainList.get(num-1).setBroken(true);
        System.err.println("Stopping train number " + num);
        
    }
    
    public void deleteTrain(String trainNum){
        int num = Integer.parseInt(trainNum);
        trainList.get(num-1).setBroken(false);
    }

    public ObservableList<Train> getTrainDataList() {
        return trainDataList;
    }

    public Gui getGui() {
        return gui;
    }
    
    
   
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    

}
