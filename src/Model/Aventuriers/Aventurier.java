package Model.Aventuriers;

import Model.CarteTrésor;
import Model.Tuile;
import java.util.*;

public class Aventurier {

	private Collection<CarteTrésor> mainCarteTrésor;
	private Tuile position;
	private int actionsRestantes;
	private int ACTIONS_MAX = 3;

	/**
	 * 
	 * @param tuile
	 */
	public void deplacer(Tuile tuile) {
		// TODO - implement Aventurier.deplacer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aven1
	 * @param aven2
	 */
	public void donnerCarte(Aventurier aven1, Aventurier aven2) {
		// TODO - implement Aventurier.donnerCarte
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param aventurier
	 */
	public void prendreTresor(Aventurier aventurier) {
		// TODO - implement Aventurier.prendreTresor
		throw new UnsupportedOperationException();
	}

	public void piocherTresor() {
		// TODO - implement Aventurier.piocherTresor
		throw new UnsupportedOperationException();
	}

	public void piocherInnondation() {
		// TODO - implement Aventurier.piocherInnondation
		throw new UnsupportedOperationException();
	}

	public void piochePossible() {
		// TODO - implement Aventurier.piochePossible
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tuile
	 */
	public void setPosition(Tuile tuile) {
		this.position = tuile;
	}

	public Tuile getPosition() {
		return this.position;
	}

}