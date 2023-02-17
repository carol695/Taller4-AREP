package edu.escuelaing.arem.app.reflexionlab;

public class Faa {
    @Test
    public static void a1() { }
    public static void a2() { }
    @Test public static void a3() {
        throw new RuntimeException("Boom");
    }

    public static void a4() { }
    @Test public static void a5() { }
    public static void a6() { }
    @Test public static void a7() {
        throw new RuntimeException("Crash");
    }
    public static void a8() { }
}
