package edu.escuelaing.arem.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServiceCSS implements RESTService{
    @Override
    public String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/css\r\n"
                + "\r\n";
    }

    @Override
    public String getResponse() throws IOException {
        byte[] data = new byte[0];
        String answer;
        try {
            Path path = Paths.get("src/main/resource/estilos.css");
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String(data);


    }
}
