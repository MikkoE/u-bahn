/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author mikko
 */
public class TrainImage extends ImageView{
    
    private double mouseX ;
    private double mouseY ;
    public TrainImage(Image image, Train train) {
        
        super(image);
        DoubleProperty trainX = new SimpleDoubleProperty();
        
        trainX.addListener(new ChangeListener(){
            

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                train.getPosition().x;
            }
        });
        
    
        setOnMousePressed(event -> {
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
        });
        
        setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX ;
            double deltaY = event.getSceneY() - mouseY ;
            relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
            mouseX = event.getSceneX() ;
            mouseY = event.getSceneY() ;
        });
    }
}
