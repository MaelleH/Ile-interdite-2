/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 *
 * @author heyrendm
 */
public enum Images {
    
    heli("/Vue/Imagewin/heli.gif"),
    goutte("/Vue/Imagewin/goutte.png"),
    interro("/Vue/Imagewin/interro.png"),
    carte("/Vue/Imagewin/carte.png"),
    back("/Vue/Imagewin/back.png")
    ;
    
    
    
    private String c;
    Images(String c){
        this.c=c;
    }
    
    public String getChemin(){
        return c;
    }
    
    

}
