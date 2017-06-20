package Model.Aventuriers;

import Model.Coordonnees;
import Util.Utils;

public class Navigateur extends Aventurier { 
    public Navigateur(Coordonnees position) {
        super(position);
    }
    
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Navigateur; //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int getMaxActions(){
        return 4;
    }
}
