package patterns;

import model.AbstractVehicle;
import model.Tir;
import model.Furgone;

public class VehicleFactory {

    // Logica decisionale basata sul peso della merce da spedire
    public AbstractVehicle createVehicle(double weightInKg) {
        if (weightInKg <= 800) {
            return new Furgone();
        } else {
            return new Tir();
        }
    }
}
