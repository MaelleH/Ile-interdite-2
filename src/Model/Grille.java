package Model;

import Util.Utils;
import java.util.*;

public class Grille {

        private HashMap<Coordonnees,Tuile> HSTuile;
        private ArrayList<Tuile> tuiles = new ArrayList<>();

    public Grille() {
        HSTuile = new HashMap<>();
        creerGrille();
                
    }
        
    
        
    /**
     * @return the HSTuile
     */
    public HashMap<Coordonnees,Tuile> getHSTuile() {
        return HSTuile;
    }

    /**
     * @param HSTuile the HSTuile to set
     */
    public void setHSTuile(HashMap<Coordonnees,Tuile> HSTuile) {
        this.HSTuile = HSTuile;
    }
    public void creertuiles(){
    Tuile tuilepda = new Tuile(NomTuile.LePontdesAbimes,Utils.EtatTuile.ASSECHEE);
    Tuile tuilepdb = new Tuile(NomTuile.LaPortedeBronze,Utils.EtatTuile.ASSECHEE);
    Tuile tuilecdo = new Tuile(NomTuile.LaCavernedesOmbres,"cristal",Utils.EtatTuile.ASSECHEE);
    Tuile tuilepdf = new Tuile(NomTuile.LaPortedeFer,Utils.EtatTuile.ASSECHEE);
    Tuile tuilepdo = new Tuile(NomTuile.LaPortedOr,Utils.EtatTuile.ASSECHEE);
    Tuile tuilefdo = new Tuile(NomTuile.LesFalaisesdelOubli,Utils.EtatTuile.ASSECHEE);
    Tuile tuilepdc = new Tuile(NomTuile.LePalaisdeCorail,"calice",Utils.EtatTuile.ASSECHEE);
    Tuile tuilepa = new Tuile(NomTuile.LaPortedArgent,Utils.EtatTuile.ASSECHEE);
    Tuile tuilecdi = new Tuile(NomTuile.LesDunesdelIllusion,Utils.EtatTuile.ASSECHEE);
    Tuile tuileh = new Tuile(NomTuile.Heliport,Utils.EtatTuile.ASSECHEE);
    Tuile tuilpdc = new Tuile(NomTuile.LaPortedeCuivre,Utils.EtatTuile.ASSECHEE);
    Tuile tuilejh = new Tuile(NomTuile.LeJardindesHurlements,"zéphyr",Utils.EtatTuile.ASSECHEE);
    Tuile tuilefp = new Tuile(NomTuile.LaForetPourpre,Utils.EtatTuile.ASSECHEE);
    Tuile tuilelp = new Tuile(NomTuile.LeLagonPerdu,Utils.EtatTuile.ASSECHEE);
    Tuile tuilemb = new Tuile(NomTuile.LeMaraisBrumeux,Utils.EtatTuile.ASSECHEE);
    Tuile tuileo = new Tuile(NomTuile.Observatoire,Utils.EtatTuile.ASSECHEE);
    Tuile tuilerf = new Tuile(NomTuile.LeRocherFantome,Utils.EtatTuile.ASSECHEE);
    Tuile tuilecb = new Tuile(NomTuile.LaCaverneduBrasier,"cristal",Utils.EtatTuile.ASSECHEE);
    Tuile tuilets = new Tuile(NomTuile.LeTempleduSoleil,"pierre",Utils.EtatTuile.ASSECHEE);
    Tuile tuiletl = new Tuile(NomTuile.LeTempledeLaLune,"pierre",Utils.EtatTuile.ASSECHEE);
    Tuile tuilepm = new Tuile(NomTuile.LePalaisdesMarees,"calice",Utils.EtatTuile.ASSECHEE);
    Tuile tuilevc = new Tuile(NomTuile.LeValduCrepuscule,Utils.EtatTuile.ASSECHEE);
    Tuile tuiletg = new Tuile(NomTuile.LaTourduGuet,Utils.EtatTuile.ASSECHEE);
    Tuile tuilejm = new Tuile(NomTuile.LeJardindesMurmures,"zéphyr",Utils.EtatTuile.ASSECHEE);
    
        
    tuiles.add(tuilpdc);
    tuiles.add(tuilepda);
    tuiles.add(tuilepdb);
    tuiles.add(tuilecdo);
    tuiles.add(tuilepdf);
    tuiles.add(tuilepdo);
    tuiles.add(tuilefdo);
    tuiles.add(tuilepdc);
    tuiles.add(tuilepa);
    tuiles.add(tuilecdi);
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
    tuiles.add(tuileh);
    tuiles.add(tuilepdc);
    }
    
    
    
    public void creerGrille(){
        creertuiles();

        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
                 int max =tuiles.size();

                if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                    HSTuile.put((new Coordonnees(Integer.toString(i),Integer.toString(o))), null);
                    

                }
                else{
                    int rand = (int) (Math.random()*max);
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), (tuiles.get(rand)));
                    System.out.println(Integer.toString(i)+Integer.toString(o)+tuiles.get(rand).getNomT());
                    tuiles.remove(rand);
                }

            }
        }      
    }
    
    public HashMap creerGrille2(){
        creertuiles();
        HashMap<Coordonnees,Tuile> HSTuil= new HashMap<>();

        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
                 int max =tuiles.size();

                if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                    HSTuile.put((new Coordonnees(Integer.toString(i),Integer.toString(o))), null);
                    HSTuil.put((new Coordonnees(Integer.toString(i),Integer.toString(o))),null);

                }
                else{
                    int rand = (int) (Math.random()*max);
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), (tuiles.get(rand)));
                    System.out.println(Integer.toString(i)+Integer.toString(o)+tuiles.get(rand).getNomT());
                    tuiles.remove(rand);
                }

            }
        }      
        return this.HSTuile;
    }    

    
    
    public void creerGrilleProf(){
        creertuiles();
        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
             int max =tuiles.size();
            
            if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), null);
            }
            else{
                int n=1;
                HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)),tuiles.get(n));
                n=n+1;
            }
                
        }
    } 
            }
    
}