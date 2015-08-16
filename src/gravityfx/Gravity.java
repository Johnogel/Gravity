/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;


/**
 *
 * @author Johnogel
 */
public class Gravity {
private ArrayList<Planet> planets;

    
    public Gravity(ArrayList<Planet> planets){
        this.planets = planets;
    }
    public void gravitate(Planet a, Planet b, double time){
        if(!a.equals(b)){
            a.gravitateTo(b, time);
            b.gravitateTo(a, time);
            
        }
    }
    
    public boolean collisionTest(Planet a, Planet b){
        if(!a.equals(b))
            return a.intersects(b);
        return false;
    }
    
    public void combine(Planet a, Planet b)
    {
        if(!a.equals(b)){
            if(collisionTest(a, b)){
                
                if (planets.indexOf(a)>planets.indexOf(b)){
                    Planet c = new Planet(a, b);
                    planets.set(planets.indexOf(b), c);
                    planets.remove(a);
                }

                
                else{
                    Planet c = new Planet(a, b);
                    planets.set(planets.indexOf(a), c);
                    planets.remove(b);
                }
            }
        }
    }
    
    
    public void attract(double time){
        for (int i = 0; i < planets.size(); i ++){
            for(int j =i; j < planets.size(); j++){
                    
                    if (i >= planets.size()||j >= planets.size()){
                        
                        if(i != 0){
                            i -= 1;
                            j -= 1;
                        }
                        else{
                            j -= 1;
                            
                        }
                        
                    }
                if(!collisionTest(planets.get(i), planets.get(j))){
                    gravitate(planets.get(i), planets.get(j), time);
                    
                }
                combine(planets.get(i), planets.get(j));
                    
                    
                    
                    
            }
        }
        
        

//            iterPlanet = planets.iterator();
//            testPlanet = iterPlanet.next();
//            
//            do{
//                
//                
//                if (!testPlanet.equals(p) && (p.distance(testPlanet) > (p.getRadius()+testPlanet.getRadius())))
//                {
//                    a = G*(p.getRadius()*1.567e20)/(p.distance(testPlanet)*1.43e23*p.distance(testPlanet));
//                    
//                    dvX = a*Math.cos(testPlanet.getPoint().angle(p.getPoint(), new Point2D(p.getCenterX(), testPlanet.getCenterY())))*time;
//                    dvY = (a*Math.sin(testPlanet.getPoint().angle(p.getPoint(), new Point2D(p.getCenterX(), testPlanet.getCenterY())))*time);
//                    
//                    testPlanet.addVelocity(dvX, dvY);
//                }
                
          
            
        
    }
    public void attract(double time, GraphicsContext gc, Point2D origin){
        for (int i = 0; i < planets.size(); i ++){
            for(int j =i; j < planets.size(); j++){
                    
                    if (i >= planets.size()||j >= planets.size()){
                        
                        if(i != 0){
                            i -= 1;
                            j -= 1;
                        }
                        else{
                            j -= 1;
                            
                        }
                        
                    }
                if(!collisionTest(planets.get(i), planets.get(j))){
                    gravitate(planets.get(i), planets.get(j), time);
                    
                }
                planets.get(i).renderLines(gc, planets.get(j),origin);
                combine(planets.get(i), planets.get(j));
                
                    
                    
                    
                    
            }
        }
    }
}
    
    
    
    

