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
import Vue.KitPanelAventurier;
import Vue.VueAventurier;
import Vue.VueLancement;
import Vue.VuePlateau;

public class Controleur implements Observateur {

	//Collection<CarteTrésor> piocheCarteTrésor;
	private Grille grille;
        private VueLancement vueL;
        private ArrayList<Aventurier> aventuriers;
        private ArrayList<VueAventurier> vuesAventuriers;
        
        private EchelleNiveauEau niveauEau;
        private VuePlateau vuePlateau;
        private ArrayList<CarteTrésor> piocheCarteTrésor;
	private ArrayList<CarteTrésor> defausseCarteTrésor;
	private ArrayList<CarteInondation> piocheCarteInondation;
	private ArrayList<CarteInondation> defausseCarteInondation;
	private ArrayList<CarteInondation> defausseCarteCoulées;
        private ArrayList<TypeTrésor> tresors;

        
        private boolean prisePierre;
        private boolean priseCristal;
        private boolean priseZephyr;
        private boolean priseCalice;

        
        public Controleur() {
            lancerPartie();
            
        }
        
        public void lancerPartie(){
            vueL= new VueLancement(this);
        }
        
        public void initPartie(int nbj,int nivdif,ArrayList<String> nomJ){
            //Créer la grille
            grille = new Grille();
            
            //Créer les Aventuriers
            creationAventurier(nbj);
            
            //Creér les vues de Aventuriers
            vuesAventuriers = new ArrayList<>();
            
            //Créer les cartes
            piocheCarteInondation = new ArrayList<>();
            piocheCarteTrésor = new ArrayList<>();
            defausseCarteCoulées = new ArrayList<>();
            defausseCarteInondation = new ArrayList<>();
            initCartetresor();
            initPiocheInondation();
            for (int a=1;a<=6;a++){                 //inondation des 6 tuiles au début
                inonderTuile();
            }
            
            
            //Création et Mise à jour du plateau
            int i = 0;
            ArrayList<KitPanelAventurier> kitsPanelAventurier = new ArrayList<>();
            for(Aventurier a : aventuriers){
                kitsPanelAventurier.add(new KitPanelAventurier(nomJ.get(i), a.getNom(), getPionAventurier(a).getCouleur()));
                i++;
            }
            vuePlateau = new VuePlateau(kitsPanelAventurier,this);
            updateVuePlateau();
            niveauEau = new EchelleNiveauEau(1);
            lancerTour();
            
        }
        
        public void lancerTour(){
            vuePlateau.setActive(aventuriers.get(0).getNom());
        }
        
        public void finTour(){
            vuePlateau.setInactive(aventuriers.get(0).getNom());
            aventuriers.get(0).resetActionsRestantes();
            aventuriers.get(0).piocherCT(piocheCarteTrésor);
            aventuriers.get(0).resetAutreA();
            
            for (int c=1;c<=niveauEau.getNbInond();c++){
                if (piocheCarteInondation.isEmpty()){
                    nouvellePile();
                }
                inonderTuile();
            }
            //voir si tout est coulé
            int fi=0;
            for(Map.Entry<Coordonnees,Tuile> e : grille.getHSTuile().entrySet()){
                if(e.getValue() != null){
                    if (e.getValue().getEtat()==EtatTuile.COULEE){
                        fi = fi+1;
                    }
                    if (e.getValue().getNomT()==NomTuile.Heliport && e.getValue().getEtat()==EtatTuile.COULEE){
                        fi=grille.getHSTuile().size();
                        System.out.println("plus d'heliport");
                    }
                }
            }
            
            if (fi>=grille.getHSTuile().size()){
                System.out.println("C'EST LA FIN");
            }
            
            
            
            
            setJoueurSuivant();
        }

        public void setJoueurSuivant(){
            Aventurier avenTmp = aventuriers.get(0);
            aventuriers.remove(0);
            aventuriers.add(avenTmp);
        }
        
	/*public VueAventurier getVueAventurier(Aventurier a){
            for(VueAventurier vA : vuesAventuriers){
                if(vA.getNomAventurier().equals(a.getNom())){
                    return vA;
                }
            }
            return null;
        }*/
        

        

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
     * @return 
	 */
	public boolean donnationPossible(Aventurier aven1, Aventurier aven2) {
		return aven1.getPosition() == aven2.getPosition();
                
	}
        
    public boolean doitDefausser(Aventurier a) {
            // TODO - implement Controleur.priseTresorPossible
            return (a.getMainCarteTrésor().size()>5);
    }
    
       
    //initialisation de la pioche Inondation au début de la partie
        public void initPiocheInondation(){
            for (NomTuile tuile : NomTuile.values()){   //la pioche de base est constituée de toutes les tuiles
                CarteInondation carte = new CarteInondation(tuile);
                Tuile t = grille.getTuile(carte.getNomTuile().toString());
                //if (t.getEtat()!=EtatTuile.COULEE){ //si c'est grilleProf
                    piocheCarteInondation.add(carte);       //mise de toutes les tuiles de la grille dans la pioche
                //}
            }
            
            Collections.shuffle(piocheCarteInondation); //on mélange la pioche
        }
        
        //inondation d'une tuile
        public void inonderTuile(){
            if (piocheCarteInondation.isEmpty()){
                System.out.println("C'EST LA FIN aaaaaaaa");
            }else{
            CarteInondation cI;
            cI = piocheCarteInondation.get((0));
            Tuile t = grille.getTuile(cI.getNomTuile().toString());
            Coordonnees co = grille.getCoordTuile(cI.getNomTuile().toString());
            if (t.getEtat()==EtatTuile.ASSECHEE){                       //si la tuile est assechée
                t.setEtat(EtatTuile.INONDEE);                           //elle devient inondée
                defausseCarteInondation.add(cI);                        //et le carte va dans la defausse
            }else if(t.getEtat()==EtatTuile.INONDEE){                   //si la tuile est assechée
                t.setEtat(EtatTuile.COULEE);                            //elle devient coulée
                
                
                //vérifier si il y a des aventuriers sur cette case
                for(Aventurier a : aventuriers){
                    if(a.getPosition().equals(co)){                     //si l'aventurier est sur la case qui vient d'être coulée
                        
                    }
                }
                
                
                defausseCarteCoulées.add(cI);                           //et la carte est retirée
            }
            piocheCarteInondation.remove(cI);                           //la carte est enlevée de la pioche
            }
        }
        //si plus de carte dans la pioche
        public void nouvellePile(){
            Collections.shuffle(defausseCarteInondation);               //on mélange la défausse
            for (CarteInondation tuile :defausseCarteInondation ){      //on met toutes les cartes de la défausse dans la pioche
                piocheCarteInondation.add(tuile);
            }
            defausseCarteInondation.clear();                            //on vide la défausse
        }

        
        
        
        
        
        
    @Override
    public void traiterMessage(Message m) {
        Coordonnees c;
            int entier = 0;
        //A Faire 
        

            switch (m.getTypeMessage()) {
                case ALLER:
                    vuePlateau.resShow();
                    
                        c = m.getCoord();
                        System.out.println("Déplacement! (" + c.getX() +","+ c.getY() +")");
                        aventuriers.get(0).deplacement(c,grille);

                    

                    break;
                case ASSECHER:
                    vuePlateau.resShow();
                    


                        
                        c = m.getCoord();

                        System.out.println("Assècher! (" + c.getX() +","+ c.getY() +")");
                        aventuriers.get(0).assecher(c,grille);
                        

                    

                    break;
                    
                case VAL2:
                    if(m.getNivDif().equals("Novice")){
                        entier=1;
                    }else if(m.getNivDif().equals("Normal")){
                       entier=2; 
                    }else if(m.getNivDif().equals("Expert")){
                       entier=3; 
                    }else if(m.getNivDif().equals("Légendaire")){
                       entier=4; 
                    }
                        initPartie((Integer.parseInt(m.getJoueur())),entier,m.getJoueurs());
                    break;   
                    
                    
                    
                case DONNERCARTE:
                    vuePlateau.resShow();
                    //if(donnationPossible(aventuriers.get(0), aven2)){
                      //  aventuriers.get(0).donnerCarte(aven2, carte);
                    //}
                    break;
                    
                case AUTREACTION:
                    /*if(!getAventurier(m.getJoueur()).isAutreA()){
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
                    }*/
                    
                    
                    break;
                case PRENDRETRESOR:
                    vuePlateau.resShow();
                    Aventurier a = getAventurier(m.getJoueur());
                    if((grille.getTuile(a.getPosition())==grille.getTuile("Le Temple du Soleil")||grille.getTuile(a.getPosition())==grille.getTuile("Le Temple de La Lune"))&& a.prendreTresor(TypeTrésor.Pierre)){
                             prisePierre=true;
                    }
                    else if((grille.getTuile(a.getPosition())==grille.getTuile("La Caverne des Ombres")||grille.getTuile(a.getPosition())==grille.getTuile("La Caverne du Brasier"))&& a.prendreTresor(TypeTrésor.Cristal)){
                             priseCristal=true;
                    }
                    else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Palais de Corail")||grille.getTuile(a.getPosition())==grille.getTuile("Le Palais des Marees"))&& a.prendreTresor(TypeTrésor.Calice)){
                             priseCalice=true;
                    }
                    else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Murmures")||grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Hurlements"))&& a.prendreTresor(TypeTrésor.Zéphyr)){
                             priseZephyr=true;
                    }
                case TERMINERTOUR:
                    vuePlateau.resShow();
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
            if(i.getNom().toString().equals(nom)){
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
            pionAAfficher = new ArrayList<>();
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
        switch(a.getNom().toString()){
            case "Explorateur" : return Pion.ROUGE;
            case "Ingenieur" : return Pion.VERT;
            case "Messager" : return Pion.ORANGE;
            case "Navigateur" : return Pion.JAUNE;
            case "Pilote" : return Pion.BLEU;
            case "Plongeur" : return Pion.VIOLET;
            default: return null;
        }
    }
    
    //méthode qui vérifie si la partie est perdue.
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
        return false;
         
    }
    // méthode pour vérifier si les joueurs ont gagné
    public boolean isGagne(){
        int nbavenheli=0;
        int nbaventres=0;
        boolean carteHeli=false;
        for (Aventurier atemp : aventuriers){
            if (grille.getTuile(atemp.getPosition())==grille.getTuile("Heliport")){
                nbavenheli=nbavenheli+1;
            }
            for (TypeTrésor tres : tresors){
                for (CarteTrésor main : atemp.getMainCarteTrésor()){
                    
                    
                    if("Hélicoptère".equals(main.getNomCT())){
                        carteHeli=true;
                    }
                }                
            }   
        }
            return nbavenheli==4 && priseCalice&& prisePierre && priseCristal && priseZephyr && carteHeli;
    }
        
    
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        Controleur controleur = new Controleur();    
    }
    
}
