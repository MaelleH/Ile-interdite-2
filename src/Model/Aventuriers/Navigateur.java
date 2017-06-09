package Model.Aventuriers;

import Model.Coordonnees;

public class Navigateur extends Aventurier { 
    public Navigateur(Coordonnees position) {
        super(position);
    }
    
    @Override
    public String getNom() {
        return "Navigateur";
    }
}
