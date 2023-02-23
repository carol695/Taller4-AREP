package edu.escuelaing.arem.app.webApps;


import edu.escuelaing.arem.app.HttpServer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class FirstApp {
    /**
     * Método principal donde se genera cualquier servicio
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        HttpServer server = HttpServer.getInstance();
       /* server.addServices("/index", new ServiceHtml());
        server.addServices("/estilos", new ServiceCSS());
        server.addServices("/javaScript", new ServiceJS());
        server.addServices("/imagen", new ServicePNG());*/
        server.run(args);
    }
}
