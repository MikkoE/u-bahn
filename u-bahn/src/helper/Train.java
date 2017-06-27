/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import defines.TrainPosition;
import defines.Station;
import gui.Gui;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mikko Eberhardt
 * Ein zug kann kaputt oder heil sein
 * Ein Zug hat 2 Fahrzustände (beschleunigen und fahren) (bremsen wird ausser acht gelassen)
 */
public class Train implements Runnable{
    
    public static final int TICK = 250;
    public static final int DRIVINGSPEED = 20;
    public static final int DRIVINGTIME = 25;
    public static final int STOPTIME = 10;
    
    private final PropertyChangeSupport propertySupport ;
    
    private ArrayList<Double> trainDist;
    
    private boolean broken;
    private int trainNumber;
    private Station currentStation;
    private Station nextStation;
    private int currentStationNum;
    private double currentSpeed;
    
    //variablen für die anzeige
    private final SimpleIntegerProperty trainNumTab;
    private final SimpleIntegerProperty stationNumTab;
    private final SimpleDoubleProperty speedTab;
    private final SimpleStringProperty brokenTab;
    private final SimpleDoubleProperty timeTab;
    
    //counter für die strecke der bahn
    int timeCounter;
    double dist;
    
    //time val to approach
    private double approachingTime = STOPTIME;
    private double accelerate = 0;
    
    //position des Zuges
    private TrainPosition position;
    
    private StationList stationList;
    
    // Enum for train states (station/stop and driving
    State.TrainState state;
    
    private volatile boolean running = true;
    boolean start;
    boolean first = true;
    
    
    /**
     * 
     * @param number
     * @param currentStation
     * @param stationList 
     */
    public Train(int number, Station currentStation, StationList stationList){
        trainNumber = number;
        this.currentStation = currentStation;
        this.currentStationNum = currentStation.getPosition();
        broken = false;
        position = new TrainPosition(1, 1);
        setSpeed(0);
        state = State.TrainState.STOP;
        this.stationList = stationList;
        start = true;
        trainDist = new ArrayList<>();
        
        
        //anzeige
        this.trainNumTab =  new SimpleIntegerProperty(trainNumber);
        this.stationNumTab =  new SimpleIntegerProperty(currentStation.getPosition());
        this.speedTab =  new SimpleDoubleProperty(currentSpeed);
        this.brokenTab =  new SimpleStringProperty("" + broken);
        this.timeTab =  new SimpleDoubleProperty(approachingTime);
        
        this.propertySupport = new PropertyChangeSupport(this);
        
    }
     
    public boolean getBroken(){
        return broken;
    }
    
    public void setBroken(boolean broken){
        this.broken = broken;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }
    
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public TrainPosition getPosition() {
        return position;
    }

    public void setPosition(TrainPosition position) {
        this.position = position;
    }

    public Station getCurrentStation() {
        return currentStation;
    }
    
    public SimpleIntegerProperty trainNumProperty(){
        return this.trainNumTab;
    }
    
    public SimpleIntegerProperty stationNumProperty(){
        return this.stationNumTab;
    }
    
    public SimpleDoubleProperty speedProperty(){
        return this.speedTab;
    }
    
    public SimpleStringProperty brokenProperty(){
        return this.brokenTab;
    }
    
    public SimpleDoubleProperty timeProperty(){
        return this.timeTab;
    }

    public double getApproachingTime() {
        return approachingTime;
    }

    public void setApproachingTime(double approachingTime) {
        double old = this.approachingTime ;
        this.approachingTime = approachingTime;
	propertySupport.firePropertyChange("approachingTime", old, approachingTime);
    }

    public int getStationNum() {
        return currentStationNum;
    }
    
    public void setStationNum(int stationNum){
        currentStationNum = stationNum;
    }

    public double getSpeed() {
        return currentSpeed;
    }
    
    public void setSpeed(double speed){
        currentSpeed = speed;
    }
    
    
    
    
    

    @Override
    public void run() {
        timeCounter = 0;
        dist = 0;
        
        //hier wird das verhalten eines zuges abgebildet 
        while (running) {
            if(!broken){
            
            dist += getSpeed();
            trainDist.add(dist);    
            
            switch (state) {
            case DRIVING:
                if (currentSpeed >= DRIVINGSPEED){
                    //System.err.println("Train " + trainNumber + " driving with max Speed");
                }else{
                    //accelerate
                    setSpeed(currentSpeed + accelerate);

                    //System.err.println("accelerate....");
                }
                position.x = position.x + (timeCounter);
                //System.out.println(position.x);
                timeCounter++;
                
                setApproachingTime(approachingTime--);
                //System.out.println("Train " + trainNumber + " approaching time :" + approachingTime);
                
                if (timeCounter >= nextStation.getDistance()) {
                    timeCounter = 0;
                    
                    if (currentStation.isSignal() && !stationList.getNextStation(currentStation).getHasTrain()){
                        blockNextSegment();
                        setApproachingTime(currentStation.getDistance());
                        System.err.println("Green signal for train before Station " + currentStation.getPosition());
                    }else{
                       currentSpeed = 0;
                       state = State.TrainState.STOP;
                       approachingTime = STOPTIME;
                       System.err.println("Train " + trainNumber + " Stoped at Station " + currentStation.getPosition());
                    }
                }
                
                break;
                    
            case STOP:
                timeCounter++;
                approachingTime--;
                
                //System.out.println("Train " + trainNumber + " stoped time :" + approachingTime);
                if (timeCounter >= STOPTIME) {
                    timeCounter = 0;
                    blockNextSegment();
                    approachingTime = currentStation.getDistance();
                    state = State.TrainState.DRIVING;
                    accelerate = Math.random() +0.5; 
                    }
                 break;
                }
            }else {
                trainDist.add(dist);
            }
            
            try {
                Thread.sleep(TICK);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void blockNextSegment() { 
        nextStation = stationList.getNextStation(currentStation);
        
        while (nextStation.getHasTrain() && running) {
            approachingTime -= 2;
            trainDist.add(dist);
            try {
                Thread.sleep(250);
                if (!start){
                    System.out.println("Train " + trainNumber + " waiting for free segment. Delayed: " +approachingTime);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        start = false;
        if(stationList.getIndexOf(nextStation) == 1){
            timeCounter = 0;
            position.x = 0;
            if (first){
                first = false;
            }else{
                dist = 0;
            }
            
        }
        currentStation.setHasTrain(false);
        currentStation = nextStation;
        currentStationNum = currentStation.getPosition();
        currentStation.setHasTrain(true);
        
        
    }

    public void terminate() {
        currentStation.setHasTrain(false);
        running = false;
    }
    
    public String distToString(){
        String result = "y" + trainNumber + " = [";
        /*for (int i = 0; i < trainNumber; i++) {
            result += "0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 ";
            
        }*/
        for (int i = 0; i < trainDist.size(); i++) {
           result += trainDist.get(i) + " ";
        }
        result += "];";
        return result;
    }
    
    
}
