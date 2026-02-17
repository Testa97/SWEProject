package tests;
import patterns.SimulationClock;

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
}