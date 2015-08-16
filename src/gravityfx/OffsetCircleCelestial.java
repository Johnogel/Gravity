/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Johnogel
 */
public class OffsetCircleCelestial extends CircleCelestial{
    
    private Point2D origin;
    
    public Point2D getOrigin(){
        
        return origin;
    }
    
    public void setOrigin(double x, double y){
        
        this.origin = new Point2D(x, y);
    }
    
    public void setOrigin(Point2D point){
        
        this.origin = new Point2D(point.getX(), point.getY());
    }
    
    @Override
    public void render(GraphicsContext gc){
        
        gc.setFill(color);
        gc.fillOval(origin.getX()+this.getCenterX()-this.getRadius(), origin.getY() + this.getCenterY() - this.getRadius(), 
                this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius());
    
    }
    
}
