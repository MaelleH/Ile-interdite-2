package Model.Aventuriers;

import Model.Coordonnees;

public class Messager extends Aventurier {
    public Messager(Coordonnees position) {
        super(position);
    }
        
    @Override
    public String getNom() {
        return "Messager"; //To change body of generated methods, choose Tools | Templates.
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
