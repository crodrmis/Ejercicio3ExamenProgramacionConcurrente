package com.project.controlaccesoparquemonitores;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Parque {
    private final Puerta[] puertas;
    private int codigodeAcceso = 1;

    public Parque(int numPuertas) {
        puertas = new Puerta[numPuertas];
        for (int i = 0; i < numPuertas; i++) {
            puertas[i] = new Puerta(i + 1);
        }
    }

    public void iniciarSimulacion() {
        ExecutorService executor = Executors.newFixedThreadPool(10); // 10 visitantes

        for (int i = 0; i < 10; i++) {
            final int visitanteId = i + 1;
            executor.execute(() -> {
                Visitante visitante = new Visitante(visitanteId, obtenerCodigoAcceso());
                accederAlParque(visitante);
            });
        }

        executor.shutdown();
    }

    private synchronized int obtenerCodigoAcceso() {
        return codigodeAcceso++;
    }

    private void accederAlParque(Visitante visitante) {
        System.out.println("Visitante " + visitante.getId() + " con código " + visitante.getCodigoAcceso() + " intentando acceder al parque.");
        for (Puerta puerta : puertas) {
            if (puerta.acceder(visitante)) {
                return;
            }
        }
        System.out.println("Visitante " + visitante.getId() + " está esperando en la cola.");
    }
}

