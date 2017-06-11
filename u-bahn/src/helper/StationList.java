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
    
    
    
}
