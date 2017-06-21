package Util;

import java.util.Objects;

public class Coordonnees{
	private String x;
	private String y;

    public Coordonnees(String x, String y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode(){
        return (x+y).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnees other = (Coordonnees) obj;
        if (!Objects.equals(this.x, other.x)) {
            return false;
        }
        if (!Objects.equals(this.y, other.y)) {
            return false;
        }
        return true;
    }
    /**
     * @return the x
     */
    public String getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(String x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public String getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(String y) {
        this.y = y;
    }

    

    
    public boolean equals(Coordonnees o) {
        int xt = Integer.parseInt(this.getX());
        int yt = Integer.parseInt(this.getY());
        
        int xo = Integer.parseInt(o.getX());
        int yo = Integer.parseInt(o.getY());
        
        return (xt==xo) && (yt==yo); //To change body of generated methods, choose Tools | Templates.
    }
        
        
    
    
    
}
