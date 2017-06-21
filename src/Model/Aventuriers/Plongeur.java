package Model.Aventuriers;

import Util.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Util.Utils;
import Util.Utils.EtatTuile;
import java.util.HashMap;

public class Plongeur extends Aventurier {
    public Plongeur(Coordonnees position) {
        super(position);
    }

    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Plongeur;
    }

    //permet au plongeur de traverser les cases innondées et coulées pour 1 action
    @Override
    public HashMap deplacementPossibleListe(Grille grille) {
            HashMap<Coordonnees,Tuile> listeATraiter = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeATraiterTemp = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeTraitees = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeD = new HashMap<>();

            //On ajoute la position du joueur à traiter
            listeATraiter.put(getPosition(),grille.getTuile(getPosition()));

            String x,y;
            Coordonnees coordBis;
            Tuile tuileBis;
            //On cherche les cases vers lesquelles il peut se déplacer tant qu'on trouve des extensions aux chemins
            while(!listeATraiter.isEmpty()){
                //On va chercher autour des cartes à traiter les cartes vers lesquels il peut se déplacer et celles qui peuvent allonger le chemin
                listeATraiterTemp = new HashMap<>();
                for(Coordonnees coord : listeATraiter.keySet()){
                    for(int i = 0;i<4;i++){
                        if(i==0){
                            //Cas de la case au dessus
                            x = Integer.toString(Integer.parseInt(coord.getX()));
                            y = Integer.toString(Integer.parseInt(coord.getY())-1);
                        }else if(i==1){
                            //Cas de la case en dessous
                            x = Integer.toString(Integer.parseInt(coord.getX()));
                            y = Integer.toString(Integer.parseInt(coord.getY())+1);
                        }else if(i==2){
                              //Cas de la case à droite
                            x = Integer.toString(Integer.parseInt(coord.getX())+1);
                            y = Integer.toString(Integer.parseInt(coord.getY()));
                        }else{
                            //Cas de la case à gauche
                            x = Integer.toString(Integer.parseInt(coord.getX())-1);
                            y = Integer.toString(Integer.parseInt(coord.getY()));
                        }

                        coordBis = new Coordonnees(x, y);
                        tuileBis = grille.getTuile(coordBis);
                        if(tuileBis!=null){
                            if(tuileBis.getEtat().equals(EtatTuile.ASSECHEE)){
                                listeD.put(coordBis, tuileBis);
                            }else if(tuileBis.getEtat().equals(EtatTuile.INONDEE)){
                                if(!listeTraitees.containsKey(coordBis)){
                                    listeD.put(coordBis, tuileBis);
                                    listeATraiterTemp.put(coordBis, tuileBis);
                                }                                
                            }else if(tuileBis.getEtat().equals(EtatTuile.COULEE)){
                                if(!listeTraitees.containsKey(coordBis)){
                                    listeATraiterTemp.put(coordBis, tuileBis);
                                }
                            } 
                        }
                    }
                    listeTraitees.put(coord,grille.getTuile(coord));
                }
                listeATraiter.clear();
                listeATraiter = listeATraiterTemp;
            }
        return listeD;
    }
        
}
        
