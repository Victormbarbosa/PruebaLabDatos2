/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratoriodatos2;

import java.util.ArrayList;
import static laboratoriodatos2.Interfaz.ArbolitoTree;

/**
 *
 * @author Usuario
 */
public class Nodo {

    ArrayList<Nodo> Hijos = new ArrayList();
    String Dominio = null;
    static int contador = 0;
    String DominioPuro = null;

    Nodo(String URL) {
        this.Dominio = URL;
        if (URL.substring(0, 8).equals("https://")) {
            this.DominioPuro = URL.substring(8, URL.length());
        } else {
            this.DominioPuro = URL.substring(7, URL.length());
        }
    }

    public ArrayList<Nodo> getHijos() {
        return Hijos;
    }

    public void setHijos(ArrayList<Nodo> Hijos) {
        this.Hijos = Hijos;
    }

    public Nodo LlenarSegundoNivel() {

        return null;
    }

    public String getDominio() {
        return Dominio;
    }

    public void setDominio(String Dominio) {
        this.Dominio = Dominio;
    }

    public Nodo InsertarEnArbol(String x) {
        Nodo ab = new Nodo(x);
        String[] componentes = ab.Dominio.split(this.Dominio);
        
        if (componentes.length>1) {
            contador++;
            this.Hijos.add(ab);
        }
        return this;

    }

    public boolean VerificarSiExiste(String x) {
        if (Dominio.equals(x) || (Dominio + "/").equals(x) || (Dominio + ".html").equals(x)) {
            return true;
        } else {
            return RecursivoBuscador(this.getHijos(), x);
        }
    }

    public boolean RecursivoBuscador(ArrayList<Nodo> Hijos, String x) {
        if (Hijos.size() > 0) {
            boolean check = false;
            for (Nodo arbol_N : Hijos) {
                if (arbol_N.getDominio().equals(x) || (arbol_N.getDominio() + "/").equals(x) || (arbol_N.getDominio() + ".html").equals(x)) {
                    return true;
                } else {
                    if (arbol_N.getHijos().size() > 0) {
                        check = RecursivoBuscador(arbol_N.getHijos(), x);
                        if (check == true) {
                            return check;
                        }
                    }
                }
            }
            return false;
        } else {
            return false;
        }
    }

}
