package Model.Aventuriers;

import Model.Coordonnees;
import Util.Utils;

public class Ingenieur extends Aventurier {
    public Ingenieur(Coordonnees position) {
        super(position);
    }
    
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Ingenieur; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
