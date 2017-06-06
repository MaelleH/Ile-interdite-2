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

        public boolean deplacementPossible(Coordonnees n,Grille g) {
		// TODO - implement Controleur.deplacementPossible
                int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(getPosition().getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(getPosition().getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn)&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))){
                    
                    if(g.getTuile(n)!= null){
                       //if(g.getHSTuile().get(n).getEtat()!=COULEE){
                            bool=true;
                        //} 
                    }

                             
                }
                return bool;
                

		
	}
                
	/**
	 * 
	 * @param a
     * @param grille
         * @param c

	 */
	public void deplacement(Coordonnees c,Grille grille ) {
            if(this.getActionsRestantes()>0&&deplacementPossible(c,grille)){
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
		Coordonnees p;
                p = getPosition();
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    if(deplacementPossible((Coordonnees) i.getKey(),grille)){
                        listeD.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                        
                    }        
                }

		return listeD;
                
	}

	/**
	 * 
     * @param o
     * @param n
	 * @param aventurier
     * @param g
     * @return 
	 */
	public boolean assechementPossible(Coordonnees o,Coordonnees n,Grille g) {
		// TODO - implement Controleur.assèchementPossible
		int xo,yo,xn,yn;
                boolean bool=false;
                
                xo=Integer.parseInt(o.getX());
                xn=Integer.parseInt(n.getX());
                yo=Integer.parseInt(o.getY());
                yn=Integer.parseInt(n.getY());
                
                if(((xo==xn)&&(yo==yn-1||yo==yn+1))||((yo==yn)&&(xo==xn-1||xo==xn+1))){
                    if(g.getHSTuile().get(n).getEtat()==INONDEE){
                        bool=true;
                    }     
                }
                return bool;
	}
        
        public HashMap assechementPossibleListe(Grille grille) {
		// TODO - implement Controleur.assécher
		HashMap<Coordonnees,Tuile> listeA = new HashMap<>();   
		Coordonnees p;
                p = getPosition();
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    if(assechementPossible(p, (Coordonnees) i.getKey(),grille)){
                        listeA.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                    }        
                }
                return listeA;


	}
        
        public void assecher(Coordonnees c,Grille grille) {
		// TODO - implement Controleur.assécher
            Scanner sc=new Scanner(System.in);
            if(assechementPossible((getPosition()), c, grille)){
                if(getActionsRestantes()>0){
                    for(Map.Entry i: grille.getHSTuile().entrySet()){
                        if((Coordonnees) i.getKey()==c){
                            ((Tuile)i.getValue()).setEtat(ASSECHEE);
                        }        
                    }
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
                    if(assechementPossible((getPosition()), c, grille)){
                        for(Map.Entry i: grille.getHSTuile().entrySet()){
                            if((Coordonnees) i.getKey()==c){
                                ((Tuile)i.getValue()).setEtat(ASSECHEE);
                            }        
                        }
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
