package ile.interdite;

import Model.cartesTresor.Activable;
import java.util.*;

import Model.CarteInondation;
import Util.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Model.cartesTresor.CarteTrésor;
import Model.Aventuriers.Aventurier;
import Model.Aventuriers.Explorateur;
import Model.Aventuriers.Ingenieur;
import Model.Aventuriers.Messager;
import Model.Aventuriers.Navigateur;
import Model.Aventuriers.Pilote;
import Model.Aventuriers.Plongeur;
import Model.EchelleNiveauEau;
import Model.cartesTresor.Helico;
import Util.NomTuile;
import Model.cartesTresor.MonteeDesEaux;
import Model.cartesTresor.Sac;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.NomTrésor;
import Util.TypeCarteActivable;
import Util.TypeCarteTresor;
import Util.Utils;
import Util.Utils.EtatTuile;
import Util.Utils.Pion;
import Vue.Regles;
import Vue.panels.KitPanelAventurier;
import Vue.VueDefausse;
import Vue.VueLancement;
import Vue.VuePlateau;

public class Controleur implements Observateur {

    //Collection<CarteTrésor> piocheCarteTrésor;
    private Grille grille;
    private VueLancement vueL;
    private ArrayList<Aventurier> aventuriers;

    private EchelleNiveauEau niveauEau;
    private VuePlateau vuePlateau;
    private ArrayList<CarteTrésor> piocheCarteTrésor;
    private ArrayList<CarteTrésor> defausseCarteTrésor;
    private ArrayList<CarteInondation> piocheCarteInondation;
    private ArrayList<CarteInondation> defausseCarteInondation;
    private ArrayList<CarteInondation> defausseCarteCoulées;
    private ArrayList<NomTrésor> tresors;

    private boolean prisePierre;
    private boolean priseCristal;
    private boolean priseZephyr;
    private boolean priseCalice;


    public Controleur() {
        lancerPartie();
    }

    public final void lancerPartie(){
        vueL= new VueLancement(this);
    }
    public void initPartie(int nbj,int nivdif,ArrayList<String> nomJ){
        //Créer la grille
        grille = new Grille();

        //Créer les Aventuriers
        creationAventurier(nbj);

        //Créer les cartes
        piocheCarteInondation = new ArrayList<>();
        piocheCarteTrésor = new ArrayList<>();
        defausseCarteCoulées = new ArrayList<>();
        defausseCarteInondation = new ArrayList<>();
        defausseCarteTrésor = new ArrayList<>();
        initCartetresor();
        
        initPiocheInondation();
        for (int a=1;a<=6;a++){                 //inondation de 6 tuiles aleatoires au début
            inonderTuile();
        }

        //Création et Mise à jour du plateau
        int i = 0;
        ArrayList<KitPanelAventurier> kitsPanelAventurier = new ArrayList<>();
        for(Aventurier a : aventuriers){
            kitsPanelAventurier.add(new KitPanelAventurier(nomJ.get(i), a.getNom(),a.getMainCarteTrésor(), getPionAventurier(a).getCouleur()));
            i++;
        }
        vuePlateau = new VuePlateau(kitsPanelAventurier,this);
        updateVuePlateau();
        niveauEau = new EchelleNiveauEau(nivdif);
        lancerTour();
    }

    public void lancerTour(){
        //Vérification de la main de l'aventurier
        if(aventuriers.get(0).doitDefausser()){
            vuePlateau.popUpDefausse(aventuriers.get(0).getMainCarteTrésor());
            return;
        }
        
        vuePlateau.setActive(aventuriers.get(0).getNom());
        
    }

    public void finTour(){
        vuePlateau.setInactive(aventuriers.get(0).getNom());
        //On redonne le nombre d'actions max a l'aventurier
        aventuriers.get(0).resetActionsRestantes();
        //pioche des 2 cartes trésor
        for(int i = 0;i<2;i++){
            if(piocheCarteTrésor.isEmpty()){    //si la pioche tresor est vide
                remplirPiocheTresor();              //on la remplit
            }//else{
            piocherCT(aventuriers.get(0));  //l'aventurier pioche une carte
            //}
            
        }
        aventuriers.get(0).setMaxActions();
        
        //picohe du nombre nécéssaire de cartes Inondation
        for (int c=1;c<=niveauEau.getNbInond();c++){
            inonderTuile();
        }

        //Passage au joueur suivant
        setJoueurSuivant();
    }

    public void setJoueurSuivant(){
        Aventurier avenTmp = aventuriers.get(0);    //On enregistre l'aventuier actuel dans une variable
        aventuriers.remove(0);                      //on enleve l'aventurier de la liste aventuriers
        aventuriers.add(avenTmp);                   //On utilise la variable pour mettre l'aventurier a la fin de la liste 
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
            piocheCarteTrésor.add(new CarteTrésorTrophée(NomTrésor.Calice));    //ajout des 5 cartes tresor calice
        }
        for(int i=0;i<5;i++){
            piocheCarteTrésor.add(new CarteTrésorTrophée(NomTrésor.Pierre));    //ajout des 5 cartes tresor pierre
        }
        for(int i=0;i<5;i++){
            piocheCarteTrésor.add(new CarteTrésorTrophée(NomTrésor.Zéphyr));    //ajout des 5 cartes tresor zephyr
        }
        for(int i=0;i<5;i++){
            piocheCarteTrésor.add(new CarteTrésorTrophée(NomTrésor.Cristal));   //ajout des 5 cartes tresor cristal
        }
        for(int i=0;i<3;i++){
            piocheCarteTrésor.add(new Helico());                                //ajout des 3 cartes helicoptere
        }
        for(int i=0;i<2;i++){
            piocheCarteTrésor.add(new Sac());                                   //ajout des 3 cartes sac
        }
        for(int i=0;i<2;i++){
            piocheCarteTrésor.add(new MonteeDesEaux());                         //ajout des 2 cartes montée des eaux
        }

        piocheCarteTrésor= Utils.melangerCT(piocheCarteTrésor);                 //melange de la picohe tresor
        for(Aventurier a : aventuriers){
            piocherDebutJeuCT(a);
        }
    }

    public void piocherCT(Aventurier a){
        if(piocheCarteTrésor.isEmpty()){                                        //si la pioche Trésor est vide
            remplirPiocheTresor();                                              //On la remplit
        }
        CarteTrésor carte= piocheCarteTrésor.get(0);                            //On pioche une carte
        piocheCarteTrésor.remove(0);                                            //On l'enlève de la pioche

        if(carte.getTypeCarteTresor().equals(TypeCarteTresor.MonteeDesEaux)){   //Si c'est une carte montée des eaux
            monteeDesEaux();                                                    //on augmente le niveau d'eau
        }else{
            a.getMainCarteTrésor().add(carte);                                  //Sinon on l'ajoute à la main de l'aventurier
        }


    }
        
    public void piocherDebutJeuCT(Aventurier a){
        CarteTrésor carte;
        for(int i = 0;i<2;i++){             //pioche 2 cartes
            carte = piocheCarteTrésor.get(0);
            while(carte.getTypeCarteTresor().equals(TypeCarteTresor.MonteeDesEaux)){    //si la carte est une carte montée des eaux
                Utils.melangerCT(piocheCarteTrésor);                                    //On mélange la picohe Trésor
                carte = piocheCarteTrésor.get(0);                                       //On repioche une carte
            }
            a.getMainCarteTrésor().add(carte);                                          //on ajoute cette carte à la main de l'aventurier
            piocheCarteTrésor.remove(0);                                                //On enleve cette carte de la pioche Trésor
        }
    }

    public boolean donnationPossible(Aventurier aven1, Aventurier aven2) {
            return aven1.getPosition() == aven2.getPosition();

	}

    //TEST CASE PRENDRE TRESOR

    /* 
    public void prendreTresor() {
            // TODO - implement Controleur.prendreTresor

            if(priseTresorPossible(aventuriers.get(0))){
                NomTrésor tresor =(grille.getHSTuile().get(aventuriers.get(0).getPosition()).getTresor());
                aventuriers.get(0).getMainCarteTrésor().remove(tresor);
            }
    }
    */

    //TEST CASE PRENDRE TRESOR

    /*public boolean priseTresorPossible(Aventurier a) {
            // TODO - implement Controleur.priseTresorPossible
            NomTrésor tresor =(grille.getHSTuile().get(a.getPosition()).getTresor());
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
    }*/


    public boolean doitDefausser(Aventurier a) {
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
            if (piocheCarteInondation.isEmpty()){                               //si la picohe est vide, cela veut dire que toute les cases sont coulées, le jeu est perdu
                remplirPiocheInondation();
            }else{                                                              //Sinon
            CarteInondation cI;                                         
            cI = piocheCarteInondation.get((0));                                //L'aventurier prend la premiere carte de la pioche
            Tuile t = grille.getTuile(cI.getNomTuile().toString());             //t = tuile correspondante à la carte
            Coordonnees co = grille.getCoordTuile(cI.getNomTuile().toString()); 
            if (t.getEtat()==EtatTuile.ASSECHEE){                               //si la tuile est assechée
                t.setEtat(EtatTuile.INONDEE);                                   //elle devient inondée
                defausseCarteInondation.add(cI);                                //et le carte va dans la defausse
            }else if(t.getEtat()==EtatTuile.INONDEE){                           //si la tuile est assechée
                t.setEtat(EtatTuile.COULEE);                                    //elle devient coulée
                //on vérifie si il y a des aventuriers sur cette case
                for(Aventurier a : aventuriers){
                    if(a.getPosition().equals(co)){                             //si l'aventurier a est sur la case qui vient d'être coulée
                    //il se déplace vers une tuile qu'il peut atteindre
                        HashMap<Coordonnees,Tuile> deplacement;
                        deplacement = a.deplacementPossibleListe(grille);       //HashMap de toutes les tuiles/coordonnées qu'il peut atteindre
                        if (deplacement.isEmpty()){                             //si la liste est vide
                            System.out.println("fin");                          //le jeu est fini
                        }else{                                                  
                            ArrayList<Tuile> tuile = new ArrayList<>();         //sinon on créer une arrayList de tuiles
                            for (Tuile ttemp : deplacement.values()){           //qui contient toutes les tuiles de la HashMap
                                  tuile.add(ttemp);
                            }
                            int taille = deplacement.size();
                            Random r = new Random();
                            int valeur = 0 + r.nextInt(taille);                 //valeur = aléatoire entre 0 et la taille de la HashMap
                            a.setActionsRestantes(1);
                            Coordonnees coord = grille.getCoordTuile(tuile.get(valeur).getNomT().toString());   //coord = coordonnées de la tuile d'index valeur
                            a.deplacement(coord, grille);                       //l'aventurier se déplace sur la tuile de coordonnées coord
                        }
                    }
                }
                defausseCarteCoulées.add(cI);                                   //la carte est retirée
            }
            piocheCarteInondation.remove(cI);                                   //la carte est enlevée de la piocheInondation
            }
        }
    
    //si il n'y a plus de carte dans la pioche
    public void remplirPiocheInondation(){
        Utils.melangerCI(defausseCarteInondation);                              //mélange de la defausse
        for(CarteInondation ci : defausseCarteInondation){                      //On met chaque carte de la defausse dans la pioche
            piocheCarteInondation.add(ci);
        }
        defausseCarteInondation.clear();                                        //On vide la defausse
    }
    
    public void monteeDesEaux(){
        vuePlateau.popUpMonteeDesEaux();
        niveauEau.monteeDesEaux();
        Utils.melangerCI(defausseCarteInondation);                              //On melange la defausse des cartes inondation
        for(CarteInondation ci : piocheCarteInondation){                        //on ajoute la pioche a la defausse 
            defausseCarteInondation.add(ci);
        }
        piocheCarteInondation.clear();                                          //on vide la pioche
        for(CarteInondation ci : defausseCarteInondation){
            piocheCarteInondation.add(ci);                                      //on ajoute toutes les cartes de la defausse dans la pioche (les cartes de l'ancienne défausse sont les premieres de la pioche
        }
        defausseCarteInondation.clear();                                        //on vide la defausse
    }

    //si plus de carte dans la pioche
    public void remplirPiocheTresor(){
        Utils.melangerCT(defausseCarteTrésor);                  //on mélange la défausse
        for (CarteTrésor carte :defausseCarteTrésor ){          //on met toutes les cartes de la défausse dans la pioche
            piocheCarteTrésor.add(carte);
        }
        defausseCarteTrésor.clear();                            //on vide la défausse
    }    
        
        
    @Override
    public void traiterMessage(Message m) {
        Coordonnees c;
        Activable carteASupp;
        int entier = 0;
        switch (m.getTypeMessage()) {
            case ALLER:
                vuePlateau.resShow();
                c = m.getCoord();
                System.out.println("Déplacement! (" + c.getX() +","+ c.getY() +")");
                aventuriers.get(0).deplacement(c,grille);
                updateVuePlateau();
                break;

            case ASSECHER:
                vuePlateau.resShow();
                c = m.getCoord();
                System.out.println("Assècher! (" + c.getX() +","+ c.getY() +")");
                aventuriers.get(0).assecher(c,grille);
                updateVuePlateau();

                break;
                
            case ALLER_HELICO:
                vuePlateau.resShow();
                c = m.getCoord();
                System.out.println("Déplacement! (" + c.getX() +","+ c.getY() +")");
                aventuriers.get(0).setPosition(c);
                for(CarteTrésor carte : aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                        if(((Activable)carte).getTypeCarteActivable().equals(TypeCarteActivable.Helicoptere)){
                            aventuriers.get(0).getMainCarteTrésor().remove(carte);
                            updateVuePlateau();
                            break;
                        }
                    }
                }
                break;

            case ASSECHER_SAC:
                vuePlateau.resShow();
                c = m.getCoord();
                System.out.println("Assècher! (" + c.getX() +","+ c.getY() +")");
                for(CarteTrésor carte : aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                        if(((Activable)carte).getTypeCarteActivable().equals(TypeCarteActivable.SacsDeSable)){
                            aventuriers.get(0).getMainCarteTrésor().remove(carte);
                            updateVuePlateau();
                            break;
                        }
                    }
                }

                break;

            case VAL2:
                switch (m.getNivDif()) {
                    case "Novice":
                        entier=1;
                        break;
                    case "Normal":
                        entier=2;
                        break;
                    case "Expert":
                        entier=3;
                        break;
                    case "Légendaire":
                        entier=4;
                        break;
                    default:
                        break;
                }
                initPartie((Integer.parseInt(m.getJoueur())),entier,m.getJoueurs());
                updateVuePlateau();
                break;      

            case DONNERCARTE:
                vuePlateau.resShow();
                //if(donnationPossible(aventuriers.get(0), aven2)){
                  //  aventuriers.get(0).donnerCarte(aven2, carte);
                //}
                //updateVuePlateau();
                break;
            case DEFAUSSER:
                vuePlateau.resShow();
                for(CarteTrésor carte : m.getListeCarteTresorADefausse()){
                    aventuriers.get(0).getMainCarteTrésor().remove(carte);
                    defausseCarteTrésor.add(carte);
                }
                vuePlateau.setActive(aventuriers.get(0).getNom());
                updateVuePlateau();

                break;
            


            case PROPOSER_ASSECHEMENT:
                vuePlateau.resShow();
                vuePlateau.showAssechables(aventuriers.get(0).assechementPossibleListe(grille).keySet());
                updateVuePlateau();        
                break;

            case PROPOSER_DEPLACEMENT:
                vuePlateau.resShow();

                try {
                    Pilote pilote = (Pilote)aventuriers.get(0);
                    vuePlateau.showDeplacementPossible(pilote.deplacementPossibleListe(grille).keySet(),pilote.autreActionListe(grille).keySet());
                } catch (Exception e) {
                    vuePlateau.showDeplacementPossible(aventuriers.get(0).deplacementPossibleListe(grille).keySet());
                }
                updateVuePlateau();
                break;
            case PROPOSER_ASSECHEMENT_SAC:
                vuePlateau.resShow();
                if(m.getpA().getNomAventurier().toString().equals(aventuriers.get(0).getNom().toString())){
                    vuePlateau.showAssechablesSac(assechementPossibleSac().keySet());
                    m.getpCA().setClicked(!m.getpCA().getClicked()); 
                }

                break;

            case PROPOSER_DEPLACEMENT_HELICO:
                vuePlateau.resShow();
                if(m.getpA().getNomAventurier().toString().equals(aventuriers.get(0).getNom().toString())){
                    vuePlateau.showDeplacementPossibeHelico(deplacementPossibeHelico().keySet());
                    m.getpCA().setClicked(!m.getpCA().getClicked()); 
                }
                break;
                     
            case PRENDRETRESOR:
                vuePlateau.resShow();
                Aventurier a = getAventurier(m.getJoueur());
                if((grille.getTuile(a.getPosition())==grille.getTuile("Le Temple du Soleil")||grille.getTuile(a.getPosition())==grille.getTuile("Le Temple de La Lune"))&& a.prendreTresor(NomTrésor.Pierre)){
                         prisePierre=true;
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("La Caverne des Ombres")||grille.getTuile(a.getPosition())==grille.getTuile("La Caverne du Brasier"))&& a.prendreTresor(NomTrésor.Cristal)){
                         priseCristal=true;
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Palais de Corail")||grille.getTuile(a.getPosition())==grille.getTuile("Le Palais des Marees"))&& a.prendreTresor(NomTrésor.Calice)){
                         priseCalice=true;
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Murmures")||grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Hurlements"))&& a.prendreTresor(NomTrésor.Zéphyr)){
                         priseZephyr=true;
                }
                updateVuePlateau();
                break;

            case RELANCERJEU:
                lancerPartie();
                updateVuePlateau();
                break;


            case TERMINERTOUR:
                vuePlateau.resShow();
                System.out.println("Fin du Tour!");
                finTour();
                lancerTour();
                updateVuePlateau();
                break;
                
            default:
                break;
        }


    }
        
    public HashMap<Coordonnees,Tuile> assechementPossibleSac(){
        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
        

        for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
            if(i.getValue()!=null){
                
                if(grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.INONDEE)){
                    listeD.put( i.getKey(), i.getValue());   
                }     
            }
        }
        return listeD;
    }
    
    public HashMap<Coordonnees,Tuile> deplacementPossibeHelico(){
        HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
        

        for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
            if(i.getValue()!=null){
                
                if(grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.INONDEE)||grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.ASSECHEE)){
                    listeD.put( i.getKey(), i.getValue());   
                }     
            }
        }
        return listeD;
    }
    
    public Aventurier getAventurier(String nom){
        for(Aventurier i : aventuriers){
            if(i.getNom().toString().equals(nom)){
                return i;
            }
        }
        return null;
    }    
    
    //créer aléatoirement les joueurs de la partie en fonction du nombre voulu
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
        for(Aventurier a : aventuriers){
            vuePlateau.updateMainAventurier(a.getNom().toString(), a.getMainCarteTrésor());
        }
        ArrayList<Pion> pionAAfficher;
        for(Map.Entry<Coordonnees,Tuile> e : grille.getHSTuile().entrySet()){
            pionAAfficher = new ArrayList<>();
            if(e.getValue() != null){
                String coord = e.getKey().getX() + e.getKey().getY();
                String nomCase = e.getValue().getNomT().toString();
                EtatTuile etatTuile = e.getValue().getEtat();
                NomTrésor tresor = e.getValue().getTresor();
                
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
        //si les 2 cases d'un tresor sont coulées, la partie est perdue
        if((grille.getTuile("Le Temple du Soleil").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Temple de La Lune").getEtat()==EtatTuile.COULEE)&&prisePierre==false){
            return true;
        }
        else if ((grille.getTuile("La Caverne des Ombres").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("La Caverne du Brasier").getEtat()==EtatTuile.COULEE)&&priseCristal==false){
            return true;
        }
        else if ((grille.getTuile("Le Palais de Corail").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Palais des Marees").getEtat()==EtatTuile.COULEE)&&priseCalice==false){
            return true;
        }
        else if ((grille.getTuile("Le Jardin des Murmures").getEtat()==EtatTuile.COULEE)&&(grille.getTuile("Le Jardin des Hurlements").getEtat()==EtatTuile.COULEE)&&priseZephyr==false){
            return true;
        }
        //si l'heliport est coulé
        else if(grille.getTuile("Heliport").getEtat()==EtatTuile.COULEE){
            return true;
        }
        //si le niveau d'eau atteint 10
        else if(niveauEau.getNiveauEau()==10){
            return true;
        }
        for (Aventurier aTMP : aventuriers){
            Tuile t = grille.getTuile(aTMP.getPosition());
            if(t.getEtat()==EtatTuile.COULEE){                                  //si l'aventurier est sur la case coulée
                HashMap<Coordonnees,Tuile> deplacement;
                deplacement = aTMP.deplacementPossibleListe(grille);            //HashMap de toutes les tuiles/coordonnées qu'il peut atteindre
                if (deplacement.isEmpty()){                                     //si la liste est vide
                    return true;                                                    //alors la partie est perdue
                }
            }
        }
            
        return false;
         
    }
    // méthode pour vérifier si les joueurs ont gagné
    public boolean isGagne(){
        int nbavenheli=0;
        int nbaventres=0;
        boolean carteHeli=false;
        Activable carteActivable;
        for (Aventurier atemp : aventuriers){
            if (grille.getTuile(atemp.getPosition())==grille.getTuile("Heliport")){
                nbavenheli=nbavenheli+1;
            }
            for (NomTrésor tres : tresors){
                for (CarteTrésor main : atemp.getMainCarteTrésor()){           
                    if(main.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                        carteActivable = (Activable) main;
                        if(carteActivable.getTypeCarteActivable().equals(TypeCarteActivable.Helicoptere)){
                            carteHeli=true;
                        }
                    }
                }                
            }   
        }
            return nbavenheli==aventuriers.size() && priseCalice&& prisePierre && priseCristal && priseZephyr && carteHeli;
    }
        
    
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        Controleur controleur = new Controleur();    
    }
    
}
