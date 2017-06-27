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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import u.bahn.UBahn;

/**
 *
 * @author mikko
 */
public class GuiTick implements Runnable{
    
    public static final long TICK = 250;
    private UBahn ubahn;
    
    boolean simulate = false;
    
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
        while (simulate){
            guiTickT.run();
            
            //System.out.println("Refresh Table!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    Task<Void> guiTickT = new Task<Void>() {

     @Override public Void call() throws Exception {
        Platform. runLater(new Runnable() {
            @Override
            public void run(){
                trainDataList.removeAll(trainDataList); 
                Gui.refreshTableView(ubahn.getGui().table, ubahn.getGui().columns, trainList);
                

            }
                
            
        });
        return null;
        }
    };
    
    
    

   /* public Task<Void> getTask() {
        return task;
    }*/

    public void setSimulate(boolean simulate) {
        this.simulate = simulate;
    }
    
    
    
}


