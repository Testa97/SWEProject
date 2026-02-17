package states;

import model.Spedizione;

public class StatoInViaggio implements StatoSpedizione {
    private int oreTrascorse = 0;

    @Override
    public void avanza(Spedizione contesto) {
        oreTrascorse++;
        // Calcolo: Tempo = Distanza / Velocità
        double oreNecessarie = contesto.getDistanza() / contesto.getVeicolo().getSpeed();

        if (oreTrascorse >= oreNecessarie) {
            System.out.println(">>> Spedizione " + contesto.getId() + ": ARRIVATA a destinazione (" + contesto.getDestinazione().getName() + ")!");
            // Cambio stato -> Consegnata
            contesto.setStato(new StatoConsegnato());
        } else {
            // System.out.println("Spedizione " + contesto.getId() + " in viaggio...");
        }
    }

    @Override
    public String getDescrizione() { return "IN TRANSITO"; }
}