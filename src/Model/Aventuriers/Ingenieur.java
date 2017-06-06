package Model.Aventuriers;

import Model.Coordonnees;

public class Ingenieur extends Aventurier {
    public Ingenieur(Coordonnees position) {
        super(position);
    }
    
    @Override
    public String getNom() {
            return "Ingenieur";
    }
    
    
    
    
}