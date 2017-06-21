package Model;

import Util.Coordonnees;
import Util.NomTuile;
import static Util.NomTrésor.Calice;
import static Util.NomTrésor.Cristal;
import static Util.NomTrésor.Pierre;
import static Util.NomTrésor.Zéphyr;
import Util.Utils;
import java.util.*;

public class Grille {
    private HashMap<Coordonnees,Tuile> HSTuile;

    public Grille() {
        HSTuile = new HashMap<>();
        creerGrille();              //creerGrille() ou CreerGrilleProf()
    }
        
    public HashMap<Coordonnees,Tuile> getHSTuile() {
        return HSTuile;
    }

    public void setHSTuile(HashMap<Coordonnees,Tuile> HSTuile) {
        this.HSTuile = HSTuile;
    }
    
    //creation des tuilles du plateau, dans l'ordre de la GrilleProf
    public ArrayList<Tuile> creertuiles(){
        ArrayList<Tuile> tuiles = new ArrayList<>();
        
        Tuile tuilepda = new Tuile(NomTuile.LePontdesAbimes);
        Tuile tuilepdb = new Tuile(NomTuile.LaPortedeBronze);
        Tuile tuilecdo = new Tuile(NomTuile.LaCavernedesOmbres,Cristal);        //On ajoute les trsors sur les tuiles correspondantes
        Tuile tuilepdf = new Tuile(NomTuile.LaPortedeFer);
        Tuile tuilepdo = new Tuile(NomTuile.LaPortedOr);
        Tuile tuilefdo = new Tuile(NomTuile.LesFalaisesdelOubli);
        Tuile tuilepdc = new Tuile(NomTuile.LePalaisdeCorail,Calice);
        Tuile tuilepa = new Tuile(NomTuile.LaPortedArgent);
        Tuile tuilecdi = new Tuile(NomTuile.LesDunesdelIllusion);
        Tuile tuileh = new Tuile(NomTuile.Heliport);
        Tuile tuilpdc = new Tuile(NomTuile.LaPortedeCuivre);
        Tuile tuilejh = new Tuile(NomTuile.LeJardindesHurlements,Zéphyr);
        Tuile tuilefp = new Tuile(NomTuile.LaForetPourpre);
        Tuile tuilelp = new Tuile(NomTuile.LeLagonPerdu);
        Tuile tuilemb = new Tuile(NomTuile.LeMaraisBrumeux);
        Tuile tuileo = new Tuile(NomTuile.Observatoire);
        Tuile tuilerf = new Tuile(NomTuile.LeRocherFantome);
        Tuile tuilecb = new Tuile(NomTuile.LaCaverneduBrasier,Cristal);
        Tuile tuilets = new Tuile(NomTuile.LeTempleduSoleil,Pierre);
        Tuile tuiletl = new Tuile(NomTuile.LeTempledeLaLune,Pierre);
        Tuile tuilepm = new Tuile(NomTuile.LePalaisdesMarees,Calice);
        Tuile tuilevc = new Tuile(NomTuile.LeValduCrepuscule);
        Tuile tuiletg = new Tuile(NomTuile.LaTourduGuet);
        Tuile tuilejm = new Tuile(NomTuile.LeJardindesMurmures,Zéphyr);
    
        tuiles.add(tuilepda);
        tuiles.add(tuilepdb);
        tuiles.add(tuilecdo);
        tuiles.add(tuilepdf);
        tuiles.add(tuilepdo);
        tuiles.add(tuilefdo);
        tuiles.add(tuilepdc);
        tuiles.add(tuilepa);
        tuiles.add(tuilecdi);
        tuiles.add(tuileh);
        tuiles.add(tuilpdc);
        tuiles.add(tuilejh);
        tuiles.add(tuilefp);
        tuiles.add(tuilelp);
        tuiles.add(tuilemb);
        tuiles.add(tuileo);
        tuiles.add(tuilerf);
        tuiles.add(tuilecb);
        tuiles.add(tuilets);
        tuiles.add(tuiletl);
        tuiles.add(tuilepm);
        tuiles.add(tuilevc);
        tuiles.add(tuiletg);
        tuiles.add(tuilejm);
    
        return tuiles;
    }
    //creation d'une grille aléatoirement
    public void creerGrille(){
        ArrayList<Tuile> tuiles = creertuiles();

        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
                int max =tuiles.size();
                //si on est dans un des coins de la grille, la case est de type null
                if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                    HSTuile.put((new Coordonnees(Integer.toString(i),Integer.toString(o))), null);   
                }else{
                    //On prend un nombre aléatoire compris entre 0 et l'index max de la liste
                    int rand = (int) (Math.random()*max);
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), (tuiles.get(rand))); //On ajoute cette tuile à la grille
                    tuiles.remove(rand);    //On enleve cette tuile de la liste
                }
            }
        }      
    }
    
    //créer la grille donner dans le sujet
    public void creerGrilleProf(){
        ArrayList<Tuile> tuiles = creertuiles();
        
        int n=0;
        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
                if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), null);
                }
                else{
                    //On place les tuiles dans l'ordre
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)),tuiles.get(n));
                    n=n+1;
                }
            }
        }
        //Mise a jour des etats des tuiles
        getTuile("Le Lagon Perdu").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Observatoire").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Le Jardin des Murmures").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("La Caverne du Brasier").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("La Porte de Bronze").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Le Jardin des Hurlements").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Le Marais Brumeux").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Le Rocher Fantome").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Les Dunes de l’Illusion").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Le Temple de La Lune").setEtat(Utils.EtatTuile.COULEE);
    }
    
    //donne les coordonnées d'une tuile
    public Coordonnees getCoordTuile(String nomTuile){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getValue().getNomT().toString().equals(nomTuile)){
                return e.getKey();
            }
        }
        return null;
    }
    
    //donne une Tuile en fonction d'un nom donné
    public Tuile getTuile(String nomTuile){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getValue().getNomT().toString().equals(nomTuile)){
                return e.getValue();
            }
        }
        return null;
    }
    
    //donne une tuile en fonction des coordonnées données
    public Tuile getTuile(Coordonnees coord){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getKey().equals(coord)){
                return e.getValue();
            }
        }
        return null;
    }
    

}
