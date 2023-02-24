package edu.escuelaing.arem.app.components;

import edu.escuelaing.arem.app.controller.RequestMapping;
import edu.escuelaing.arem.app.controller.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class Services {
    @RequestMapping("/")
    public static String index(){
        return getHeader() + ResponseIndex();
    }

    private static String ResponseIndex() {
        byte[] data = new byte[0];
        try {
            Path path = Paths.get("src/main/resource/inicio.html");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);
    }


    @RequestMapping("404")
    public static String notFound(){
        return getHeader() + Response404();
    }

    public static String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html \r\n"
                + "\r\n";
    }





    private static String Response404() {
        byte[] data = new byte[0];
        try {
            Path path = Paths.get("src/main/resource/404.html");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);


    }

}
