package model;

public class Van extends AbstractVehicle {

    public Van() {
        super();
        this.setSpeed(110);      // Veloce
        this.setCapacity(800);   // Max 800 kg
        this.setModelName("Van Express");
    }

    @Override
    public void prepareForDeparture() {
        System.out.println("Caricamento rapido Van (ID: " + getId() + ")...");
        try {
            // Simula un tempo breve di carico (2 secondi) come Raspanti [cite: 555]
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}