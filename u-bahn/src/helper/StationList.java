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
    
    public int getSize(){
        return stationList.size();
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
        
        this.addStation(new Station("7", 6, 11, false));
        
        this.addStation(new Station("8", 7, 11, true));
        
        this.addStation(new Station("9", 8, 11, false));
         
        this.addStation(new Station("10", 9, 11, true));
         
        this.addStation(new Station("11", 10, 11, false));
         
        this.addStation(new Station("12", 11, 11, true));
         
        this.addStation(new Station("13", 12, 11, false));
         
        this.addStation(new Station("14", 13, 11, true));
         
        this.addStation(new Station("15", 14, 11, false));
         
        this.addStation(new Station("16", 15, 11, true)); 
        
        this.addStation(new Station("17", 16, 11, false)); 
        
        this.addStation(new Station("18", 17, 11, true));
         
        this.addStation(new Station("19", 18, 11, false));
        
        this.addStation(new Station("20", 19, 11, true));
        
    }
    public Station getStation(int index){
        return stationList.get(index);
    } 
    
    
    
    
}
