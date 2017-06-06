package Model.Aventuriers;

import Model.Coordonnees;

public class Pilote extends Aventurier {
        
	private boolean pouvoirUtilise;
        public Pilote(Coordonnees position) {
            super(position);
        }

        @Override
        public String getNom() {
            return "Pilote";
        }
}