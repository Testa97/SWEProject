package tests;
import model.Hub;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HubTest {

    private Hub hub;

    @BeforeEach
    void setUp() {
        // Crea un Hub con 2 baie
        hub = new Hub("Hub Test", 2, 45.0, 11.0);
    }

    @Test
    void testGestioneBaie() {
        // All'inizio deve esserci posto
        assertTrue(hub.isBayAvailable(), "Le baie dovrebbero essere libere all'inizio");

        // Occupiamo la prima baia
        hub.getBays().get(0).setOccupied(true);
        assertTrue(hub.isBayAvailable(), "Dovrebbe esserci ancora una baia libera");

        // Occupiamo la seconda baia
        hub.getBays().get(1).setOccupied(true);

        // Ora deve essere pieno
        assertFalse(hub.isBayAvailable(), "L'hub dovrebbe risultare pieno");
    }

    @Test
    void testCoordinate() {
        assertEquals(45.0, hub.getLatitude());
        assertEquals(11.0, hub.getLongitude());
    }
}