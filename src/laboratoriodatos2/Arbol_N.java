/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriodatos2;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Arbol_N {
    
   ArrayList <Arbol_N> Hijos = new ArrayList();
   String Dominio;
   
   Arbol_N(String URL){
       this.Dominio = URL;
   }

    public Arbol_N getHijos(int x) {
        return Hijos.get(x);
    }

    public void setHijos(String URL) {
        Arbol_N ab = new Arbol_N(URL);
        this.Hijos.add(ab);
    }

    public String getDominio() {
        return Dominio;
    }

    public void setDominio(String Dominio) {
        this.Dominio = Dominio;
    }
}
