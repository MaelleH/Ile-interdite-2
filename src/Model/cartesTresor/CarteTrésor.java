package Model.cartesTresor;

import Util.NomTuile;
import Util.TypeCarteTresor;

public abstract class CarteTrésor {
    public abstract TypeCarteTresor getTypeCarteTresor();
    
    public boolean equals(CarteTrésor c) {
        return this.getTypeCarteTresor().toString().equals((c.getTypeCarteTresor()));
    }
}