package Model.cartesTresor;

import Util.TypeCarteActivable;
import Util.TypeCarteTresor;

public abstract class Activable extends CarteTrésor {
    
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Activable;
    }
     
    
    public abstract TypeCarteActivable getTypeCarteActivable();
    
    @Override
    public boolean equals(CarteTrésor c) {
        if(super.equals(c)){
            return this.getTypeCarteActivable().toString().equals(((Activable)c).getTypeCarteActivable());
        }else{
            return false;
        }
    }
    
}