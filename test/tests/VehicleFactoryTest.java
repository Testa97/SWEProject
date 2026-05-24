package tests;
import fleet.VehicleFactory;

import fleet.AbstractVehicle;
import fleet.Van;
import fleet.Truck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehicleFactoryTest {

    private final VehicleFactory factory = new VehicleFactory();

    @Test
    void testCreateVan() {
        // Test: Peso basso (500kg) -> Deve tornare Van
        AbstractVehicle v = factory.createVehicle(500);

        assertTrue(v instanceof Van, "Dovrebbe essere un Van");
        assertEquals("Van Express", v.getModelName());
        assertEquals(110, v.getSpeed());
    }

    @Test
    void testCreateTruck() {
        // Test: Peso alto (5000kg) -> Deve tornare Tir
        AbstractVehicle v = factory.createVehicle(5000);

        assertTrue(v instanceof Truck, "Dovrebbe essere un Truck");
        assertEquals("Heavy Truck", v.getModelName());
        assertEquals(70, v.getSpeed());
    }
}