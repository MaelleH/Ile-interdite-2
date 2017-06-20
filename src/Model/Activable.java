package Model;

public class Activable extends CarteTrésor {
    @Override
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Activable;
    }
     
    
    public TypeCarteActivable getTypeCarteActivable(){
        return TypeCarteActivable.undefined;
    }
}