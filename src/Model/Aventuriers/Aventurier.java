package Model.Aventuriers;

import Model.CarteTrésor;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import java.util.*;
import java.util.HashMap;

public class Aventurier {
        private String nom;
	private Collection<CarteTrésor> mainCarteTrésor;
	private int actionsRestantes;
	private int ACTIONS_MAX = 3;
	private Coordonnees position;

        
    public Aventurier(){
        
    }
    public Aventurier(String nom) {
        this.nom = nom;
    }

        

        

	public Coordonnees getPosition() {
		return this.position;
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
	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public boolean assechementPossible(Coordonnees o,Coordonnees n,Grille g) {
		// TODO - implement Controleur.assèchementPossible
		int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(o.getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(o.getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn)&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))){
                    if(g.getHSTuile().get(n).getEtat()==1){
                        bool=true;
                    }     
                }
                return bool;
	}

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the mainCarteTrésor
     */
    public Collection<CarteTrésor> getMainCarteTrésor() {
        return mainCarteTrésor;
    }

    /**
     * @param mainCarteTrésor the mainCarteTrésor to set
     */
    public void setMainCarteTrésor(Collection<CarteTrésor> mainCarteTrésor) {
        this.mainCarteTrésor = mainCarteTrésor;
    }

    /**
     * @return the actionsRestantes
     */
    public int getActionsRestantes() {
        return actionsRestantes;
    }

    /**
     * @param actionsRestantes the actionsRestantes to set
     */
    public void setActionsRestantes(int actionsRestantes) {
        this.actionsRestantes = actionsRestantes;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Coordonnees position) {
        this.position = position;
    }
}