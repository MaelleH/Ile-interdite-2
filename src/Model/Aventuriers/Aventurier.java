package Model.Aventuriers;

import Model.CarteTrésor;
import Model.Coordonnees;
import Model.Grille;
import Model.Tuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import static Util.Utils.EtatTuile.COULEE;
import static Util.Utils.EtatTuile.INONDEE;
import java.util.*;
import java.util.HashMap;

public class Aventurier {
	private Collection<CarteTrésor> mainCarteTrésor;
	private int actionsRestantes;
	private int ACTIONS_MAX = 3;
	private Coordonnees position= new Coordonnees("3","3");
       
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
            else if(this.getActionsRestantes()<0){
                System.out.println("Plus d'actions....");
            }
            else{
                System.out.println("Déplacement impossible!");
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
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&(((Tuile)i.getValue()).getEtat()!=COULEE)){
                        listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
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
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    xn=Integer.parseInt((String)((Coordonnees)i.getKey()).getX());
                    yn=Integer.parseInt((String)((Coordonnees)i.getKey()).getY());

                    if(((((xo==xn))&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1)))&&(((Tuile)i.getValue()).getEtat()==ASSECHEE)){
                        listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                        System.out.println(Integer.toString(xn)+Integer.toString(yn));
                        System.out.println(((Tuile)i.getValue()).getNomT());    
                    }        
                }

		return listeD;


	}
        
        public void assecher(Coordonnees c,Grille grille) {
		// TODO - implement Controleur.assécher
            Scanner sc=new Scanner(System.in);
            if(assechementPossibleListe(grille).containsKey(c)){
                if(getActionsRestantes()>0){
                    
                    setActionsRestantes(getActionsRestantes()-1);
                }
                else{
                    System.out.println("Plus d'actions....");
                } 
            }  
            else{
                System.out.println("Assechement non possible ici!");
            }
            
            if(getNom().equals("Navigateur")){
                System.out.println("Voulez vous assécher une deuxième case? oui/non");
                if(sc.nextLine()=="oui"){
                    System.out.println("Entrer les nouvelles coordonnées");
                    if(assechementPossibleListe(grille).containsKey(c)){
                        setActionsRestantes(getActionsRestantes()-1);
                    }  
                else{
                    System.out.println("Assechement non possible ici!");
                }
                }
                
            
            }
	}

    /**
     * @return the nom
     */
    public String getNom() {
        return "Aventurier";
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
    public void setMainCarteTrésor(Collection<CarteTrésor> mainCarteTrésor) {
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

    /**
     * @param position the position to set
     */
    public void setPosition(Coordonnees position) {
        this.position = position;
    }
}
