/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import defines.Station;
import java.util.ArrayList;

/**
 *
 * @author mikko
 */
public class StationList {
    
    private ArrayList<Station> stationList;

    public StationList() { 
        stationList = new ArrayList<>();
    }
    
    public void addStation(Station station){
        stationList.add(station);
    }
    
    public Station getNextStation(Station currentStation){
        if (stationList.indexOf(currentStation)+1 >= stationList.size()){
            
            return stationList.get(0);
        }else{
           return stationList.get(stationList.indexOf(currentStation)+1); 
        }
        
    }

    public ArrayList<Station> getStationList() {
        return stationList;
    }
    
    public int getIndexOf(Station station){
        return stationList.indexOf(station);
    }   
    
    public void initStations(Station startStation) {
        this.addStation(startStation);
        
        this.addStation(new Station("2", 1, 7, true));
        
        this.addStation(new Station("3", 2, 9, false));
        
        this.addStation(new Station("4", 3, 14, true));
        
        this.addStation(new Station("5", 4, 10, false));
        
        this.addStation(new Station("6", 5, 11, true));
        
        this.addStation(new Station("7", 5, 11, false));
        
        this.addStation(new Station("8", 5, 11, true));
        
        this.addStation(new Station("9", 5, 11, false));
         
        this.addStation(new Station("10", 5, 11, true));
         
        this.addStation(new Station("11", 5, 11, false));
         
        this.addStation(new Station("12", 5, 11, true));
         
        this.addStation(new Station("13", 5, 11, false));
         
        this.addStation(new Station("14", 5, 11, true));
         
        this.addStation(new Station("15", 5, 11, false));
         
        this.addStation(new Station("16", 5, 11, true)); 
        
        this.addStation(new Station("17", 5, 11, false)); 
        
        this.addStation(new Station("18", 5, 11, true));
         
        this.addStation(new Station("19", 5, 11, false));
        
        this.addStation(new Station("20", 5, 11, true));
        
    }
    
    
    
}
