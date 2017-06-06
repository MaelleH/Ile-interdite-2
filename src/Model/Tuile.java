package Model;

import Util.Utils;


public class Tuile {

	private Utils.EtatTuile etat;
	private String tresor;
	private NomTuile nomT;
        
        public Tuile (NomTuile nom,String tresor){
            this.nomT=nom;
            this.tresor=tresor;
            this.etat=Utils.EtatTuile.ASSECHEE;

        }
        
        public int hashMap(){
          return nomT.hashCode();
        }
        
        public Tuile (NomTuile nom){
            this.nomT=nom;
            this.tresor="vide";
            this.etat=Utils.EtatTuile.ASSECHEE;
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
