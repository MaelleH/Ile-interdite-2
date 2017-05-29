/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;

public class Explorateur extends Aventurier {

    public Explorateur(String explorateur) {
        super.setNom(explorateur);
    }
    
    /**
     *
     * @param o
     * @param n
     * @param g
     * @return
     */
    @Override
        public boolean deplacementPossible(Coordonnees o,Coordonnees n,Grille g) {
		// TODO - implement Controleur.deplacementPossible
                int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(o.getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(o.getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))){
                    if(g.getHSTuile().get(n).getEtat()!=2){
                        bool=true;
                    }     
                }
                else if (super.deplacementPossible(o, n, g)){
                    bool = true;
                }
                return bool;

		
	}    
        
        @Override
        public boolean assechementPossible(Coordonnees o,Coordonnees n,Grille g) {
		// TODO - implement Controleur.deplacementPossible
                int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(o.getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(o.getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))){
                    if(g.getHSTuile().get(n).getEtat()!=2){
                        bool=true;
                    }     
                }
                else if (super.assechementPossible(o, n, g)){
                    bool = true;
                }
                return bool;

		
	}    
                
        
}