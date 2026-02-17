package states;

import model.Spedizione;

public interface StatoSpedizione {
    void avanza(Spedizione contesto);
    String getDescrizione();
}