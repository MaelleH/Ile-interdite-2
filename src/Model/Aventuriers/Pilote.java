 package Model.Aventuriers;

import Util.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Util.Utils;
import static Util.Utils.afficherInformation;
import java.util.HashMap;
import java.util.Map;

public class Pilote extends Aventurier {
        
    private boolean pouvoirUtilise;

    public Pilote(Coordonnees position) {
        super(position);
    }

    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Pilote;
    }

    //Liste des tuiles non adjacentes non coulées
    public HashMap autreActionListe(Grille grille) {
        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
        int xo,yo,xn,yn;
        
        xo=Integer.parseInt(getPosition().getX());              //coordonnées du joueur
        yo=Integer.parseInt(getPosition().getY());
        
        if(pouvoirUtilise==false){                                                                                                      //si il peut utiliser son pouvoir
            for (Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){                                                           //Pour chaque tuile de la grille
                xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());                                                                  //Coordonnées de la tuile
                yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
                if (i.getValue()!=null){                                                                                                        //Si la tuile existe,
                    if (i.getValue().getEtat()!=Utils.EtatTuile.COULEE  ){                                                                          //qu'elle n'est pas coulée
                        if (  (xn==xo && (yn==yo-1 || yn==yo+1))  ||  (yn==yo && (xn==xo-1 || xn==xo+1))  ||  (xn==xo && yn==yo)  ){
                        }else{                                                                                                                  //et qu'elle n'est pas adjacente
                            listeD.put((Coordonnees) i.getKey(), i.getValue());                                                                     //elle est ajoutée à la liste des tuiles possibles avec le pouvoir
                        }
                    }
                }
            }
        }
        return listeD;
    }

    @Override
    public void deplacement(Coordonnees c,Grille grille ) {
        if (this.getActionsRestantes() > 0){                                                //Si l'aventurier a encore au moins une action
            if (pouvoirUtilise==true){                                                          //si le pouvoir a été utilisé
                if (this.getActionsRestantes() > 0 && (deplacementPossibleListe(grille).containsKey(c)) ){  //et que la tuile choisie est dans la liste des tuiles adjacentes
                    setPosition(c);                                                                 //l'aventurier se déplace
                }
            }else if (this.getActionsRestantes() > 0 && ((deplacementPossibleListe(grille).containsKey(c)) || autreActionListe(grille).containsKey(c))){    //si le pouvoir est disponible et que la tuile est non coulée
                    if (autreActionListe(grille).containsKey(c)){                               //si la tuile est dans la liste des tuiles non adjacentes
                        System.out.println("Pouvoir utilisé pour ce deplacement ");
                        pouvoirUtilise=true;}                                                       //le pouvoir est utilisé pour effectuer le déplacement
                        setPosition(c);                                                             //l'aventurier se déplace
            }
            setActionsRestantes(getActionsRestantes()-1);                                       //on lui enleve une action
        }else if(this.getActionsRestantes()<1){                                             //Si il n'a plus d'action
            System.out.println("Plus d'actions....");
            afficherInformation("Vous ne pouvez plus effectuer d'actions!");                    //On affiche cette information
        }else{                                                                              //Sinon (encore au moins une action mais la tuile n'est dans aucune des listes)
            System.out.println("Déplacement impossible!");
            afficherInformation("Vous ne pouvez pas vous déplacer vers cette case!");           //On affiche le fait qu'il ne puisse pas se déplacer vers la tuile
        }
    }

    @Override
    public void resetActionsRestantes() {
        setMaxActions();                                    //On réinitialise ses actions
        pouvoirUtilise=false;                               //On réinitialise le pouvoir du pilote
        System.out.println("Pouvoir du pilote reinitialsé");
    }

    @Override
    public HashMap deplacementPossibleListe(Grille grille){

        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();        //liste des cases adjacentes non coulées
        HashMap<Coordonnees,Tuile> listeAutre = new HashMap<>();    //liste des cases non adjacentes non coulées
        int xo,yo,xn,yn;

        xo=Integer.parseInt(getPosition().getX());                  //Coordonnées de l'aventurier
        yo=Integer.parseInt(getPosition().getY());
        for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){        //Pour chaque tuile de la grille
            xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());          //Coordonnées de la tuile
            yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());
            if(i.getValue()!=null){         //Si la tuile est adjacente à la tuile de l'aventurier et non coulée
                if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((! grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.COULEE)))){
                    listeD.put((Coordonnees) i.getKey(), i.getValue());             //On ajoute cette tuile dans la liste des tuiles possibles
                }
            }
        }
        
        return listeD;
    }

}
