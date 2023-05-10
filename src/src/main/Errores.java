package main;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class Errores {
    static public void informaError(Exception ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        String s = writer.toString();
        System.err.println("Se ha producido un error. El error es:\n" + s);
    }
}
