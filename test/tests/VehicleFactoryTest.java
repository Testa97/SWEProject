package tests;
import patterns.VehicleFactory;

import model.AbstractVehicle;
import model.Furgone;
import model.Tir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryTest {

    private final VehicleFactory factory = new VehicleFactory();

    @Test
    void testCreateFurgone() {
        // Test: Peso basso (500kg) -> Deve tornare Furgone
        AbstractVehicle v = factory.createVehicle(500);

        assertTrue(v instanceof Furgone, "Dovrebbe essere un Furgone");
        assertEquals("Furgone Express", v.getModelName());
        assertEquals(110, v.getSpeed());
    }

    @Test
    void testCreateTir() {
        // Test: Peso alto (5000kg) -> Deve tornare Tir
        AbstractVehicle v = factory.createVehicle(5000);

        assertTrue(v instanceof Tir, "Dovrebbe essere un Tir");
        assertEquals("Tir Heavy Duty", v.getModelName());
        assertEquals(70, v.getSpeed());
    }
}