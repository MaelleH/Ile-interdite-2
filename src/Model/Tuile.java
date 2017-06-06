package Model;

import Util.Utils;


public class Tuile {

	private Utils.EtatTuile etat;
	private String tresor;
	private NomTuile nomT;
        
        public Tuile (NomTuile nom,String tresor, Utils.EtatTuile etat){
            this.nomT=nom;
            this.tresor=tresor;
            this.etat=etat;
        }
        public Tuile (NomTuile nom, Utils.EtatTuile etat){
            this.nomT=nom;
            this.etat=etat;
        }
        
        
	public Utils.EtatTuile getEtat() {
		return this.etat;
	}

	public void setEtat(Utils.EtatTuile etat) {
		this.etat = etat;
	}

	public NomTuile getNomT() {
		return this.nomT;
	}

    /**
     * @return the tresor
     */
    public String getTresor() {
        return tresor;
    }

    /**
     * @param tresor the tresor to set
     */
    public void setTresor(String tresor) {
        this.tresor = tresor;
    }

    /**
     * @param nomT the nomT to set
     */
    public void setNomT(NomTuile nomT) {
        this.nomT = nomT;
    }

}
