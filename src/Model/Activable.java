package Model;

public abstract class Activable extends CarteTrésor {
    @Override
    public TypeCarteTresor getTypeCarteTresor() {
        return TypeCarteTresor.Activable;
    }
     
    
    public abstract TypeCarteActivable getTypeCarteActivable();
    
    public boolean equals(CarteTrésor c) {
        if(super.equals(c)){
            return this.getTypeCarteActivable().toString().equals(((Activable)c).getTypeCarteActivable());
        }else{
            return false;
        }
    }
    
}