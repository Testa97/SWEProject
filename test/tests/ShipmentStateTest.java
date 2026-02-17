package tests;
import model.Hub;
import model.Shipment;
import model.Van;
import model.AbstractVehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentStateTest {

    @Test
    void testFlussoCompletoSpedizione() {
        // 1. SETUP: Creiamo due Hub vicini (circa 111km di distanza latitudinale)
        Hub h1 = new Hub("Hub A", 10, 45.0, 10.0);
        Hub h2 = new Hub("Hub B", 10, 46.0, 10.0);

        // Creiamo un furgone (Velocità 110 km/h)
        AbstractVehicle furgone = new Van();

        Shipment s = new Shipment(h1, h2, furgone);

        // 2. VERIFICA STATO INIZIALE
        assertEquals("IN PREPARAZIONE", s.getStatusDescription());
        assertFalse(s.isCompleted());

        // 3. PRIMO AVANZAMENTO (Attesa -> Viaggio)
        s.advanceOneHour();
        assertEquals("IN TRANSITO", s.getStatusDescription(), "Dopo il primo tick deve essere in viaggio");

        // 4. AVANZAMENTO VIAGGIO
        // Distanza ~144km (111 * 1.3 strada). Velocità 110km/h.
        // Servono 2 ore circa.
        s.advanceOneHour(); // Ore viaggio: 1
        s.advanceOneHour(); // Ore viaggio: 2 (Dovrebbe arrivare qui)

        // 5. VERIFICA ARRIVO (Viaggio -> Consegnata)
        assertEquals("CONSEGNATA", s.getStatusDescription(), "Dovrebbe essere arrivata");
        assertTrue(s.isCompleted(), "Il flag completata deve essere true");
    }
}