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

    //permet au plongeur de traverser les tuiles innondées et coulées pour une action
    @Override
    public HashMap deplacementPossibleListe(Grille grille) {
            HashMap<Coordonnees,Tuile> listeATraiter = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeATraiterTemp = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeTraitees = new HashMap<>();
            HashMap<Coordonnees,Tuile> listeD = new HashMap<>();

            //On ajoute la position du joueur à traiter
            listeATraiter.put(getPosition(),grille.getTuile(getPosition()));
            String a,b;
            a=this.getPosition().getX();    //coordonnées de l'aventurier
            b=this.getPosition().getY();
            String x,y;
            Coordonnees coordBis;
            Tuile tuileBis;
            //On cherche les tuiles vers lesquelles il peut se déplacer tant qu'on trouve des extensions aux chemins
            while(!listeATraiter.isEmpty()){
                //On va chercher autour des tuiles à traiter les tuiles vers lesquels il peut se déplacer et celles qui peuvent allonger le chemin
                listeATraiterTemp = new HashMap<>();
                for(Coordonnees coord : listeATraiter.keySet()){
                    for(int i = 0;i<4;i++){
                        if(i==0){
                            //Cas de la tuile au dessus
                            x = Integer.toString(Integer.parseInt(coord.getX()));
                            y = Integer.toString(Integer.parseInt(coord.getY())-1);
                        }else if(i==1){
                            //Cas de la tuile en dessous
                            x = Integer.toString(Integer.parseInt(coord.getX()));
                            y = Integer.toString(Integer.parseInt(coord.getY())+1);
                        }else if(i==2){
                              //Cas de la tuile à droite
                            x = Integer.toString(Integer.parseInt(coord.getX())+1);
                            y = Integer.toString(Integer.parseInt(coord.getY()));
                        }else{
                            //Cas de la tuile à gauche
                            x = Integer.toString(Integer.parseInt(coord.getX())-1);
                            y = Integer.toString(Integer.parseInt(coord.getY()));
                        }
                        coordBis = new Coordonnees(x, y);
                        tuileBis = grille.getTuile(coordBis);
                        if(tuileBis!=null){
                            if (! (a.contentEquals(x) && b.contentEquals(y))){          //Si la tuile n'est pas la tuile de l'avneturier
                                if(tuileBis.getEtat().equals(EtatTuile.ASSECHEE)){          //si la tuile est assehcee
                                    listeD.put(coordBis, tuileBis);                             //on l'ajoute dans la liste des tuiles possibles
                                }else if(tuileBis.getEtat().equals(EtatTuile.INONDEE)){     //si la tuile est innondée
                                    if(!listeTraitees.containsKey(coordBis)){                   //si elle n'est pas dans la liste traitée
                                        listeD.put(coordBis, tuileBis);                             //on l'ajoute dans la liste des tuiles possibles
                                        listeATraiterTemp.put(coordBis, tuileBis);                  //on l'ajoute dans la liste a traiter temporaire
                                    }                                
                                }else if(tuileBis.getEtat().equals(EtatTuile.COULEE)){      //si la tuile est coulée
                                    if(!listeTraitees.containsKey(coordBis)){                   //si la tuile n'est pas dans la liste traitée
                                        listeATraiterTemp.put(coordBis, tuileBis);                  //on l'ajoute dans la liste a traiter temporaire
                                    }
                                }
                            }
                        }
                    }
                    listeTraitees.put(coord,grille.getTuile(coord));                   //on l'ajoute dans la liste traitée
                }
                listeATraiter.clear();                                                 //on vide la liste a traiter
                listeATraiter = listeATraiterTemp;                                     //la liste a traiter temporaire devient la liste a traiter
            }
        return listeD;
    }
        
}
        
