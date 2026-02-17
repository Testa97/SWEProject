package states;

import model.Shipment;

public class DeliveredState implements ShipmentState {
    @Override
    public void advance(Shipment contesto) {
        // Non fa nulla
    }

    @Override
    public String getDescription() { return "CONSEGNATA"; }
}