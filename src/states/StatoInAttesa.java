package states;

import model.Spedizione;

public class StatoInAttesa implements StatoSpedizione {
    @Override
    public void avanza(Spedizione contesto) {
        System.out.println(">>> Spedizione " + contesto.getId() + ": Merce caricata su " + contesto.getVeicolo().getModelName() + ". Si parte!");
        // Cambio stato -> In Viaggio
        contesto.setStato(new StatoInViaggio());
    }

    @Override
    public String getDescrizione() { return "IN PREPARAZIONE"; }
}