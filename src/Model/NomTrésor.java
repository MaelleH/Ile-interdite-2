/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author heyrendm
 */
public enum NomTrésor {
    	Calice("Calice"),
        Cristal ("Cristal"), 
        Zéphyr("Zephyr"),
        Pierre("Pierre"),
        Vide("vide"); 


        private String nomT = "";
        NomTrésor(String nomT){
               this.nomT=nomT;
        }

        @Override
        public String toString(){
           return nomT;
        }
}
