package tests;
import model.Hub;
import model.Spedizione;
import model.Furgone;
import model.AbstractVehicle;

import org.junit.jupiter.api.Test;
import states.StatoConsegnato;
import states.StatoInAttesa;
import states.StatoInViaggio;
import static org.junit.jupiter.api.Assertions.*;

class SpedizioneStateTest {

    @Test
    void testFlussoCompletoSpedizione() {
        // 1. SETUP: Creiamo due Hub vicini (circa 111km di distanza latitudinale)
        Hub h1 = new Hub("Hub A", 10, 45.0, 10.0);
        Hub h2 = new Hub("Hub B", 10, 46.0, 10.0);

        // Creiamo un furgone (Velocità 110 km/h)
        AbstractVehicle furgone = new Furgone();

        Spedizione s = new Spedizione(h1, h2, furgone);

        // 2. VERIFICA STATO INIZIALE
        assertEquals("IN PREPARAZIONE", s.getStatoLeggibile());
        assertFalse(s.isCompletata());

        // 3. PRIMO AVANZAMENTO (Attesa -> Viaggio)
        s.avanzaDiUnOra();
        assertEquals("IN TRANSITO", s.getStatoLeggibile(), "Dopo il primo tick deve essere in viaggio");

        // 4. AVANZAMENTO VIAGGIO
        // Distanza ~144km (111 * 1.3 strada). Velocità 110km/h.
        // Servono 2 ore circa.
        s.avanzaDiUnOra(); // Ore viaggio: 1
        s.avanzaDiUnOra(); // Ore viaggio: 2 (Dovrebbe arrivare qui)

        // 5. VERIFICA ARRIVO (Viaggio -> Consegnata)
        assertEquals("CONSEGNATA", s.getStatoLeggibile(), "Dovrebbe essere arrivata");
        assertTrue(s.isCompletata(), "Il flag completata deve essere true");
    }
}