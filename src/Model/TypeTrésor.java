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
public enum TypeTrésor {
    	Calice("Calice"),
        Crystal ("crystal"), 
        Zéphir("Zéphir"),
        Pierre("Pierre"); 


        private String nomT = "";
        TypeTrésor(String nomT){
               this.nomT=nomT;
        }

        @Override
        public String toString(){
           return nomT;
        }
}
