/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Util.Utils;
import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import static Util.Utils.EtatTuile.COULEE;
import static Util.Utils.EtatTuile.INONDEE;
import java.util.HashMap;
import java.util.Map;

public class Explorateur extends Aventurier {

    public Explorateur(Coordonnees position) {
        super(position);
    }

    @Override
    public String getNom() {
        return "Explorateur"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    @Override
    public HashMap deplacementPossibleListe(Grille grille) {
		// TODO - implement Controleur.deplacementPossible
                                
                /*HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    if((((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))||((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))))&&((! i.getValue().getEtat().equals(Utils.EtatTuile.COULEE)))){
                        listeD.put((Coordonnees) i.getKey(), i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
                    }        
                }

		return listeD;*/
                
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)(i.getKey()).getX());
                    yn=Integer.parseInt((String)(i.getKey()).getY());

                    
                    
                    if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((! i.getValue().getEtat().equals(EtatTuile.COULEE)))){
                        listeD.put((Coordonnees) i.getKey(), i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
                    }        
                }

		return listeD;
                
	}
      
        
        @Override
        public HashMap assechementPossibleListe(Grille grille) {
		// TODO - implement Controleur.deplacementPossible
                /*HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                
                for(Map.Entry<Coordonnees,Tuile> i: g.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    if((((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))||((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))))&&((i.getValue().getEtat().equals(Utils.EtatTuile.COULEE)))){
                        listeD.put( i.getKey(), i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
                    }        
                }

		return listeD;*/
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i : grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)(i.getKey()).getX());
                    yn=Integer.parseInt((String)(i.getKey()).getY());

                    Tuile t = i.getValue();
                    
                    if((((xo==xn)&&(yo==yn))||(((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((i.getValue().getEtat().equals(Utils.EtatTuile.INONDEE)))){
                        listeD.put( i.getKey(), i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println((i.getValue()).getNomT());    
                    }        
                }

		return listeD;

		
	}    
                
        
}
