package laboratoriodatos2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import static laboratoriodatos2.Arbol_N.contador;
import static laboratoriodatos2.Interfaz.ArbolitoTree;
import static laboratoriodatos2.webCrawler.Arbol_n_ario;

//clase auxiliar
public class webCrawler {

    public static Arbol_N Arbol_n_ario;

    webCrawler(Arbol_N ArbolitoLocal) {
        Arbol_n_ario = new Arbol_N(ArbolitoLocal.getDominio());
        try {
            URL my_url = new URL(ArbolitoLocal.getDominio());

            rastrear(my_url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("" + Arbol_n_ario.getHijos().size());
    }

    public Arbol_N LLenarRecursivo(Arbol_N Receptor) {
        webCrawler wc;
        for (Arbol_N HijosLlenables : Receptor.getHijos()) {
            if (contador < 100) {

                Arbol_n_ario = null;
                wc = new webCrawler(HijosLlenables);
                for (Arbol_N hijo : Arbol_n_ario.getHijos()) {
                    if (ArbolitoTree.VerificarSiExiste(hijo.getDominio())) {
                        HijosLlenables.getHijos().add(hijo);
                    }
                }
            } else {
                break;
            }
        }

        return Receptor;
    }

    public void rastrear(URL url) {
        try {
            boolean esValida;
            try {
                URLConnection connection = url.openConnection();
                connection.connect();
                if ((connection.getContentType() != null) && !connection.getContentType().toLowerCase().startsWith("text/")) {
                    esValida = false;
                } else {
                    esValida = true;
                }
            } catch (IOException e) {
                esValida = false;
            }

            if (esValida) {
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                HTMLEditorKit.Parser parse = new HTMLParse().getParser();
                //escribir enlaces de una página
                parse.parse(br, new Parser(url), true);

            } else {
                System.out.println("No es una página válida");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

class HTMLParse extends HTMLEditorKit {

    public HTMLEditorKit.Parser getParser() {
        return super.getParser();
    }
}
//clase auxiliar

class Parser extends HTMLEditorKit.ParserCallback {

    protected URL url;

    public Parser(URL base) {
        this.url = base;
    }

    public void EtiquetaSimple(HTML.Tag t, MutableAttributeSet a, int pos) {
        //obtener la propiedad href de la etiqueta <a> que identifica que hay un enlace en la página
        String href = (String) a.getAttribute(HTML.Attribute.HREF);
        if ((href == null) && (t == HTML.Tag.FRAME)) {
            href = (String) a.getAttribute(HTML.Attribute.SRC);
        }

        if (href == null) {
            return;
        }

        int i = href.indexOf('#');
        if (i != -1) {
            href = href.substring(0, i);
        }
        CreacionDeArbol(url, href, Arbol_n_ario);
        //mostrarLink(url,href);
    }

    public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
        EtiquetaSimple(t, a, pos);
    }

    public void mostrarLink(URL dominio, String url) {
        if (!url.equals("") && !url.equals(" ") && !url.equals("/")) {
            if (url.substring(0, 8).equals("https://") || url.substring(0, 7).equals("http://")) {
                System.out.println(url);
            } else {
                System.out.println(dominio + url);
            }
        }
    }

    public void LlenarTodoElArbol() {
    }

    public void CreacionDeArbol(URL dominio, String url, Arbol_N Arbol) {
        boolean verificadorlocal = false;
        if (!url.equals("") && !url.equals(" ") && !url.equals("/")) {
            if (contador < 100) {
                if (url.substring(0, 8).equals("https://") || url.substring(0, 7).equals("http://")) {
                    verificadorlocal = Arbol.VerificarSiExiste(url);
                    if (verificadorlocal == false) {
                        Arbol.InsertarEnArbol(url);
                    }
                } else {
                    verificadorlocal = Arbol.VerificarSiExiste(dominio + url);
                    if (verificadorlocal == false) {
                        Arbol.InsertarEnArbol(dominio + url);
                    }
                }
            }
        }
    }
}
