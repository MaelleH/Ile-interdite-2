package Model.Aventuriers;

import Model.CarteTrésor;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import java.util.*;
import java.util.HashMap;

public class Aventurier {

	private Collection<CarteTrésor> mainCarteTrésor;
	private int actionsRestantes;
	private int ACTIONS_MAX = 3;
	private Tuile position;

	public Tuile getPosition() {
		return this.position;
	}

	public HashMap caseEnvisagable(Coordonnees c,Grille g) {
		// TODO - implement Aventurier.caseEnvisagable
                
                HashMap<Coordonnees,Tuile> listeE;   
		Tuile p = new Tuile();
                p = this.getPosition();
                
                for(g.getHSTuile().entrySet()){
                    if(deplacementPossible()){
                        
                }

	}
        }
        public boolean deplacementPossible(Coordonnees o,Coordonnees n,Grille g) {
		// TODO - implement Controleur.deplacementPossible
                int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(o.getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(o.getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn)&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))){
                    if(g.getHSTuile().get(n).getEtat()!=2){
                        bool=true;
                    }     
                }
                return bool;

		
	}

}