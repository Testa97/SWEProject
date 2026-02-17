package model;

public class Truck extends AbstractVehicle {

    public Truck() {
        super();
        this.setSpeed(70);        // Lento
        this.setCapacity(24000);  // Max 24 Tonnellate
        this.setModelName("Heavy Truck");
    }

    @Override
    public void prepareForDeparture() {
        System.out.println("Caricamento pesante Truck (ID: " + getId() + ")...");
        try {
            // Simula un tempo lungo di carico (5 secondi) [cite: 169]
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}