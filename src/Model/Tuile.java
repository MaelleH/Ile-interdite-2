
import Model.NomTuile;

public class Tuile {

	/**
	 * 0 = Sec
	 * 1 = Innondé
	 * 2 = Dead
	 */
	private int etat = 0;
	private String tresor;
	private NomTuile nomT;
        
        public Tuile (NomTuile nom,String tresor){
            this.nomT=nom;
            this.tresor=tresor;
        }
        public Tuile (NomTuile nom){
            this.nomT=nom;
        }
        
        
	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public NomTuile getNomT() {
		return this.nomT;
	}

}