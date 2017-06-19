package Model.Aventuriers;

import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import static Util.Utils.EtatTuile.COULEE;
import static Util.Utils.afficherInformation;
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
        public void autreAction(Coordonnees c,Grille grille ) {

            System.out.println("Position aventurier" +getPosition().getX()+getPosition().getY());
            if(this.getActionsRestantes()>0&&autreActionListe(grille).containsKey(c)){
		setPosition(c);
                setActionsRestantes(getActionsRestantes()-1);
                System.out.println(getPosition().getX()+getPosition().getY());
                
            }
            else if(this.getActionsRestantes()<1){
                System.out.println("Plus d'actions....");
                afficherInformation("Vous ne pouvez plus effectuer d'actions!");
            }
            else{
                System.out.println("Déplacement impossible!");
                afficherInformation("Vous ne pouvez pas vous déplacer vers cette case!");
            }
	}
        
        
        
        public HashMap autreActionListe(Grille grille) {
		// TODO - implement Controleur.deplacementPossible
                                
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
                    if(i.getValue()!=null){
                        if((((Tuile)i.getValue()).getEtat()!=COULEE)){
                            listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                            System.out.println(Integer.toString(xn)+Integer.toString(yn));
                            System.out.println(((Tuile)i.getValue()).getNomT());    
                        } 
                    }
                }

		return listeD;
                
	}
        
        
}
