package tests;
import simulation.Observer;
import simulation.SimulationClock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimulationClockTest {

    @Test
    void testSingletonUniqueness() {
        // Chiedo l'istanza due volte
        SimulationClock c1 = SimulationClock.getInstance();
        SimulationClock c2 = SimulationClock.getInstance();

        // DEVONO essere lo stesso oggetto identico in memoria
        assertSame(c1, c2, "Il Singleton deve restituire sempre la stessa istanza!");
    }

    @Test
    void testSingletonNotNull() {
        assertNotNull(SimulationClock.getInstance(), "L'istanza non deve mai essere null");
    }

    //test Observer
    @Test
    public void testObserverNotification() {
        SimulationClock clock = SimulationClock.getInstance();
        final boolean[] isNotified = {false};

        // Creiamo un Mock Observer al volo usando una lambda
        Observer mockObserver = currentHour -> isNotified[0] = true;

        clock.addObserver(mockObserver);

        // Attiviamo l'orologio manualmente
        clock.manualTickForTest();

        assertTrue(isNotified[0], "L'Observer deve ricevere la notifica (update) dal Subject");
    }

}