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
    public void monteeDesEaux() {   //augmentation du niveau d'inondation
        this.niveauEau = getNiveauEau() + 1;
        
        if(getNiveauEau()==10){
            System.out.println("Fin de Partie");
        }
    }
    //retourne le nombre de carte innondation à piocher en fin de tour/montée des eaux
    public int getNbInond() {       
        int nbI=5;
        if(getNiveauEau()<=2){
            nbI = 1;
        }else if(getNiveauEau()>=3 && getNiveauEau()<=5){
            nbI = 2;
        }
        else if(getNiveauEau()>=6 && getNiveauEau()<=7){
            nbI = 3;
        }
        else if(getNiveauEau()>=8 && getNiveauEau()<=9){
            nbI = 4;
        }
        return nbI;
    }
}
 