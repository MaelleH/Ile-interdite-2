package Model;

public class Coordonnees implements Comparable<Coordonnees>{
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

    @Override
    public int compareTo(Coordonnees o) {
        if(this.getX().compareTo(o.getX())<0){
            return -1;
        }else if(this.getX().compareTo(o.getX())==0){
            if (this.getY().compareTo(o.getY())<0) {
               return -1; 
            }else if(this.getY().compareTo(o.getY())==0){
                return 0;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
        

    }
        
        
    
    
    
}