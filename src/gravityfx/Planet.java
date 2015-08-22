package gravityfx;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Planet extends CircleCelestial
{
    private final double PI = 3.1415;

    

    public Planet (Planet p, Point2D origin){
        this.origin = origin;
        velocityX = p.getVelocityX();
        velocityY = p.getVelocityY();
        point = new Point2D(p.getX(), p.getY());
        this.setCenterX(point.getX());
        this.setCenterY(point.getY());
        color = new Color(p.getColor().getRed(), p.getColor().getGreen(),p.getColor().getBlue(), p.getColor().getOpacity());
        this.setRadius(p.getRadius());
        mass = PI*(this.getRadius()*this.getRadius());
        
    }
    public Planet(Planet a, Planet b, Point2D origin){
        this.origin = origin;
        this.setMass(a.getMass()+b.getMass());
        velocityX = (a.getMass()*a.getVelocityX()+b.getMass()*b.getVelocityX())/mass;
        velocityY = (a.getMass()*a.getVelocityY()+b.getMass()*b.getVelocityY())/mass;
        
        if (a.getMass()>b.getMass()){
            this.point = new Point2D(a.getX(), a.getY());
            this.color = new Color(a.getColor().getRed(), a.getColor().getGreen(),a.getColor().getBlue(), a.getColor().getOpacity());
        }
        else{
            this.point = new Point2D(b.getX(), b.getY());
            this.color = new Color(b.getColor().getRed(), b.getColor().getGreen(),b.getColor().getBlue(), b.getColor().getOpacity());
        }
        this.setCenterX(point.getX());
        this.setCenterY(point.getY());
    }
    public Planet(double radius, Point2D origin)
    {   
        this.origin = origin;
        velocityX = 0;
        velocityY = 0;
        color = Color.BLUE;
        this.setCenterX(0);
        this.setCenterY(0);
        this.setRadius(radius);
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    }
    
    public Planet(double radius, double x, double y, Point2D origin)
    {   
        this.origin = origin;
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        color = Color.BLUE;
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    
    }
    
    public Planet(double radius, double x, double y, Color color, Point2D origin)
    {   
        this.origin = origin;
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        this.color = color;
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    
    }
    public void updateRadius(double radius){
        
    }
    @Override
    public double getMass(){
        return mass;
    }    
    
    @Override
    public void setMass(double mass){
        this.mass = mass;
        this.setRadius(Math.sqrt(mass/(PI)));
    }
   
    public void renderLines(GraphicsContext gc, Planet p, Point2D origin){
        gc.setStroke(Color.WHITE);
        gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
        //gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ this.getY());
        //gc.strokeLine(origin.getX()+p.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
    }
    
    
    @Override
    public boolean intersects(Canvas c){
        return ((this.getPoint().getX() < this.getRadius())||(this.getPoint().getX() > c.getWidth()-this.getRadius())
                ||(this.getPoint().getY() < this.getRadius())||this.getPoint().getY() > c.getHeight() - this.getRadius());
    }
    
    @Override
    public String toString()
    {
        return " Position: [" + this.getCenterX() + "," + this.getCenterY() + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "] Color: " +color.toString() +" Hashcode: "+this.hashCode();
    }
}
