package Model.Aventuriers;

import Util.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Util.Utils;
import Util.Utils.NomAventurier;
import java.util.HashMap;
import java.util.Map;

public class Explorateur extends Aventurier {

    public Explorateur(Coordonnees position) {
        super(position);
    }

    @Override
    public NomAventurier getNom() {
        return NomAventurier.Explorateur;
    }
    
    //permet à l'explorateur de se déplacer en diagonale
    @Override
    public HashMap deplacementPossibleListe(Grille grille) {
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());      //Coordonnées de l'aventurier
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){        //On parcourt toutes les tuiles de la grille
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());          //coordonnées de la tuile
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
                    if(i.getValue()!=null){     //Si la tuile existe
                        //Si la case est autour de la case de l'aventurier
                        if((((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))||((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))))&&((! i.getValue().getEtat().equals(Utils.EtatTuile.COULEE)))){
                            listeD.put((Coordonnees) i.getKey(), i.getValue());     //On l'ajoute a la liste de deplacements possibles
                        }  
                    }
                }
		return listeD;
	}
      
        //permet à l'explorateur d'asséecher en diagonale, meme principe que deplacementPossibleListe(Grille grille)
        @Override
        public HashMap assechementPossibleListe(Grille grille) {
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
                    if(i.getValue()!=null){
                        xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                        yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
                        //Si la case est autour de la case de l'aventurier
                        if(((xo==xn && yo==yn)||((xo==xn+1||(xo==xn-1))&&(yo==yn-1||yo==yn+1))||((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))))&&((i.getValue().getEtat().equals(Utils.EtatTuile.INONDEE)))){
                            listeD.put( i.getKey(), i.getValue());                  //On l'ajoute a la liste des assechements possibles
                        }        
                    }
                }
		return listeD;
	}
        
}
