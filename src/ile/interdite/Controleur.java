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
import static Util.Images.sam;
import Util.NomTrésor;
import Util.TypeCarteActivable;
import Util.TypeCarteTresor;
import Util.Utils;
import Util.Utils.EtatTuile;
import Util.Utils.Pion;
import Vue.PopUpGif;
import Vue.panels.KitPanelAventurier;
import Vue.VueLancement;
import Vue.VueLoose;
import Vue.VuePlateau;
import Vue.VueWin;
import Vue.panels.PanelCarteTrophee;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controleur implements Observateur {

    private Grille grille;
    private VueLancement vueL;
    private VueLoose vuePerdu;
    private VueWin vueGagner;
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
    
    private int nbJoueurs,nivDif;
    private ArrayList<String> nomJ;


    public Controleur() {
        lancerPartie();
    }

    public final void lancerPartie(){
        vueL= new VueLancement(this);
    }
    public void initPartie(int nbj,int nivdif,ArrayList<String> nomJ){
        //On reset les vues
        vueL=null;
        vuePerdu=null;
        vuePlateau=null;
        
        //Creer le niveau d'eau
        niveauEau = new EchelleNiveauEau(nivdif);
        
        //Créer la grille
        grille = new Grille();      //Modifier dans la classe Grille pour avoir les differentes grilles
        
        //Sauver les valeurs
        this.nbJoueurs=nbj;
        this.nivDif=nivdif;
        this.nomJ=nomJ;
        
        
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
        //Enlevever cette partie pour le test 1
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
        vuePlateau = new VuePlateau(kitsPanelAventurier,niveauEau.getNiveauEau(),getNbCartePiocheInondation(),getListeCarteDefausseInondation(),getNbCartePiocheTresor(),getListeCarteDefausseTresor(),this);
        updateVuePlateau();
        
        lancerTour();
    }

    public void lancerTour(){
        //Vérification de la main de l'aventurier
        if(aventuriers.get(0).doitDefausser() && !isPerdu()){
            vuePlateau.popUpDefausse(aventuriers.get(0).getMainCarteTrésor());
            return;

        }
        
        vuePlateau.setActive(aventuriers.get(0).getNom());
        aventuriers.get(0).resetActionsRestantes();
        
    }

    public void finTour(){
        gagne();
        vuePlateau.setInactive(aventuriers.get(0).getNom());
        //On redonne le nombre d'actions max a l'aventurier
        
        //pioche des 2 cartes trésor
        for(int i = 0;i<2;i++){
            piocherCT(aventuriers.get(0));  //l'aventurier pioche une carte
        }
        
        
        //picohe du nombre nécéssaire de cartes Inondation
        
        /*Enlever pour la grille de test 1, pas d'inondation lors de la fin du tour(plus simple)*/
        for (int c=1;c<=niveauEau.getNbInond();c++){
            inonderTuile();
        }
        /////////////////////////////////////////////
        
        
        aventuriers.get(0).resetActionsRestantes();
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
        
    public int getNbCartePiocheInondation(){
        int i = 0;
        for(CarteInondation cI : piocheCarteInondation){
            i++;
        }
        return i;
    }
    
    public int getNbCartePiocheTresor(){
        int i = 0;
        for(CarteTrésor cT : piocheCarteTrésor){
            i++;
        }
        return i;
    }
    

    public ArrayList<String> getListeCarteDefausseInondation(){
        ArrayList<String> listeCarteDefausse = new ArrayList<>();
        for(CarteInondation cI : defausseCarteInondation){
            listeCarteDefausse.add(cI.getNomTuile().toString());
        }
        return listeCarteDefausse;
    }
    
    public ArrayList<String> getListeCarteDefausseTresor(){
        ArrayList<String> listeCarteDefausse = new ArrayList<>();
        for(CarteTrésor cT : defausseCarteTrésor){
            if(cT.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                listeCarteDefausse.add(((Activable)cT).getTypeCarteActivable().toString());
            }else if(cT.getTypeCarteTresor().equals(TypeCarteTresor.MonteeDesEaux)){
                listeCarteDefausse.add(cT.getTypeCarteTresor().toString());
            }else if(cT.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                listeCarteDefausse.add(((CarteTrésorTrophée)cT).toString());
            }
        }
        return listeCarteDefausse;
    }


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
            remplirPiocheTresor();   
            if(piocheCarteTrésor.isEmpty()){  
                //si les joueurs ont déja toutes les cartes disponibles
                vuePlateau.popUpMessage("Vous possédez toutes les cartes");
            }else{
                //On pioche une carte
                CarteTrésor carte= piocheCarteTrésor.get(0);
                

                if(carte.getTypeCarteTresor().equals(TypeCarteTresor.MonteeDesEaux)){   //Si c'est une carte montée des eaux
                    defausseCarteTrésor.add(carte);
                    monteeDesEaux();                                                    //on augmente le niveau d'eau
                    
                }else{
                    a.getMainCarteTrésor().add(carte);                                  //Sinon on l'ajoute à la main de l'aventurier
                    piocheCarteTrésor.remove(0);                                            //On l'enlève de la pioche
                }
            }
        }else{
                //On pioche une carte
                CarteTrésor carte= piocheCarteTrésor.get(0);   
                piocheCarteTrésor.remove(0);                                            //On l'enlève de la pioche

                if(carte.getTypeCarteTresor().equals(TypeCarteTresor.MonteeDesEaux)){   //Si c'est une carte montée des eaux
                    defausseCarteTrésor.add(carte);
                    monteeDesEaux();                                                    //on augmente le niveau d'eau
                }else{
                    a.getMainCarteTrésor().add(carte);                                  //Sinon on l'ajoute à la main de l'aventurier
                }
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

    
    public void prendreTresor(NomTrésor nomT) {
            // TODO - implement Controleur.prendreTresor
            if(priseTresorPossible(nomT)){
                int i =0;
                ArrayList<CarteTrésor> toRemove = new ArrayList<>();
                for(CarteTrésor carte :aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                        if(((CarteTrésorTrophée)carte).getNomT().equals(nomT)){
                            if(i<=3){
                                toRemove.add(carte);
                                i++;
                            }
                        }
                    }
                }
                
                aventuriers.get(0).getMainCarteTrésor().removeAll(toRemove);
                defausseCarteTrésor.addAll(toRemove);
                
                if(nomT.equals(NomTrésor.Calice)){
                    priseCalice=true;
                }else if(nomT.equals(NomTrésor.Cristal)){
                    priseCristal=true;
                }else if(nomT.equals(NomTrésor.Pierre)){
                    prisePierre=true;
                }else if(nomT.equals(NomTrésor.Zéphyr)){
                    priseZephyr=true;
                }
                aventuriers.get(0).setActionsRestantes(aventuriers.get(0).getActionsRestantes()-1);

            }
                
                
            
    }
    

    //TEST CASE PRENDRE TRESOR

    public boolean priseTresorPossible(NomTrésor nomT) {
            int nbCartes=0;
            if(aventuriers.get(0).getActionsRestantes()!=0){
                if ((aventuriers.get(0).getMainCarteTrésor().size()>=4)&&(nomT!=null)){
                    for(CarteTrésor carte :aventuriers.get(0).getMainCarteTrésor()){
                        if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                            if(((CarteTrésorTrophée)carte).getNomT().equals(nomT)){
                                nbCartes = nbCartes +1;  
                            }
                        }
                    }
                    if(nbCartes>=4){
                        return true;
                    }else{
                        vuePlateau.popUpMessage("Il manque des cartes!");
                    }
                }else{
                    vuePlateau.popUpMessage("Il manque des cartes!");
                }

            }else{
                vuePlateau.popUpMessage("Plus d'actions restantes");
            }
            
            return false;                
    }


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
                        if (!deplacement.isEmpty()){                                              
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
            
              perdu();
            
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
        
        if(isPerdu()){                                                          //on vérifie qi la partie est perdue
            perdu();
        }
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
                aventuriers.get(0).deplacement(c,grille);
                updateVuePlateau();
                break;

            case ASSECHER:
                vuePlateau.resShow();
                c = m.getCoord();
                aventuriers.get(0).assecher(c,grille);
                updateVuePlateau();

                break;
                
            case ALLER_HELICO:
                vuePlateau.resShow();
                c = m.getCoord();
                aventuriers.get(0).setPosition(c);
                
                for(CarteTrésor carte : aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                        if(((Activable)carte).getTypeCarteActivable().equals(TypeCarteActivable.Helicoptere)){
                            aventuriers.get(0).getMainCarteTrésor().remove(carte);
                            defausseCarteTrésor.add(carte);
                            updateVuePlateau();
                            break;
                        }
                    }
                }
                gagne();
                break;

            case ASSECHER_SAC:
                vuePlateau.resShow();
                c = m.getCoord();
                grille.getTuile(c).assechement();
                
                //on supprime la carte de sa main
                for(CarteTrésor carte : aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                        if(((Activable)carte).getTypeCarteActivable().equals(TypeCarteActivable.SacsDeSable)){
                            aventuriers.get(0).getMainCarteTrésor().remove(carte);
                            defausseCarteTrésor.add(carte);
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

            case PROPOSER_DONATION_CARTE:
                vuePlateau.resShow();
                ArrayList<Aventurier> aventuriersTemp = new ArrayList<>();
                
                for(int i =1;i<aventuriers.size();i++){
                    if(aventuriers.get(0).donnerCartePossible(aventuriers.get(i))){
                        aventuriersTemp.add(aventuriers.get(i));
                    }
                }
                ArrayList<CarteTrésorTrophée> listeCarteTresor = new ArrayList<>();
                CarteTrésorTrophée carteTrophee;
                for(CarteTrésor carte : aventuriers.get(0).getMainCarteTrésor()){
                    if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                        carteTrophee=(CarteTrésorTrophée)carte;
                        listeCarteTresor.add(carteTrophee);
                    }
                }
                
                if(aventuriers.get(0).getMainCarteTrésor().isEmpty()||listeCarteTresor.isEmpty()){
                    vuePlateau.popUpMessage("Vous n'avez pas de cartes à donner!");
                }else if(aventuriersTemp.isEmpty()){
                    vuePlateau.popUpMessage("Personne à proximité");
                }else if(aventuriers.get(0).getActionsRestantes()==0){
                    vuePlateau.popUpMessage("Plus d'actions restantes");
                }else{
                    vuePlateau.setInactive(aventuriers.get(0).getNom());
                    vuePlateau.popUpDonnerCarte(listeCarteTresor, aventuriersTemp);
                }
                
                updateVuePlateau();
                break;
            case DONNERCARTE_Donner:
                m.getAventurierRecepteur().getMainCarteTrésor().add(m.getCTTrophée());
                aventuriers.get(0).getMainCarteTrésor().remove(m.getCTTrophée());
                
                vuePlateau.setActive(aventuriers.get(0).getNom());
                updateVuePlateau();
                break;
                
            case DONNERCARTE_Annuler:
                vuePlateau.setActive(aventuriers.get(0).getNom());
                updateVuePlateau();
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
                    m.getpCA().setClicked(!m.getpCA().getClicked()); 
                    if(m.getpCA().getClicked()){
                        vuePlateau.showAssechablesSac(assechementPossibleSac().keySet());
                    }
                }

                break;

            case PROPOSER_DEPLACEMENT_HELICO:
                vuePlateau.resShow();
                if(m.getpA().getNomAventurier().equals(aventuriers.get(0).getNom().toString())){
                    m.getpCA().setClicked(!m.getpCA().getClicked()); 
                    if(m.getpCA().getClicked()){
                        vuePlateau.showDeplacementPossibeHelico(deplacementPossibeHelico().keySet());
                    }
                }
                break;
                     
            case PRENDRETRESOR:
                vuePlateau.resShow();
                Aventurier a = getAventurier(m.getJoueur());
                if((grille.getTuile(a.getPosition())==grille.getTuile("Le Temple du Soleil")||grille.getTuile(a.getPosition())==grille.getTuile("Le Temple de La Lune"))){
                        prendreTresor(NomTrésor.Pierre);
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("La Caverne des Ombres")||grille.getTuile(a.getPosition())==grille.getTuile("La Caverne du Brasier"))){
                        prendreTresor(NomTrésor.Cristal);
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Palais de Corail")||grille.getTuile(a.getPosition())==grille.getTuile("Le Palais des Marees"))){
                        prendreTresor(NomTrésor.Calice);
                }
                else if((grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Murmures")||grille.getTuile(a.getPosition())==grille.getTuile("Le Jardin des Hurlements"))){
                        prendreTresor(NomTrésor.Zéphyr);
                }else{
                    vuePlateau.popUpMessage("Il n'y a pas de trésors ici!");
                }
                updateVuePlateau();
                break;

            case RELANCERJEU:
                lancerPartie();
                vuePlateau.dispose();
                
                break;
                
            case REJOUER:
                vuePlateau.dispose();
                initPartie(nbJoueurs, nivDif, nomJ);
                break;
                
            case QUITTER:
                if (vueL!=null) {
                    vueL.dispose();
                }
                if (vuePerdu!=null) {
                    vuePerdu.dispose();
                }
                if (vuePlateau!=null) {
                    vuePlateau.dispose();
                }
                
            case TERMINERTOUR:
                vuePlateau.resShow();
                finTour();
                if(!isPerdu()){
                   lancerTour(); 
                }
                updateVuePlateau();
                break;
                
            default:
                updateVuePlateau();
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

        Navigateur navig = new Navigateur(grille.getCoordTuile("La Porte d’Or"));
        Messager mess = new Messager(grille.getCoordTuile("La Porte d’Argent"));
        Plongeur plong= new Plongeur(grille.getCoordTuile("La Porte de Fer"));
        Ingenieur inge = new Ingenieur(grille.getCoordTuile("La Porte de Cuivre"));
        Explorateur explo = new Explorateur(grille.getCoordTuile("La Porte de Bronze"));
        Pilote pilot = new Pilote(grille.getCoordTuile("Heliport"));
        
        
        //Plongeur plong= new Plongeur(grille.getCoordTuile("Le Marais Brumeux"));      //grille test 1
        //Ingenieur inge = new Ingenieur(grille.getCoordTuile("Le Val du Crepuscule")); //grille test 1
        //Explorateur explo = new Explorateur(grille.getCoordTuile("Le Marais Brumeux")); //grille test 1
        //Pilote pilot = new Pilote(grille.getCoordTuile("Le Marais Brumeux"));         //grille test 1
        
        //Messager mess= new Messager(grille.getCoordTuile("La Porte de Cuivre"));      //grille test 2
        //Pilote pilot = new Pilote(grille.getCoordTuile("La Porte de Cuivre"));        //grille test 2
        
        aventuriers.add(explo);
        aventuriers.add(inge);
        aventuriers.add(mess);
        aventuriers.add(navig);
        aventuriers.add(pilot);
        aventuriers.add(plong);
        Collections.shuffle((List<?>) aventuriers);
        
        ArrayList<Aventurier> aventuriersTemp = new ArrayList<>();
        /*choix des joueurs aléatoire, a enlever pour les grilles de test*/
        for (int i=0 ; i<nbjoueur; i++){
            aventuriersTemp.add(aventuriers.get(i));
        }
        
        //Pour la grille de test 1 : deplacement, assechement, case coulée avec aventurier dessus
        /*
        aventuriersTemp.add(plong);
        aventuriersTemp.add(pilot);
        aventuriersTemp.add(explo);
        aventuriersTemp.add(inge);
        */
        
        //Pour la grille de test 2 : tresor, donnation de cartes
        /*aventuriersTemp.add(mess);
        aventuriersTemp.add(pilot);
        */
        
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
        vuePlateau.updatePanelsPioche(getNbCartePiocheInondation(),getListeCarteDefausseInondation(),getNbCartePiocheTresor(),getListeCarteDefausseTresor());
        vuePlateau.majIconesTresors(priseCalice, priseCristal, prisePierre, priseZephyr);
        vuePlateau.setNiveauEchelleEau(niveauEau.getNiveauEau());
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
        
        
        //grille test 1
        //return false;
        
        
        
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
        for (Aventurier aTMP : aventuriers){                                    //pour chaque aventurier
            Tuile t = grille.getTuile(aTMP.getPosition());                      //on note sa tuile
            if(t.getEtat()==EtatTuile.COULEE){                                  //si la tuile est coulée
                HashMap<Coordonnees,Tuile> deplacement;
                deplacement = aTMP.deplacementPossibleListe(grille);                //on fait une HashMap de toutes les tuiles qu'il peut atteindre
                if (deplacement.isEmpty()){                                         //si la liste est vide
                    return true;                                                        //alors la partie est perdue
                }
            }
        }
            
        return false;
         
    }
    
    public void perdu(){
        if(isPerdu()){
         vuePlateau.setAllInactive();
        if(vuePerdu==null){
            vuePerdu = new VueLoose(this);
        }   
        }
        
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
      
    public void gagne(){
        if(isGagne()){
            vuePlateau.setAllInactive();

            if(vueGagner==null){
                vueGagner = new VueWin(this);
            }
        }
        
    }
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        Controleur controleur = new Controleur();    
    }
    
}
