package ile.interdite;


import Model.Coordonnees;
import Vue.VueAventurier;
import Model.CarteInondation;
import Model.Grille;
import Model.Aventuriers.Aventurier;
import Model.Tuile;
import Model.CarteTrésor;
import java.util.*;

public class Controleur implements Observateur {

	Collection<CarteTrésor> piocheCarteTrésor;
	private Grille grille;
	private Collection<Aventurier> joueurs;
	private VueAventurier vueAventurier;
	private Collection<CarteTrésor> defausseCarteTrésor;
	private Collection<CarteInondation> piocheCarteInondation;
	private Collection<CarteInondation> défausseCarteInondation;
	private Collection<CarteInondation> défausseCarteCoulées;

	/**
	 * 
	 * @param a
         * @param c

	 */
	public HashMap deplacement(Aventurier a, Coordonnees c) {
		// TODO - implement Controleur.deplacement
              throw new UnsupportedOperationException();  
	}

	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public HashMap deplacementPossible(Aventurier a) {
		// TODO - implement Controleur.deplacementPossible
                                
                HashMap<Coordonnees,Tuile> listeE = new HashMap<>();   
		Coordonnees p;
                p = a.getPosition();
                
                for(Map.Entry i: grille.getHSTuile().entrySet()){
                    if(a.deplacementPossible(p, (Coordonnees) i.getKey(),grille)){
                        listeE.put((Coordonnees) i.getKey(),(Tuile) i.getValue());
                    }        
                }

		return listeE;
	}

	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public boolean assèchementPossible(Aventurier aventurier, Tuile tuile) {
		// TODO - implement Controleur.assèchementPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public void assécher(Aventurier aventurier, Tuile tuile) {
		// TODO - implement Controleur.assécher
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public void donnerCarte(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Controleur.donnerCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public boolean donnationPossible(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Controleur.donnationPossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 */
	public void prendreTresor(Aventurier aventurier) {
		// TODO - implement Controleur.prendreTresor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 */
	public void priseTresorPossible(Aventurier aventurier) {
		// TODO - implement Controleur.priseTresorPossible
		throw new UnsupportedOperationException();
	}

        
        public void traiterMessage(Message m) {
            //A Faire 
            if(m.getBtnCliquéTxt().equals("Aller")){
                System.out.println("Déplacement! (" + m.getChampSaisieTxt().charAt(0)+","+m.getChampSaisieTxt().charAt(1)+")");
            }else if (m.getBtnCliquéTxt().equals("Assecher")){
                System.out.println("Assècher! (" + m.getChampSaisieTxt().charAt(0)+","+m.getChampSaisieTxt().charAt(1)+")");
            }else if (m.getBtnCliquéTxt().equals("AutreAction")){
                System.out.println("Autre Action! (" + m.getChampSaisieTxt().charAt(0)+","+m.getChampSaisieTxt().charAt(1)+")");
            }else if (m.getBtnCliquéTxt().equals("Terminer Tour")){
                System.out.println("Fin du Tour! (" + m.getChampSaisieTxt().charAt(0)+","+m.getChampSaisieTxt().charAt(1)+")");
            }
            
        }
}
