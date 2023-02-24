package edu.escuelaing.arem.app;

import edu.escuelaing.arem.app.controller.RequestMapping;
import edu.escuelaing.arem.app.controller.SpringBoot;
import org.json.JSONTokener;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class HttpServer {
    private static  HttpServer _instance = new HttpServer();

    private HashMap<String, Method> methods = new HashMap<>();

    private OutputStream outputStream;

    public void run(String[] args) throws IOException, ClassNotFoundException {

        SpringBoot spring = new SpringBoot();
        ArrayList<String> classes = spring.getClassComponent(new ArrayList<>(), ".");
        System.out.println(classes);
        for (String className: classes) {
            Class c = Class.forName(className);
            for (Method m: c.getMethods()) {
                if (m.isAnnotationPresent(RequestMapping.class)){
                    methods.put(m.getAnnotation(RequestMapping.class).value(), m);
                    System.out.println("--------");
                }
            }
        }
        System.out.println("Methods: " + methods);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while(running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
            String inputLine, outputLine, request = "/simple", typeMethod = "GET";
            Boolean boolFirstLine = true;
            outputStream = clientSocket.getOutputStream();

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (boolFirstLine) {
                    request = inputLine.split(" ")[1];
                    typeMethod = inputLine.split(" ")[0];
                    boolFirstLine = false;
                }
                if (!in.ready()) {
                    break;
                }
            }
            if (Objects.equals(typeMethod, "GET")) {
                try {
                    if (methods.containsKey(request)) {
                        outputLine = (String) methods.get(request).invoke(null);
                    } else {
                        System.out.println(methods);
                        outputLine = (String) methods.get("404").invoke(null);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } else {
                outputLine = "HTTP/1.1 200 OK\r\n" +
                        "Content-type: text/html\r\n" +
                        "\r\n" +
                        "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>404</title>\n"
                        + "</head>"
                        + "<body>"
                        + "Use metodos GET"
                        + "</body>"
                        + "</html>";
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * method that returns the server instance
     * @return Instance Server
     */
    public static HttpServer getInstance() {
        return _instance;
    }

    /**
     * Retorn outoutStream
     * @return OutputStream
     */
    public OutputStream getOutputStream() {
        return this.outputStream;
    }

}



















