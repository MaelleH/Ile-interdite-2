package Model;

public class Tuile {

	private NomTuile nomT;
	/**
	 * Formé avec le num colonne +ligne ( exemple 11 ou 15)
	 */
	private String numT;
	/**
	 * 0 = Sec
	 * 1 = Innondé
	 * 2 = Dead
	 */
	private int etat = 0;
	private String tresor;

	public String getNumT() {
		return this.numT;
	}

	public void setNumT(String numT) {
		this.numT = numT;
	}

	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

}