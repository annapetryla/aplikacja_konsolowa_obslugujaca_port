package pl;

public class KontenerNaWybuchowe extends KontenerCiezki {

    double gestosc;

    public KontenerNaWybuchowe (Nadawca nadawca, double tara,
                               String zabezpieczenia, double wagaNetto,
                               double wagaBrutto, String informacjeOCertyfikatach,
                               int NrIdentyfijacyjny, boolean czyWzmocniony,
                               double gestosc) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny, czyWzmocniony);
        this.gestosc = gestosc;
    }

    @Override
    public String toString() {
        return "\n" +"KontenerNaWybuchowe: "+"\n"+
                "Nadawca:->" + nadawca.toString() + "\n" +
                "Tara:->" + tara + "\n" +
                "Zabezpieczenia:->" + zabezpieczenia + "\n" +
                "WagaNetto:->" + wagaNetto + "\n" +
                "WagaBrutto:->" + wagaBrutto + "\n" +
                "InformacjeOCertyfikatach:->" + informacjeOCertyfikatach+ "\n" +
                "NrIdentyfijacyjny:->" + nrID + "\n" +
                "Czas przybycia do magazynu:->"+dateOfStart+ "\n" +
                "CzyWzmocniony:->"+czyWzmocniony+ "\n" +
                "Gestosc:->"+gestosc+"\n"+
                "DataKoncaPrzechowywania:->" + dateOfEnd +"\n";
    }
}

