import Vue.VueAventurier;
import Model.CarteInondation;
import Model.Grille;
import Model.Aventuriers.Aventurier;
import Model.Tuile;
import Model.CarteTrésor;
import java.util.*;

public class Controleur {

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
	 * @param aventurier
	 * @param tuile
	 */
	public void deplacement(Aventurier aventurier, Tuile tuile) {
		// TODO - implement Controleur.deplacement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 * @param tuile
	 */
	public boolean deplacementPossible(Aventurier aventurier, Tuile tuile) {
		// TODO - implement Controleur.deplacementPossible
		
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

}