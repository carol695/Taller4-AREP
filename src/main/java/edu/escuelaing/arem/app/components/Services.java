package edu.escuelaing.arem.app.components;

import edu.escuelaing.arem.app.controller.RequestMapping;
import edu.escuelaing.arem.app.controller.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static edu.escuelaing.arem.app.components.Resource.getHeader;

@Component
public class Services {
    @RequestMapping("/")
    public static String index(){
        return getHeader() + Response404();
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
