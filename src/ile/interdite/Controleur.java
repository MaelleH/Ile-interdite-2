package ile.interdite;

import java.util.*;

import Model.CarteInondation;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Model.CarteTrésor;
import Model.Aventuriers.Aventurier;
import Model.Aventuriers.Explorateur;
import Model.Aventuriers.Ingenieur;
import Model.Aventuriers.Messager;
import Model.Aventuriers.Navigateur;
import Model.Aventuriers.Pilote;
import Model.Aventuriers.Plongeur;
import Model.EchelleNiveauEau;
import Model.Helico;
import Model.NomTuile;
import Model.MonteeDesEaux;
import Model.Sac;
import Model.CarteTrésorTrophée;
import Model.TypeTrésor;
import Util.Utils;
import Util.Utils.EtatTuile;
import Util.Utils.Pion;
import Vue.VueAventurier;
import Vue.VuePlateau;

public class Controleur implements Observateur {

	//Collection<CarteTrésor> piocheCarteTrésor;
	private Grille grille;
        private ArrayList<Aventurier> aventuriers;
        private ArrayList<VueAventurier> vuesAventuriers;
        
        private EchelleNiveauEau niveauEau;
        private VuePlateau vuePlateau;
        private ArrayList<CarteTrésor> piocheCarteTrésor;
	private ArrayList<CarteTrésor> defausseCarteTrésor;
	private ArrayList<CarteInondation> piocheCarteInondation;
	private ArrayList<CarteInondation> défausseCarteInondation;
	private ArrayList<CarteInondation> défausseCarteCoulées;
        private ArrayList<TypeTrésor> tresors;
        
        public Controleur() {
            initPartie();
            lancerTour();
        }
        
        public void initPartie(){
            //Créer la grille
            grille = new Grille();
            
            //Créer les Aventuriers
            creationAventurier(4);
            vuePlateau = new VuePlateau();
            
            //Creér les vues de Aventuriers
            vuesAventuriers = new ArrayList<>();
            
            int i = 1;
            VueAventurier vueAventurier;
            for(Aventurier a : aventuriers){
                vueAventurier = new VueAventurier ("Joueur "+i,a.getNom(),getPionAventurier(a).getCouleur(),this);
                vueAventurier.setVisible(false);
                vuesAventuriers.add(vueAventurier);
                i++;
            }
            //Créer les cartes
            initCartetresor();
            
            
            //Mise à jour du plateau
            updateVuePlateau();
            
            
        }
        
        public void lancerTour(){
            getVueAventurier(aventuriers.get(0)).setVisible(true);
        }
        
        public void finTour(){
            getVueAventurier(aventuriers.get(0)).setVisible(false);
            aventuriers.get(0).resetActionsRestantes();
            aventuriers.get(0).piocherCT(piocheCarteTrésor);
            aventuriers.get(0).resetAutreA();
            
            setJoueurSuivant();
        }

        public void setJoueurSuivant(){
            Aventurier avenTmp = aventuriers.get(0);
            aventuriers.remove(0);
            aventuriers.add(avenTmp);
        }
        
	public VueAventurier getVueAventurier(Aventurier a){
            for(VueAventurier vA : vuesAventuriers){
                if(vA.getNomAventurier().equals(a.getNom())){
                    return vA;
                }
            }
            return null;
        }
        

        

        public void initCartetresor(){
            piocheCarteTrésor = new ArrayList<>();
            for(int i=0;i<5;i++){
                piocheCarteTrésor.add(new CarteTrésorTrophée(TypeTrésor.Calice));
            }
            for(int i=0;i<5;i++){
                piocheCarteTrésor.add(new CarteTrésorTrophée(TypeTrésor.Pierre));
            }
            for(int i=0;i<5;i++){
                piocheCarteTrésor.add(new CarteTrésorTrophée(TypeTrésor.Zéphyr));
            }
            for(int i=0;i<5;i++){
                piocheCarteTrésor.add(new CarteTrésorTrophée(TypeTrésor.Cristal));
            }
            for(int i=0;i<3;i++){
                piocheCarteTrésor.add(new Helico());
            }
            for(int i=0;i<2;i++){
                piocheCarteTrésor.add(new Sac());
            }
            for(int i=0;i<3;i++){
                piocheCarteTrésor.add(new MonteeDesEaux());
            }
            
            piocheCarteTrésor= Utils.melangerCT(piocheCarteTrésor);
        }
        
        
	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public void donnerCarte(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Controleur.donnerCarte
		//TODO - TROUVER UN MOYEN DE RENTRER UNE DEUXIEME INOF A  PART SI LES BOUTONS SONT FAIT;
	}

	/**
	 * 
	 * @param aven1
	 * @param aven2
     * @return 
	 */
	public boolean donnationPossible(Aventurier aven1, Aventurier aven2) {
		return aven1.getPosition() == aven2.getPosition();
                
	}

	/**
	 * 
	 * @param a
	 */
	public void prendreTresor() {
		// TODO - implement Controleur.prendreTresor
                
		if(priseTresorPossible(aventuriers.get(0))){
                    TypeTrésor tresor =(grille.getHSTuile().get(aventuriers.get(0).getPosition()).getTresor());
                    aventuriers.get(0).getMainCarteTrésor().remove(tresor);
//PENSER A METTRE UN ATTRIBUT TRESOR QUELQUE PART

 
                }
	}

	/**
	 * 
	 * @param a
     * @return 
	 */
	public boolean priseTresorPossible(Aventurier a) {
		// TODO - implement Controleur.priseTresorPossible
                TypeTrésor tresor =(grille.getHSTuile().get(a.getPosition()).getTresor());
                int stop=0;
                
                if ((a.getMainCarteTrésor().size()>4)&&(tresor!=null)){
                    
                    for(CarteTrésor i :a.getMainCarteTrésor()){
                        if(!(i.getNomCT()).equals(tresor)){
                          stop = stop +1;  
                        }
                    }
                    if(stop<1){
                        return true;
                    }
                }
                return false;                
	}
        
    public boolean doitDefausser(Aventurier a) {
            // TODO - implement Controleur.priseTresorPossible
            return (a.getMainCarteTrésor().size()>5);
    }
    
        public void initPiocheInnondation(){
                for (NomTuile tuile : NomTuile.values()){
                    CarteInondation carte = new CarteInondation(tuile);
                    piocheCarteInondation.add(carte);
                }
        }

        public CarteInondation picoherInnondation(Aventurier a){
                return (piocheCarteInondation.get((0)));
        }

        
        
        
        
        
        
    @Override
    public void traiterMessage(Message m) {
        //A Faire 
        String x,y;

            switch (m.getTypeMessage()) {
                case ALLER:
                    if(m.getChampSaisieTxt().length() != 2){
                        Utils.afficherInformation("La position saisie ne respecte pas le format attendu!\n(saisir \"xy\"tel que x et y les coordonnées de la case )");
                    }else{


                        x=Character.toString(m.getChampSaisieTxt().charAt(0));
                        y=Character.toString(m.getChampSaisieTxt().charAt(1));

                        Coordonnees c = new Coordonnees(x,y);
                        System.out.println("Déplacement! (" + x +","+ y +")");
                        getAventurier(m.getJoueur()).deplacement(c,grille);

                    }

                    break;
                case ASSECHER:
                    if(m.getChampSaisieTxt().length() != 2){
                        Utils.afficherInformation("La position saisie ne respecte pas le format attendu!\n(saisir \"xy\"tel que x et y les coordonnées de la case )");
                    }else{


                        x=Character.toString(m.getChampSaisieTxt().charAt(0));
                        y=Character.toString(m.getChampSaisieTxt().charAt(1));

                        Coordonnees c = new Coordonnees(x,y);

                        System.out.println("Assècher! (" + x +","+ y +")");
                        getAventurier(m.getJoueur()).assecher(c,grille);
                        

                    }

                    break;
                case AUTREACTION:
                    if(!getAventurier(m.getJoueur()).isAutreA()){
                        if(m.getChampSaisieTxt().length() != 2){
                            Utils.afficherInformation("La position saisie ne respecte pas le format attendu!\n(saisir \"xy\"tel que x et y les coordonnées de la case )");
                        }else{
                         x=Character.toString(m.getChampSaisieTxt().charAt(0));
                         y=Character.toString(m.getChampSaisieTxt().charAt(1));

                         Coordonnees c = new Coordonnees(x,y);

                         System.out.println("Autre Action!");

                         getAventurier(m.getJoueur()).autreAction(c,grille);
                         getAventurier(m.getJoueur()).setAutreA(true);
                        } 
                    }
                    else{
                        Utils.afficherInformation("Super Action déja utilisée!");
                    }
                    
                    
                    break;
                case TERMINERTOUR:
                    System.out.println("Fin du Tour!");
                    finTour();
                    lancerTour();
                    break;
                default:
                    break;
            }
            updateVuePlateau();
    }
        
    public Aventurier getAventurier(String nom){
        for(Aventurier i : aventuriers){
            if(i.getNom().equals(nom)){
                return i;
            }
        }
        return null;
    }    
    
    
    public void creationAventurier(int nbjoueur){
        aventuriers = new ArrayList<>();

        Explorateur explo = new Explorateur(grille.getCoordTuile("La Porte de Bronze"));
        Ingenieur inge = new Ingenieur(grille.getCoordTuile("La Porte de Cuivre"));
        Messager mess = new Messager(grille.getCoordTuile("La Porte d’Argent"));
        Navigateur navig = new Navigateur(grille.getCoordTuile("La Porte d’Or"));
        Pilote pilot = new Pilote(grille.getCoordTuile("Heliport"));
        Plongeur plong= new Plongeur(grille.getCoordTuile("La Porte de Fer"));
        
        aventuriers.add(explo);
        aventuriers.add(inge);
        aventuriers.add(mess);
        aventuriers.add(navig);
        aventuriers.add(pilot);
        aventuriers.add(plong);
        Collections.shuffle((List<?>) aventuriers);
        
        ArrayList<Aventurier> aventuriersTemp = new ArrayList<>();
        for (int i=0 ; i<nbjoueur; i++){
            aventuriersTemp.add(aventuriers.get(i));
            //* Ecrire pour chaque joueur son rôle en utilisant joueur i : get(i).getNom();
        }
        
        aventuriers = aventuriersTemp;
    }
    
              
    public void updateVuePlateau(){
        ArrayList<Pion> pionAAfficher;
        for(Map.Entry<Coordonnees,Tuile> e : grille.getHSTuile().entrySet()){
            pionAAfficher = new ArrayList<Pion>();
            if(e.getValue() != null){
                String coord = e.getKey().getX() + e.getKey().getY();
                String nomCase = e.getValue().getNomT().toString();
                EtatTuile etatTuile = e.getValue().getEtat();
                TypeTrésor tresor = e.getValue().getTresor();
                
                for(Aventurier a : aventuriers){
                    if(a.getPosition().equals(e.getKey())){
                        pionAAfficher.add(getPionAventurier(a));
                    }
                }
                
                vuePlateau.updateCase(coord, nomCase, etatTuile, tresor, pionAAfficher);
            }
        }
    }

    public Pion getPionAventurier(Aventurier a){
        switch(a.getNom()){
            case "Explorateur" : return Pion.ROUGE;
            case "Ingenieur" : return Pion.VERT;
            case "Messager" : return Pion.ORANGE;
            case "Navigateur" : return Pion.JAUNE;
            case "Pilote" : return Pion.BLEU;
            case "Plongeur" : return Pion.VIOLET;
            default: return null;
        }
    }
        
    
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        Controleur controleur = new Controleur();
        
        
        
        
        
    }
    public boolean isPerdu(){
         if((grille.getTuile("Le Temple du Soleil").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Temple de La Lune").getEtat()==EtatTuile.COULEE)){
             return true;
         }
         else if ((grille.getTuile("La Caverne des Ombres").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("La Caverne du Brasier").getEtat()==EtatTuile.COULEE)){
             return true;
         }
         else if ((grille.getTuile("Le Palais de Corail").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Palais des Marees").getEtat()==EtatTuile.COULEE)){
             return true;
         }
         else if ((grille.getTuile("Le Jardin des Murmures").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Jardin des Hurlements").getEtat()==EtatTuile.COULEE)){
             return true;
         }
         else if(grille.getTuile("Heliport").getEtat()==EtatTuile.COULEE){
             return true;
         }
         else if(niveauEau.getNiveauEau()==10){
             return true;
         }
         else{
             return false;
         }
    }
    public boolean isGagne(){
        int nbavenheli=0;
        int nbaventres=0;
        boolean carteHeli;
        for (Aventurier atemp : aventuriers){
            if (grille.getTuile(atemp.getPosition())==grille.getTuile("Heliport")){
                nbavenheli=nbavenheli+1;
            }
            for (CarteTrésor trestemp : atemp.getMainCarteTrésor()){
                if (trestemp == ){
                    
                }
                
            }
            if(atemp.){
                
            }
        }
        if (nbavenheli==4 && nbaventres ==4){
            
        }
        return true;
    }
}
