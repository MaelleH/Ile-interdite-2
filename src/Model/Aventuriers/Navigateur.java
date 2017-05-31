package Model.Aventuriers;

public class Navigateur extends Aventurier {
        @Override
        public String getNom() {
            return "Navigateur";
        }
    public Navigateur(String navig) {
        super.setNom(navig);
    }
}
