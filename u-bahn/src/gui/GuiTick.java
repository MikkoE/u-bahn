/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import helper.Train;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import u.bahn.UBahn;

/**
 *
 * @author mikko
 */
public class GuiTick implements Runnable{
    
    public static final long TICK = 10;
    private UBahn ubahn;
    
    public ArrayList<Train> trainList = new ArrayList();
    
    private ObservableList<Train> trainDataList =
        FXCollections.observableArrayList();

    public GuiTick(UBahn ubahn, ObservableList<Train> trainDataList, ArrayList<Train> trainList) {
        this.trainDataList = trainDataList;
        this.trainList = trainList;
        this.ubahn = ubahn;
    }
    
    
    
    @Override
    public void run() {
        trainDataList.removeAll(trainDataList); 
        Gui.refreshTableView(ubahn.getGui().table, ubahn.getGui().columns, trainList);
        
        
        
        try {
                Thread.sleep(TICK);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
