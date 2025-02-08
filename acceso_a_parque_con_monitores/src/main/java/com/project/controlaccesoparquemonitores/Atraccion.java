package com.project.controlaccesoparquemonitores;

public class Atraccion {
    private final String nombre;
    private boolean ocupada = false;

    public Atraccion(String nombre) {
        this.nombre = nombre;
    }

    public synchronized void acceder(Visitante visitante) {
        while (ocupada) {
            try {
                wait(); // Esperar si la atracción está ocupada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        ocupada = true; // Marcar la atracción como ocupada
        System.out.println("Visitante " + visitante.getId() + " accediendo a la atracción " + nombre);

        // Simular tiempo en la atracción
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Visitante " + visitante.getId() + " ha salido de la atracción " + nombre);
        ocupada = false; // Liberar la atracción
        notify(); // Notificar a otros hilos que la atracción está libre
    }
}
