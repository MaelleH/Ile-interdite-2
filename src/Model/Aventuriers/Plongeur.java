package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;
import java.util.Map;

public class Plongeur extends Aventurier {

	/**
	 * @param g
	 * @param o
         * @param n
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
                
                if(super.deplacementPossible(o, n, g)){    
                        bool=true;
                } 
                else {
                    for(Map.Entry i: g.getHSTuile().entrySet()){
                        if(deplacementPossible((Coordonnees)i.getKey(), n, g)){
                             if(deplacementPossible((Coordonnees)i.getKey(), o, g)){
                                 bool=true;
                                 
                             }
                        else{
                          for(Map.Entry j: g.getHSTuile().entrySet()){
                            if(deplacementPossible(((Coordonnees)j.getKey()),((Coordonnees)i.getKey()) , g)){
                                if(deplacementPossible((Coordonnees)j.getKey(), o, g)){
                                    bool=true;
                                 
                            }       
                        }
                         }
                    }
                    
                }
                
                
	}
        
        


        }return bool;
        }
        
        
}
        