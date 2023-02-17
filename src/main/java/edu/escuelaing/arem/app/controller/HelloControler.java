package edu.escuelaing.arem.app.controller;


public class HelloControler {
    @RequestMapping("/hello")
    public static String Service() {
        return getHeader() + "Get ready page";

    }
    public static String getHeader() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
    }

}
