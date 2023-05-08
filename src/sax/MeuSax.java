/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax;

import main.Errores;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.Model;

/**
 *
 * @author mascport
 */
public class MeuSax {

    private String fic;
    private Model modelo;

    public MeuSax(String f, Model m) {
        fic = f;
        modelo = m;
    }

    public void llegir() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream xmlInput
                    = new FileInputStream(fic);
            SAXParser saxParser = factory.newSAXParser();
            MeuHandler handler = new MeuHandler(modelo);
            saxParser.parse(xmlInput, handler);
        } catch (Exception e) {
            Errores.informaError(e);
        }
    }
}
