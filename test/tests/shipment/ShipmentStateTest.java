package tests.shipment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import shipment.*;
import fleet.Van;
import infrastructure.Hub;

public class ShipmentStateTest {

    @Test
    public void testShipmentLifecycle() {
        // Inizializzazione Hub (necessaria per la compilazione)
        Hub h1 = new Hub("Hub A", 10, 45.0, 10);
        Hub h2 = new Hub("Hub B", 10, 46.0, 10);

        // 1. Creazione (Stato Iniziale: WAITING)
        Shipment shipment = new Shipment(h1, h2, new Van());
        assertTrue(shipment.getStatusDescription().contains("WAITING"));

        // 2. Simulazione Partenza
        shipment.advance();
        assertTrue(shipment.getStatusDescription().contains("TRANSIT"));

        // 3. Simulazione Viaggio (avanzamento veloce)
        for (int i = 0; i < 100; i++) {
            shipment.advance();
        }

        // 4. Verifica Arrivo (Stato Finale: DELIVERED)
        assertTrue(shipment.isCompleted());
        assertTrue(shipment.getStatusDescription().contains("DELIVERED"));
    }
}