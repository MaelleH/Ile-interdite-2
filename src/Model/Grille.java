package Model;

import Util.Utils;
import java.util.*;

public class Grille {

        private HashMap<Coordonnees,Tuile> HSTuile;
        private ArrayList<Tuile> tuiles = new ArrayList<>();

    public Grille() {
        HSTuile = new HashMap<>();
        creerGrilleProf();
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
        Tuile tuilepda = new Tuile(NomTuile.LePontdesAbimes);
        Tuile tuilepdb = new Tuile(NomTuile.LaPortedeBronze);
        Tuile tuilecdo = new Tuile(NomTuile.LaCavernedesOmbres,"cristal");
        Tuile tuilepdf = new Tuile(NomTuile.LaPortedeFer);
        Tuile tuilepdo = new Tuile(NomTuile.LaPortedOr);
        Tuile tuilefdo = new Tuile(NomTuile.LesFalaisesdelOubli);
        Tuile tuilepdc = new Tuile(NomTuile.LePalaisdeCorail,"calice");
        Tuile tuilepa = new Tuile(NomTuile.LaPortedArgent);
        Tuile tuilecdi = new Tuile(NomTuile.LesDunesdelIllusion);
        Tuile tuileh = new Tuile(NomTuile.Heliport);
        Tuile tuilpdc = new Tuile(NomTuile.LaPortedeCuivre);
        Tuile tuilejh = new Tuile(NomTuile.LeJardindesHurlements,"zéphyr");
        Tuile tuilefp = new Tuile(NomTuile.LaForetPourpre);
        Tuile tuilelp = new Tuile(NomTuile.LeLagonPerdu);
        Tuile tuilemb = new Tuile(NomTuile.LeMaraisBrumeux);
        Tuile tuileo = new Tuile(NomTuile.Observatoire);
        Tuile tuilerf = new Tuile(NomTuile.LeRocherFantome);
        Tuile tuilecb = new Tuile(NomTuile.LaCaverneduBrasier,"cristal");
        Tuile tuilets = new Tuile(NomTuile.LeTempleduSoleil,"pierre");
        Tuile tuiletl = new Tuile(NomTuile.LeTempledeLaLune,"pierre");
        Tuile tuilepm = new Tuile(NomTuile.LePalaisdesMarees,"calice");
        Tuile tuilevc = new Tuile(NomTuile.LeValduCrepuscule);
        Tuile tuiletg = new Tuile(NomTuile.LaTourduGuet);
        Tuile tuilejm = new Tuile(NomTuile.LeJardindesMurmures,"zéphyr");
    
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
    
     

    
    public Coordonnees getCoordTuile(String nomTuile){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getValue().getNomT().toString().equals(nomTuile)){
                return e.getKey();
            }
        }
        return null;
    }
    
    public Tuile getTuile(String nomTuile){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getValue().getNomT().toString().equals(nomTuile)){
                return e.getValue();
            }
        }
        return null;
    }
    
    public Tuile getTuile(Coordonnees coord){
        for (Map.Entry<Coordonnees, Tuile> e : HSTuile.entrySet()) {
            if(e.getValue()!=null && e.getKey().equals(coord)){
                return e.getValue();
            }
        }
        return null;
    }
    
    
       

    
    
    public void creerGrilleProf(){
        creertuiles();
        int n=0;
        for (int i=1;i<=6;i++){
            for (int o=1;o<=6;o++){
                if (  (i==1 && (o<3||o>4)) || (i==2 && (o==1||o==6)) || (i==5 && (o==1||o==6))  || (i==6 && (o<3||o>4))  ){
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)), null);
                }
                else{
                    HSTuile.put(new Coordonnees(Integer.toString(i),Integer.toString(o)),tuiles.get(n));
                    n=n+1;
                }
            }
        }
        
        getTuile("Le Lagon Perdu").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Observatoire").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Le Jardin des Murmures").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("La Caverne du Brasier").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("La Porte de Bronze").setEtat(Utils.EtatTuile.INONDEE);
        getTuile("Le Marais Brumeux").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Le Rocher Fantome").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Les Dunes de l’Illusion").setEtat(Utils.EtatTuile.COULEE);
        getTuile("Le Temple de La Lune").setEtat(Utils.EtatTuile.COULEE);
    }
    
}
