package shipment;

public interface ShipmentState {
    void advance(Shipment contesto);
    String getDescription();
}