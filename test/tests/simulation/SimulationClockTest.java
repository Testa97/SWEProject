package tests.simulation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import simulation.SimulationClock;

public class SimulationClockTest {

    @Test
    public void testSingletonInstance() {
        SimulationClock clock1 = SimulationClock.getInstance();
        SimulationClock clock2 = SimulationClock.getInstance();

        // Verifica che entrambi i riferimenti puntino allo stesso oggetto
        assertSame(clock1, clock2, "Le istanze devono essere identiche");
        assertNotNull(clock1, "L'istanza non deve essere null");
    }
}