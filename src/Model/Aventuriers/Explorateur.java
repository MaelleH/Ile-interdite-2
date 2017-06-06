/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;
import static Util.Utils.EtatTuile.ASSECHEE;
import static Util.Utils.EtatTuile.INONDEE;

public class Explorateur extends Aventurier {

    public Explorateur(Coordonnees position) {
        super(position);
    }

    @Override
    public String getNom() {
        return "Explorateur"; //To change body of generated methods, choose Tools | Templates.
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
                    if(g.getHSTuile().get(n).getEtat()!=ASSECHEE){
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
                    if(g.getHSTuile().get(n).getEtat()!=INONDEE){
                        bool=true;
                    }     
                }
                else if (super.assechementPossible(o, n, g)){
                    bool = true;
                }
                return bool;

		
	}    
                
        
}
