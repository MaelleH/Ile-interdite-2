package Model.Aventuriers;

import Util.Coordonnees;
import Util.Utils;

public class Messager extends Aventurier {
    public Messager(Coordonnees position) {
        super(position);
    }
        
    @Override
    public Utils.NomAventurier getNom() {
        return Utils.NomAventurier.Messager;
    }
    
    //permet au messagerde toujours pouvoir donner des cartes
    @Override
    public boolean donnerCartePossible( Aventurier aven2) {
         return true;
    }
}
