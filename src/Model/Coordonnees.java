package Model;

public class Coordonnees{
	private String x;
	private String y;

    public Coordonnees(String x, String y) {
        this.x = x;
        this.y = y;
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
        return (this.getX().equals(o.getX()) && this.getY().equals(o.getY())); //To change body of generated methods, choose Tools | Templates.
    }
        
        
    
    
    
}
