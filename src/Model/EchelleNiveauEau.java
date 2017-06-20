package Model;

public class EchelleNiveauEau {

	
    private int niveauEau;

	
    public EchelleNiveauEau (int nivEau){
        this.niveauEau = nivEau;
    }
    
    public int getNiveauEau() {
        return niveauEau;
    }

    public void monteeDesEaux() {   //augmentation du niveau d'inondation
        this.niveauEau = getNiveauEau() + 1;
        
        if(getNiveauEau()==10){
            System.out.println("Fin de Partie");
        }
    }

    public int getNbInond() {       //nombre de cartes inondation à piocher en fonction du niveau d'inondation
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
 