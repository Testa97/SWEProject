package states;

import model.Shipment;

public interface ShipmentState {
    void advance(Shipment contesto);
    String getDescription();
}