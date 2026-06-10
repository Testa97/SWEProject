package tests.simulation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulation.*;


public class SimulationClockObserverTest {

    @Test
    public void testClockNotifiesObservers() {
        SimulationClock clock = SimulationClock.getInstance();

        // Creazione di un Mock Observer tramite interfaccia funzionale
        final boolean[] isNotified = {false};
        Observer mockObserver = currentHour -> isNotified[0] = true;

        clock.addObserver(mockObserver);

        // Avanzamento del tempo che deve triggerare la notifica
        clock.advanceTime();

        // Verifica che l'Observer sia stato avvisato
        assertTrue(isNotified[0], "L'Observer doveva ricevere la notifica");
    }
}