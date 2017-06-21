package Model.Aventuriers;

import Util.Coordonnees;
import Util.Utils;

public class Messager extends Aventurier {
    public Messager(Coordonnees position) {
        super(position);
    }
        
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Messager; //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * 
    * @param aven1
    * @param aven2
    */
    
    //permet au messagerde toujours donner des cartes
   public boolean donnerCartePossible(Aventurier aven1, Aventurier aven2) {
           // TODO - implement Messager.donnerCarte
           return true;
   }
}
