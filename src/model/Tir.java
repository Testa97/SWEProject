package model;

public class Tir extends AbstractVehicle {

    public Tir() {
        super();
        this.setSpeed(70);        // Lento
        this.setCapacity(24000);  // Max 24 Tonnellate
        this.setModelName("Tir Heavy Duty");
    }

    @Override
    public void prepareForDeparture() {
        System.out.println("Caricamento pesante Tir (ID: " + getId() + ")...");
        try {
            // Simula un tempo lungo di carico (5 secondi) [cite: 169]
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}