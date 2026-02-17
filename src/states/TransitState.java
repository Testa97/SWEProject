package states;

import model.Shipment;

public class TransitState implements ShipmentState {
    private int hoursElapsed = 0;

    @Override
    public void advance(Shipment context) {
        hoursElapsed++;
        // Calcolo: Tempo = Distanza / Velocità
        double hoursNeeded = context.getDistance() / context.getVehicle().getSpeed();

        if (hoursElapsed >= hoursNeeded) {
            System.out.println(">>> Spedizione " + context.getId() + ": ARRIVATA a destinazione (" + context.getDestination().getName() + ")!");
            // Cambio stato -> Consegnata
            context.setState(new DeliveredState());
        } else {
            // System.out.println("Spedizione " + contesto.getId() + " in viaggio...");
        }
    }

    @Override
    public String getDescription() { return "IN TRANSITO"; }
}