package Model;

public abstract class Activable extends CarteTrésor {
    @Override
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Activable;
    }
     
    
    public abstract TypeCarteActivable getTypeCarteActivable();
}