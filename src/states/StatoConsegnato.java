package states;

import model.Spedizione;

public class StatoConsegnato implements StatoSpedizione {
    @Override
    public void avanza(Spedizione contesto) {
        // Non fa nulla
    }

    @Override
    public String getDescrizione() { return "CONSEGNATA"; }
}