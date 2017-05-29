package Model;

public class EchelleNiveauEau {

	
	private int niveauEau;
        private int nbInnond ;

	public int getNbCartesInondation() {
		// TODO - implement EchelleNiveauEau.getNbCartesInondation
		throw new UnsupportedOperationException();
	}
        public EchelleNiveauEau (int nivEau){
            setNiveauEau(nivEau);
        }
    public int getNiveauEau() {
        return niveauEau;
    }

    public void setNiveauEau(int niveauEau) {
        if (niveauEau==10){
            // fin de partie pour niveau de l'eau à 10
            System.out.print("partieFinie");
        }
        else {
            if (niveauEau<2){
                setNiveauEau(niveauEau);
                setNbInnond(2);
            }
            else if (niveauEau>2 && niveauEau<6){
                setNiveauEau(niveauEau);
                setNbInnond(3);
            }
            else if (niveauEau>5 && niveauEau<8){
                setNiveauEau(niveauEau);
                setNbInnond(4);
            }
            else {
                setNiveauEau(niveauEau);
                setNbInnond(5);
            }
        }
        
        this.niveauEau = niveauEau;
    }

    public int getNbInnond() {
        return nbInnond;
    }

    public void setNbInnond(int nbInnond) {
        this.nbInnond = nbInnond;
    }
            
 }
