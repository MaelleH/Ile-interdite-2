package Model.Aventuriers;

public class Messager extends Aventurier {
    public Messager() {
    }
        
    @Override
    public String getNom() {
        return "Explorateur"; //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * 
    * @param aven1
    * @param aven2
    */
   public boolean donnerCartePossible(Aventurier aven1, Aventurier aven2) {
           // TODO - implement Messager.donnerCarte
           return true;
   }
}
