package edu.escuelaing.arem.app.components;


import edu.escuelaing.arem.app.controller.RequestMapping;
import edu.escuelaing.arem.app.HttpServer;
import edu.escuelaing.arem.app.controller.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class Resource {
    @RequestMapping("/app")
    public static String FileJS(){
        return getHeader() + getResponseJS();
    }

    @RequestMapping("/html")
    public static String FileHTML(){
        return getHeaderHTML() + getResponseHTML();
    }

    @RequestMapping("/css")
    public static String FileCSS(){
        return getHeader() + getResponseCSS();
    }


    @RequestMapping("/image")
    public static String FileImage() throws IOException {
        return getResponseImage();
    }



    public static String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json \r\n"
                + "\r\n";
    }


    public static String getHeaderHTML() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html \r\n"
                + "\r\n";
    }



    private static String getResponseImage() throws IOException {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: image/jpg\r\n"
                + "\r\n";
        BufferedImage bufferedImage = ImageIO.read(new File("src/main/resource/imagen.jpg"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        HttpServer server = HttpServer.getInstance();
        DataOutputStream dataOutputStream = new DataOutputStream(server.getOutputStream());
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        dataOutputStream.writeBytes(response);
        dataOutputStream.write(byteArrayOutputStream.toByteArray());
        return response;
    }


    private static String getResponseCSS() {
        byte[] data = new byte[0];
        try {
            Path path = Paths.get("src/main/resource/estilos.css");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);
    }


    private static String getResponseHTML() {
        byte[] data = new byte[0];
        try {
            Path path = Paths.get("src/main/resource/index.html");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);

    }

    private static String getResponseJS() {
        byte[] data = new byte[0];
        try {
            Path path = Paths.get("src/main/resource/main.js");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);

    }
}
