package com.project.controlaccesoparquemonitores;

public class Puerta {
    private final int id;
    private boolean ocupada = false;

    public Puerta(int id) {
        this.id = id;
    }

    public synchronized boolean acceder(Visitante visitante) {
        while (ocupada) {
            try {
                wait(); // Esperar si la puerta está ocupada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        ocupada = true; // Marcar la puerta como ocupada
        System.out.println("El Visitante :" + visitante.getId() + " se encuentra accediendo a través de la puerta " + id);

        // Simular tiempo de acceso
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("El Visitante : " + visitante.getId() + " ha entrado a la puerta " + id);
        ocupada = false; // Liberar la puerta
        notify(); // Notificar a otros hilos que la puerta está libre
        return true;
    }
}
