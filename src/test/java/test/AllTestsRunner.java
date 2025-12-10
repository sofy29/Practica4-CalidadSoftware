package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AllTestsRunner {

    public static void main(String[] args) {
        System.out.println("Ejecutando todas las clases de prueba...");

        Result result = JUnitCore.runClasses(
                BibliotecaMusicalTest.class,
                BibliotecaMusicalPartitionByCategoryTest.class,
                BibliotecaMusicalTestFallos.class
        );

        int total = result.getRunCount();
        int fallos = result.getFailureCount();
        int ignorados = result.getIgnoreCount();
        int exitosos = total - fallos - ignorados;

        System.out.println("---- RESULTADOS ----");
        System.out.println("Tests ejecutados: " + total);
        System.out.println("Fallos: " + fallos);
        System.out.println("Ignorados: " + ignorados);
        System.out.println("Éxitos: " + exitosos);
        System.out.println("Tiempo: " + result.getRunTime() + " ms");

        if (!result.wasSuccessful()) {
            System.out.println("\nDetalles de fallos:");
            for (Failure failure : result.getFailures()) {
                System.out.println(" - " + failure.toString());
            }
        } else {
            System.out.println("TODOS LOS TESTS OK ✅");
        }
    }
}
