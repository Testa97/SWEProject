package tests.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import infrastructure.*;

public class HubTest {

    @Test
    public void testHubCapacity() {
        // Costruttore: String name, int bayCount, double latitude, double longitude
        Hub hub = new Hub("Test Hub", 1, 45.0, 10.0); // Hub con 1 sola baia

        assertTrue(hub.hasAvailableBay());

        // Occupa l'unica baia disponibile
        hub.getBays().get(0).setOccupied(true);

        assertFalse(hub.hasAvailableBay(), "L'Hub dovrebbe risultare pieno");
    }

    @Test
    public void testHubWithTwoBays() {

        Hub hub = new Hub("Hub",2,45.0,10.0);

        hub.getBays().get(0).setOccupied(true);

        assertTrue(hub.hasAvailableBay());

        hub.getBays().get(1).setOccupied(true);

        assertFalse(hub.hasAvailableBay());
    }
}