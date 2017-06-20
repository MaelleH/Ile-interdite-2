/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.TypeCarteActivable;
import Model.TypeCarteTresor;

/**
 *
 * @author ferreijo
 */
public class PanelCarteActivable extends PanelCarteTresor{
    private TypeCarteActivable typeActivable;
    public PanelCarteActivable(TypeCarteActivable typeActivable){
        super(TypeCarteTresor.Activable);
        this.typeActivable=typeActivable;
    }
    
}
