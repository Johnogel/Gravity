/*
 * To change a license header, choose License Headers in Project Properties.
 * To change a template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

/**
 *
 * @author Johnogel
 */
public abstract class SpaceMath {
    
    public static double distanceSquared(SpaceBody a, SpaceBody b){
        
        return(((a.getX()-b.getX())*(a.getX()-b.getX()))+((a.getY()-b.getY())*(a.getY()-b.getY())));
        
    }
}
