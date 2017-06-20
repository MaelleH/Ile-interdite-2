package Model.Aventuriers;

import Model.CarteTrésor;
import Model.CarteTrésorTrophée;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Model.NomTrésor;
import Model.TypeCarteTresor;
import Util.Utils;
import Util.Utils.NomAventurier;
import static Util.Utils.afficherInformation;
import java.util.*;
import java.util.HashMap;

public class Aventurier {
	private ArrayList<CarteTrésor> mainCarteTrésor = new ArrayList<>();
	private int actionsRestantes;
	private Coordonnees position;
        private boolean autreA=false;
       
    public Aventurier(){
        this.actionsRestantes=getMaxActions();
    }
    public Aventurier(Coordonnees position) {
        this.position=position;
        this.actionsRestantes=getMaxActions();
    }
        

	public Coordonnees getPosition() {
		return this.position;
	}
        
        public void autreAction(Coordonnees c,Grille grille ){
            afficherInformation("Vous n'avez pas d'action de bg!");
        }
        public void piocherCT(ArrayList<CarteTrésor> piocheCarteTrésor){
            getMainCarteTrésor().add(piocheCarteTrésor.get(0));
            piocheCarteTrésor.remove(0);
            
            if(doitDefausser()){
                //TODO
                //PENSER A METTRE A JOUR LA DEFAUSSE
                
            }
        }

        public boolean doitDefausser() {
            // TODO - implement Controleur.priseTresorPossible
            return (getMainCarteTrésor().size()>5);
        }
                
	/**
	 * 
	 * @param a
     * @param grille
         * @param c

	 */
	public void deplacement(Coordonnees c,Grille grille ) {
            if(this.getActionsRestantes()>0&&deplacementPossibleListe(grille).containsKey(c)){
		setPosition(c);
                setActionsRestantes(getActionsRestantes()-1);
            }
            else if(this.getActionsRestantes()<1){                                                  //Si il n'a plus d'action
                System.out.println("Plus d'actions....");
                afficherInformation("Vous ne pouvez plus effectuer d'actions!");                    //On affiche cette information
            }
            else{                                                                                   //Sinon
                System.out.println("Déplacement impossible!");
                afficherInformation("Vous ne pouvez pas vous déplacer vers cette case!");           //On affiche le fait qu'il ne puisse pas se déplacer vers la tuile
            }
	}
        
        
        /**
	 * 
     * @param grille
     * @return 
	 */
	public HashMap deplacementPossibleListe(Grille grille) {
		// TODO - implement Controleur.deplacementPossible
                                
                HashMap<Coordonnees,Tuile> listeD = new HashMap<>();
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());                  //Coordonnées du joueur
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){    //Pour chaque tuile de la grille
                    if(i.getValue()!=null){                                             //Si la tuile n'est pas une tuile située dans les coins
                        xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());  //On note ses coordonnées
                        yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                        //Si elle est adjacente à la tuile de l'aventurier et elle n'est pas coulée
                        if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((! grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.COULEE)))){
                            listeD.put((Coordonnees) i.getKey(), i.getValue());  
                        }    
                    }
                        
                }

		return listeD;
                
	}
        
        public HashMap assechementPossibleListe(Grille grille) {
		// TODO - implement Controleur.assécher
		HashMap<Coordonnees,Tuile> listeD = new HashMap<>();   
                int xo,yo,xn,yn;
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
                    if(i.getValue()!=null){
                        xn=Integer.parseInt((String)(i.getKey()).getX());
                        yn=Integer.parseInt((String)(i.getKey()).getY());

                        if(((xo==xn && yo==yn)||(((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.INONDEE)))){
                            listeD.put( i.getKey(), i.getValue());   
                        }     
                    }
                }

		return listeD;


	}
        
        public void assecher(Coordonnees c,Grille grille) {         
		// TODO - implement Controleur.assécher
            if(assechementPossibleListe(grille).containsKey(c)){    //Si la tuile de coordonnée c est dans la liste assechementPossibleListe
                if(getActionsRestantes()>0){                            //Si l'aventurier a encore au moins une action
                    grille.getTuile(c).assechement();                   //Il asseche la tuile
                    setActionsRestantes(getActionsRestantes()-1);       //On lui retire une acion
                }
                else{                                                   //Sinon on l'informe qu'il n'a plus d'action
                    System.out.println("Plus d'actions....");
                    afficherInformation("Vous ne pouvez plus effectuer d'actions!");
                } 
            }  
            else{                                                   //Sinon on l'informe qu'il ne peut pas assecher la tuile
                System.out.println("Assechement non possible ici!");
                afficherInformation("Vous ne pouvez pas assécher cette case!");
            }
            
            /*if(getNom().equals("Ingenieur")){
                if(Utils.poserQuestion("Voulez-vous assécher une deuxième case?")){
                    System.out.println("Entrer les nouvelles coordonnées");
                    if(assechementPossibleListe(grille).containsKey(c)){
                        setActionsRestantes(getActionsRestantes()-1);
                    }  
                    else{
                        System.out.println("Assechement non possible ici!");
                        afficherInformation("Vous ne pouvez pas assécher cette case!");
                    }
                }
            }*/
	}

    /**
     * @return the nom
     */
    public NomAventurier getNom() {
        return NomAventurier.Aventurier;
    }


    /**
     * @return the mainCarteTrésor
     */
    public Collection<CarteTrésor> getMainCarteTrésor() {
        return mainCarteTrésor;
    }

    /**
     * @param mainCarteTrésor the mainCarteTrésor to set
     */
    public void setMainCarteTrésor(ArrayList<CarteTrésor> mainCarteTrésor) {
        this.mainCarteTrésor = mainCarteTrésor;
    }

    /**
     * @return the actionsRestantes
     */
    public int getActionsRestantes() {
        return actionsRestantes;
    }

    /**
     * @param actionsRestantes the actionsRestantes to set
     */
    public void setActionsRestantes(int actionsRestantes) {
        this.actionsRestantes = actionsRestantes;
    }

    public void resetActionsRestantes() {
        this.actionsRestantes = getMaxActions();
    }
    public void resetAutreA() {
        this.setAutreA(false);
    }
    /**
     * @param position the position to set
     */
    public void setPosition(Coordonnees position) {
        this.position = position;
    }

    /**
     * @return the autreA
     */
    public boolean isAutreA() {
        return autreA;
    }

    /**
     * @param autreA the autreA to set
     */
    public void setAutreA(boolean autreA) {
        this.autreA = autreA;
    }
    
    public boolean priseTresorPossible(NomTrésor tresor) {
		// TODO - implement Controleur.priseTresorPossible
                int nbCartes=0;
                CarteTrésorTrophée iBis;
                
                if ((getMainCarteTrésor().size()>4)&&(tresor!=null)){       
                    
                    for(CarteTrésor i :getMainCarteTrésor()){               
                        if((i.getTypeCarteTresor()).equals(TypeCarteTresor.Tresor)){
                            iBis = (CarteTrésorTrophée) i;
                            if(iBis.getNomT().equals(tresor)){
                               nbCartes = nbCartes +1;  
                            }
                        }
                    }
                    if(nbCartes>=1){
                        return true;
                    }
                }
                return false;                
	}
    public boolean prendreTresor(NomTrésor tresor) {
        // TODO - implement Controleur.prendreTresor

        if(priseTresorPossible(tresor)){
            getMainCarteTrésor().remove(tresor);
            return true;

        }
        else{
            return false;
        }
    }
        
    	/**
	 * 
	 * @param aven2
     * @param carte
	 */
    public void donnerCarte(Aventurier aven2,CarteTrésor carte) {
        
        int taille;
        
        if(this.mainCarteTrésor.contains(carte)){
            //On verifie que l'aventurier a la carte, si il a :
            
            //On ajoute la carte a l'aventurier 2
            aven2.getMainCarteTrésor().add(carte);
            
            //on recupere la taille de la main du joueur
            taille=this.getMainCarteTrésor().size();
            
            //on enlève la ou les cartes "carte" de sa main
            this.mainCarteTrésor.remove(carte);
            
            //on remet les cartes qu'on a enlevées en trop
            for(int i=(this.getMainCarteTrésor().size());i<taille;i++ ){
                this.mainCarteTrésor.add(carte);
            }
                
        }
    }
    public int getMaxActions(){
        return 3;
    }
    
}
