/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import defines.TrainPosition;
import defines.Station;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private boolean broken;
    private int trainNumber;
    private Station currentStation;
    private Station nextStation;
    private double currentSpeed;
    
    //counter für die strecke der bahn
    int timeCounter;
    
    //position des Zuges
    private TrainPosition position;
    
    private StationList stationList;
    
    // Enum for train states (station/stop and driving
    State.TrainState state;
    
    
    /**
     * 
     * @param number
     * @param currentStation
     * @param stationList 
     */
    public Train(int number, Station currentStation, StationList stationList){
        trainNumber = number;
        this.currentStation = currentStation;
        broken = false;
        position = new TrainPosition(1, 1);
        currentSpeed = 0;
        state = State.TrainState.STOP;
        this.stationList = stationList;
        nextStation = stationList.getNextStation(currentStation);
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
    
    private void accelerate() {
        currentSpeed += 1;
        //System.err.println("Accelerate: " + currentSpeed);
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
    
    
    
    
    
    
    

    @Override
    public void run() {
        timeCounter = 0;
        
        //hier wird das verhalten eines zuges abgebildet 
        while (true) {
            switch (state) {
            case DRIVING:
                if (currentSpeed >= DRIVINGSPEED){
                    //System.err.println("Train " + trainNumber + " driving with max Speed");
                }else{
                    accelerate();
                    //System.err.println("accelerate....");
                }
                position.x = (position.x + (timeCounter)) % 588;
                System.out.println(position.x);
                timeCounter++;
                
                if (timeCounter >= nextStation.getDistance()) {
                    timeCounter = 0;
                    if (currentStation.isSignal() && !stationList.getNextStation(currentStation).getHasTrain()){
                        blockNextSegment();
                        System.err.println("Green signal for traing " + trainNumber);
                    }else{
                       currentSpeed = 0;
                       state = State.TrainState.STOP;
                        System.err.println("Train " + trainNumber + " Stoped at Station " + currentStation.getPosition());
                    }
                }
                
                break;
                    
            case STOP:
                timeCounter++;
                
                
                if (timeCounter >= STOPTIME) {
                    timeCounter = 0;
                    blockNextSegment();
                    state = State.TrainState.DRIVING;
                }
                break;
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
        
        while (nextStation.getHasTrain()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        if(stationList.getIndexOf(nextStation) == 1){
            timeCounter = 0;
        }
        currentStation.setHasTrain(false);
        currentStation = nextStation;
        currentStation.setHasTrain(true);
        
        
    }

    
    
    
}
