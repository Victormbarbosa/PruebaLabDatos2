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
public class Arbol_N {

    ArrayList<Arbol_N> Hijos = new ArrayList();
    String Dominio = null;
    static int contador = 0;
    int numero = 0;

    Arbol_N(String URL) {
        this.Dominio = URL;
        numero = contador++;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Arbol_N> getHijos() {
        return Hijos;
    }

    public void setHijos(ArrayList<Arbol_N> Hijos) {
        this.Hijos = Hijos;
    }

    public Arbol_N LlenarSegundoNivel() {

        return null;
    }

    public String getDominio() {
        return Dominio;
    }

    public void setDominio(String Dominio) {
        this.Dominio = Dominio;
    }

    public Arbol_N InsertarEnArbol(String x) {
        Arbol_N ab = new Arbol_N(x);
        if (ab.getNumero() <= 100) {
            this.Hijos.add(ab);
        }
        return this;
    }

    public boolean VerificarSiExiste(String x) {
        if (Dominio.equals(x) || (Dominio + "/").equals(x)) {
            return true;
        } else {
            if (ArbolitoTree.getDominio().equals(this.Dominio)) {
                return RecursivoBuscador(this.getHijos(), x);
            } else {
                boolean comprobador;
                comprobador = RecursivoBuscador(ArbolitoTree.getHijos(), x);
                if (comprobador == false) {
                    return RecursivoBuscador(this.getHijos(), x);
                }else{
                    return comprobador;
                }
                
            }
        }
    }

    public boolean RecursivoBuscador(ArrayList<Arbol_N> Hijos, String x) {
        if (Hijos.size() > 0) {
            boolean check = false;
            for (Arbol_N arbol_N : Hijos) {
                if (arbol_N.getDominio().equals(x)) {
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
