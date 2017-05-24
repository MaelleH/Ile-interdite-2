import Model.CarteTrésor;
import Model.Coordonnees;
import java.util.*;

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
		Tuile p = new Tuile();
                p = this.getPosition();
                for(Coordonnees i : g.getHSTuile()){
                    if(deplacementPossible()){
                      return true;   
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
                    if(g.get(n).getEtat()!=2){
                        bool=true;
                    }     
                }
                return bool;

		
	}

}