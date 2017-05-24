import Model.Coordonnees;
import java.util.*;

public class Grille {

        private HashMap<Coordonnees,Tuile> HSTuile;

    /**
     * @return the HSTuile
     */
    public HashMap<Coordonnees,Tuile> getHSTuile() {
        return HSTuile;
    }

    /**
     * @param HSTuile the HSTuile to set
     */
    public void setHSTuile(HashMap<Coordonnees,Tuile> HSTuile) {
        this.HSTuile = HSTuile;
    }


}