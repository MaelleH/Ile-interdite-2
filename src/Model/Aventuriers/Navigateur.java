package Model.Aventuriers;

import Util.Coordonnees;
import Util.Utils;

public class Navigateur extends Aventurier { 
    public Navigateur(Coordonnees position) {
        super(position);
    }
    
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Navigateur;
    }
    @Override
    public int getMaxActions(){
        return 4;
    }
}
