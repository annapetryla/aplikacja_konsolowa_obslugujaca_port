package pl;

public abstract class KontenerNaToksyczne extends KontenerCiezki {

    protected enum TOKSYCZNOSC_KATEGORIA {
        A,
        B,
        C
    }

    protected TOKSYCZNOSC_KATEGORIA kategoria;

    public KontenerNaToksyczne(Nadawca nadawca, double tara,
                               String zabezpieczenia, double wagaNetto,
                               double wagaBrutto, String informacjeOCertyfikatach,
                               int NrIdentyfijacyjny, boolean czyWzmocniony, TOKSYCZNOSC_KATEGORIA kategoria) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny, czyWzmocniony);
        this.kategoria = kategoria;
    }
}
