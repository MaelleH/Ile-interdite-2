package Model.Aventuriers;

import Model.CarteTrésor;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import Model.TypeTrésor;
import Util.Utils;
import Util.Utils.NomAventurier;
import static Util.Utils.afficherInformation;
import java.util.*;
import java.util.HashMap;

public class Aventurier {
	private ArrayList<CarteTrésor> mainCarteTrésor = new ArrayList<>();
	private int actionsRestantes;
	private int ACTIONS_MAX = 3;
	private Coordonnees position= new Coordonnees("3","3");
        private boolean autreA=false;
       
    public Aventurier(){
        this.actionsRestantes=ACTIONS_MAX;
    }
    public Aventurier(Coordonnees position) {
        this.position=position;
        this.actionsRestantes=ACTIONS_MAX;
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

            System.out.println("Position    aventurier" +getPosition().getX()+getPosition().getY());
            if(this.getActionsRestantes()>0&&deplacementPossibleListe(grille).containsKey(c)){
		setPosition(c);
                setActionsRestantes(getActionsRestantes()-1);
                System.out.println(getPosition().getX()+getPosition().getY());
                
            }
            else if(this.getActionsRestantes()<1){
                System.out.println("Plus d'actions....");
                afficherInformation("Vous ne pouvez plus effectuer d'actions!");
            }
            else{
                System.out.println("Déplacement impossible!");
                afficherInformation("Vous ne pouvez pas vous déplacer vers cette case!");
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
                
                xo=Integer.parseInt(getPosition().getX());
                yo=Integer.parseInt(getPosition().getY());
                
                for(Map.Entry<Coordonnees,Tuile> i: grille.getHSTuile().entrySet()){
                    if(i.getValue()!=null){
                        xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                        yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    
                        if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&((! grille.getTuile(i.getKey()).getEtat().equals(Utils.EtatTuile.COULEE)))){
                            listeD.put((Coordonnees) i.getKey(), i.getValue());
                            System.out.println(Integer.toString(xn)+Integer.toString(yn));
                            System.out.println(((Tuile)i.getValue()).getNomT());    
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
                            System.out.println(Integer.toString(xn)+Integer.toString(yn));
                            System.out.println((i.getValue()).getNomT());    
                        }     
                    }
                }

		return listeD;


	}
        
        public void assecher(Coordonnees c,Grille grille) {
		// TODO - implement Controleur.assécher
            if(assechementPossibleListe(grille).containsKey(c)){
                if(getActionsRestantes()>0){
                    grille.getTuile(c).assechement();
                    setActionsRestantes(getActionsRestantes()-1);
                }
                else{
                    System.out.println("Plus d'actions....");
                    afficherInformation("Vous ne pouvez plus effectuer d'actions!");
                } 
            }  
            else{
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
        this.actionsRestantes = ACTIONS_MAX;
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
    
    public boolean priseTresorPossible(TypeTrésor tresor) {
		// TODO - implement Controleur.priseTresorPossible
                int stop=0;
                
                if ((getMainCarteTrésor().size()>4)&&(tresor!=null)){
                    
                    for(CarteTrésor i :getMainCarteTrésor()){
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
    public boolean prendreTresor(TypeTrésor tresor) {
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
}
