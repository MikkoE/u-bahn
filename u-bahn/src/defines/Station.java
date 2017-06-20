/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defines;

/**
 *
 * @author mikko
 */
public class Station {
    
    // Eine Station hat zwei richtungen 
    // Up kennzeichnet die Erste Fahrtrichtung
    // Down kennzeichnet den Rückweg
    private boolean hasTrain;
    private boolean signal;
    
    private final String name;
    private final int position;
    private final int distance;
    
    
    public Station(String name, int position, int distance, boolean signal){
       hasTrain = false;
       this.name = name;
       this.position = position;
       this.distance = 25;
       this.signal = signal;
    }
    
    public boolean getHasTrain(){
        return hasTrain;
    }
    
    public void setHasTrain(boolean hasTrain){
        if (hasTrain){
            System.err.println("New Train Approaching at Station: " + position);
        }else{ 
            System.err.println("Train Leaving Station:" + position);
        }
        this.hasTrain = hasTrain;
    }

    public int getPosition() {
        return position;
    }

    public int getDistance() {
        return distance;
    }
    
    public boolean isSignal(){
        return signal;
    }
    
    public boolean isStation(){
        return !signal;
    }
    
    
    

}
