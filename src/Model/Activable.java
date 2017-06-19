package Model;

public class Activable extends CarteTrésor {
    private String desc;
    private String uti;
    public Activable() {
        this.setUti("• A jouer à tout moment \n • Ne compte pas pour une action \n • A défauser avec les cartes trésor");
    }
        /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the uti
     */
    public String getUti() {
        return uti;
    }

    /**
     * @param uti the uti to set
     */
    public void setUti(String uti) {
        this.uti = uti;
    }
     
}