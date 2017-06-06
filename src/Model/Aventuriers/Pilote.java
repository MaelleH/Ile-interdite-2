package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import static Util.Utils.EtatTuile.COULEE;
import java.util.HashMap;
import java.util.Map;

public class Pilote extends Aventurier {
        
	private boolean pouvoirUtilise;
        public Pilote(Coordonnees position) {
            super(position);
        }

        @Override
        public String getNom() {
            return "Pilote";
        }
        @Override
        public HashMap deplacementPossibleListe(Grille grille) {
		// TODO - implement Controleur.deplacementPossible
                                
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    if((((Tuile)i.getValue()).getEtat()!=COULEE)){
                        listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
                    }        
                }

		return listeD;
                
	}
        
        
}
