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
public class TrainPosition {
    public double x;
    public double y;
    public double speed = 0;
    public double accelerate = 0;

    public TrainPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    
}
