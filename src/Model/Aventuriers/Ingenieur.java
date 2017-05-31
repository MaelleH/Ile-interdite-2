package Model.Aventuriers;

public class Ingenieur extends Aventurier {
    public Ingenieur(String ing) {
        super.setNom(ing);
    }
    
    @Override
    public String getNom() {
            return "Ingenieur";
    }
    
    
    
    
}