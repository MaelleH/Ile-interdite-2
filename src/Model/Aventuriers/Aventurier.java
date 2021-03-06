package Model.Aventuriers;

import Model.cartesTresor.CarteTrésor;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Util.NomTrésor;
import Util.TypeCarteTresor;
import Util.Utils;
import Util.Utils.NomAventurier;
import static Util.Utils.afficherInformation;
import java.util.*;
import java.util.HashMap;

public class Aventurier {
    private ArrayList<CarteTrésor> mainCarteTrésor = new ArrayList<>();
    private int actionsRestantes;
    private Coordonnees position;
        
    public Aventurier(){
        this.actionsRestantes=getMaxActions();
    }
    public Aventurier(Coordonnees position) {
        this.position=position;
        this.actionsRestantes=getMaxActions();
    }
    
    public Coordonnees getPosition() {
            return this.position;
    }

    public void piocherCT(ArrayList<CarteTrésor> piocheCarteTrésor){
        getMainCarteTrésor().add(piocheCarteTrésor.get(0));
        piocheCarteTrésor.remove(0);

        if(doitDefausser()){
            //TODO
            //PENSER A METTRE A JOUR LA DEFAUSSE

        }
    }

    public boolean doitDefausser() {
        return (getMainCarteTrésor().size())>5;
    }

    //déplace un aventurier
    public void deplacement(Coordonnees c,Grille grille ) {
        if(this.getActionsRestantes() > 0 && deplacementPossibleListe(grille).containsKey(c)){
            setPosition(c);
            setActionsRestantes(getActionsRestantes()-1);
        }else if(this.getActionsRestantes()<1){                                        //Si il n'a plus d'action
            afficherInformation("Vous ne pouvez plus effectuer d'actions!");            //On affiche cette information
        }else{                                                                          //Sinon
            afficherInformation("Vous ne pouvez pas vous déplacer vers cette case!");   //On affiche le fait qu'il ne puisse pas se déplacer vers la tuile
        }
    }


    //donne les cases sur lesquelles un jouer peut se déplacer
    public HashMap deplacementPossibleListe(Grille grille) {
        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();
        int xo,yo,xn,yn;

        xo=Integer.parseInt(getPosition().getX());                              //Coordonnées du joueur
        yo=Integer.parseInt(getPosition().getY());

        for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){    //Pour chaque tuile de la grille
            if(i.getValue()!=null){                                             //Si la tuile n'est pas une tuile située dans les coins
                xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());  //On note ses coordonnées
                yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
                //Si elle est adjacente à la tuile de l'aventurier et elle n'est pas coulée
                if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((! grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.COULEE)))){
                    listeD.put((Coordonnees) i.getKey(), i.getValue());         //On l'ajoute dans la liste de déplacements possibles
                }    
            }
        }
        return listeD;
    }

    //donne les cases asséchables pour un joueur, meme principe que deplacementPossibleListe
    public HashMap assechementPossibleListe(Grille grille) {
        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
        int xo,yo,xn,yn;

        xo=Integer.parseInt(getPosition().getX());
        yo=Integer.parseInt(getPosition().getY());

        for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
            if(i.getValue()!=null){
                xn=Integer.parseInt((String)(i.getKey()).getX());
                yn=Integer.parseInt((String)(i.getKey()).getY());
                if(((xo==xn && yo==yn)||(((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.INONDEE)))){
                    listeD.put( i.getKey(), i.getValue());   
                }     
            }
        }
        return listeD;
    }
    //assèche une case
    public void assecher(Coordonnees c,Grille grille) {  
        if(assechementPossibleListe(grille).containsKey(c)){        //Si la tuile de coordonnée c est dans la liste assechementPossibleListe
            if(getActionsRestantes()>0){                            //Si l'aventurier a encore au moins une action
                grille.getTuile(c).assechement();                   //Il asseche la tuile
                setActionsRestantes(getActionsRestantes()-1);       //On lui retire une acion
            }else{                                                   //Sinon on l'informe qu'il n'a plus d'action
                afficherInformation("Vous ne pouvez plus effectuer d'actions!");
            } 
        }else{                                                   //Sinon on l'informe qu'il ne peut pas assecher la tuile
            afficherInformation("Vous ne pouvez pas assécher cette case!");
        }
    }

                                                 
    /**
     * @return the nom
     */
    public NomAventurier getNom() {
        return NomAventurier.Aventurier;
    }

    /**
     * @return the mainCarteTrésor
     */
    public ArrayList<CarteTrésor> getMainCarteTrésor() {
        return mainCarteTrésor;
    }

    /**
     * @param mainCarteTrésor the mainCarteTrésor to set
     */
    public void setMainCarteTrésor(ArrayList<CarteTrésor> mainCarteTrésor) {
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

    public void resetActionsRestantes() {
        this.actionsRestantes = getMaxActions();
    }
    /**
     * @param position the position to set
     */
    public void setPosition(Coordonnees position) {
        this.position = position;
    }

    
    //vérifie si le joueur peut prendre le trésor
    public boolean priseTresorPossible(NomTrésor tresor) {
                int nbCartes=0;
                CarteTrésorTrophée iBis;
                
                if ((getMainCarteTrésor().size()>4)&&(tresor!=null)){ 
                    for(CarteTrésor i :getMainCarteTrésor()){               
                        if((i.getTypeCarteTresor()).equals(TypeCarteTresor.Tresor)){    
                            iBis = (CarteTrésorTrophée) i;
                            if(iBis.getNomT().equals(tresor)){
                               nbCartes = nbCartes +1;  
                            }
                        }
                    }
                    if(nbCartes>=1){
                        return true;
                    }
                }
                return false;                
	}
    //le joueur prend le trésor donné
    public boolean prendreTresor(NomTrésor tresor) {
        if(priseTresorPossible(tresor)){                //Si il peut prendre le tresor
            getMainCarteTrésor().remove(tresor);        //One enleve les cartes trésor correspondantes de sa main
            return true;
        }else{
            return false;
        }
    }
        
    //le joueur donne une carte à un aventurier donné	
    public boolean donnerCartePossible(Aventurier aven2) {
        return getPosition().equals(aven2.getPosition());
    }
    
    public int getMaxActions(){
        return 3;
    }
}
 
