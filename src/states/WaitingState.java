package states;

import model.Shipment;

public class WaitingState implements ShipmentState {
    @Override
    public void advance(Shipment contesto) {
        System.out.println(">>> Spedizione " + contesto.getId() + ": Merce caricata su " + contesto.getVehicle().getModelName() + ". Si parte!");
        // Cambio stato -> In Viaggio
        contesto.setState(new TransitState());
    }

    @Override
    public String getDescription() { return "IN PREPARAZIONE"; }
}