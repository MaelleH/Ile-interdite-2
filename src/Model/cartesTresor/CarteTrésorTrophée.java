
package Model.cartesTresor;

import Util.NomTrésor;
import Util.TypeCarteTresor;

public class CarteTrésorTrophée extends CarteTrésor{
    private NomTrésor nomT;
    
    public CarteTrésorTrophée(NomTrésor nomT) {
        this.setNomT(nomT);
    }

    private void setNomT(NomTrésor nomT) {
       this.nomT = nomT;
    }
    
    @Override
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Tresor;
    }  

    public NomTrésor getNomT() {
        return nomT;
    }

    public boolean equals(CarteTrésor c) {
        if(super.equals(c)){
            return this.getNomT().toString().equals(((CarteTrésorTrophée)c).getNomT());
        }else{
            return false;
        }
    }

    
    
}
