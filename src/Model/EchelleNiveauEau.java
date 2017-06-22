package Model;

public class EchelleNiveauEau {

    private int niveauEau;
	
    public EchelleNiveauEau (int nivEau){
        this.niveauEau = nivEau;
    }
    
    public int getNiveauEau() {
        return niveauEau;
    }
    
    //change l'echelle de niveau d'eau si on pioche une montée des eaux
    public void monteeDesEaux() {   
        this.niveauEau = getNiveauEau() + 1;        //On augmente le niveau d'eau de 1    
        if(getNiveauEau()==10){                     //si la niveau d'eau atteint 10, la partie est perdue
            System.out.println("Fin de Partie");
        }
    }
    
    //retourne le nombre de cartes innondation à piocher en fin de tour
    public int getNbInond() {
        int nbI=5;
        if(getNiveauEau()<=2){
            nbI = 2;
        }else if(getNiveauEau()>=3 && getNiveauEau()<=5){
            nbI = 3;
        }else if(getNiveauEau()>=6 && getNiveauEau()<=7){
            nbI = 4;
        }else if(getNiveauEau()>=8 && getNiveauEau()<=9){
            nbI = 5;
        }
        return nbI;
    }
}
 