package edu.escuelaing.arem.app;

import edu.escuelaing.arem.app.controller.RequestMapping;
import edu.escuelaing.arem.app.services.RESTService;
import edu.escuelaing.arem.app.spark.Request;
import edu.escuelaing.arem.app.spark.Spark;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class httpServer {
    private static final String url = "http://www.omdbapi.com/?t=";
    private static final String key = "&apikey=2c402a46";

    private static httpServer instance = new httpServer();

    private static Map<String, RESTService> services = new HashMap<>();
    private static HashMap<String, Method> mapRequest = new HashMap<>();

    OutputStream outputStream = null;

    public static httpServer getInstance(){
        return instance;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }




     /**
      * Método principal, inicia un socket
      * recibe la petición get y agrega el nombre a de la
      * película seleccionada a la URL de la API
      *
      * @param args
      * @return
      * @throws IOException
      */
    public void run(String[] args) throws IOException, JSONException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        String className = args[0];
        int passed = 0, failed = 0;
        //Cargar clase con formato

        Class c = Class.forName(className);
        Method[] methods = c.getMethods();
        //Extraer metodos con anotacion @RequestMapping
        for (Method m : methods) {
            if (m.isAnnotationPresent(RequestMapping.class)) {
                mapRequest.put(m.getAnnotation(RequestMapping.class).value(), m);
            }

            //Extraer el valor del path
            //Extraer una instancia del método
            //Poner en la tabla el método con llave path
            ServerSocket serverSocket = null;
            Spark spark = Spark.getInstance();

            try {
                serverSocket = new ServerSocket(35000);
            } catch (IOException e) {
                System.err.println("Could not listen on port: 35000.");
                System.exit(1);
            }

            boolean running = true;
            while (running) {
                Socket clientSocket = null;
                //reply = "Inicio";
                try {
                    System.out.println("Listo para recibir ...");
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String request = "/simple";
                String petition = "GET";
                Boolean FirstLine = true;
                String title = "", inputLine, outputLine = "";
                outputStream = clientSocket.getOutputStream();

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);


                    if (inputLine.contains("/hello?name=")) {
                        String[] res = inputLine.split("=");
                        title = res[1].split("HTTP")[0].replace(" ", "");
                    }

                    if (FirstLine) {
                        request = inputLine.split(" ")[1];
                        petition = inputLine.split(" ")[0];
                        FirstLine = false;
                    }
                    if (!in.ready()) {
                        break;
                    }
                }

                if (request.startsWith("/apps/")) {
                    System.out.println(mapRequest);
                    String path = request.substring(5);
                    System.out.println(mapRequest.get(path));
                    outputLine = "" + mapRequest.get(path).invoke(null);
                    System.out.println(outputLine);
                }
                out.println(outputLine);
                out.close();
                in.close();
                clientSocket.close();
            }
            serverSocket.close();
        }

    }
}



















