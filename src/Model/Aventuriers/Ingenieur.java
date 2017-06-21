package Model.Aventuriers;

import Model.cartesTresor.CarteTrésor;
import Util.Coordonnees;
import Model.Grille;
import Util.NomTrésor;
import Util.Utils;
import static Util.Utils.afficherInformation;

public class Ingenieur extends Aventurier {
    private boolean assechementBonus;
    
    public Ingenieur(Coordonnees position) {
        super(position);
        setAssechementBonus(true);
    }
    
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Ingenieur;
    }

    @Override
    public void assecher(Coordonnees c, Grille grille) {
        if(assechementPossibleListe(grille).containsKey(c)){        //Si la tuile de coordonnées c est dans la liste assechementPossibleListe
            if(getAssechementBonus()||getActionsRestantes()>0){         //Si l'ingénieur a encore au moins une action ou utilise son assechement bonus
                grille.getTuile(c).assechement();                           //Il asseche la tuile
                if(getAssechementBonus()){                                  
                    setAssechementBonus(false);
                }else{
                    setActionsRestantes(getActionsRestantes()-1);           //On lui retire une action si il n'utilise pas son assechement bonus
                    setAssechementBonus(true);
                }
                       
            }else{                                                   //Sinon on l'informe qu'il n'a plus d'action
                System.out.println("Plus d'actions....");
                afficherInformation("Vous ne pouvez plus effectuer d'actions!");
            } 
        }else{                                                   //Sinon on l'informe qu'il ne peut pas assecher la tuile
            System.out.println("Assechement non possible ici!");
            afficherInformation("Vous ne pouvez pas assécher cette case!");
        }
    }
    
    

    @Override
    public void donnerCarte(Aventurier aven2, CarteTrésor carte) {
        //Reset du pouvoir
        setAssechementBonus(false);
        super.donnerCarte(aven2, carte);
    }

    @Override
    public void deplacement(Coordonnees c, Grille grille) {
        //Reset du pouvoir
        setAssechementBonus(false);
        super.deplacement(c, grille);
    }

    @Override
    public boolean prendreTresor(NomTrésor tresor) {
        //Reset du pouvoir
        setAssechementBonus(false);
        return super.prendreTresor(tresor);
    }
    
    public void setAssechementBonus(boolean b){
        assechementBonus = b;
    }

    public boolean getAssechementBonus() {
        return assechementBonus;
    }
    
}
