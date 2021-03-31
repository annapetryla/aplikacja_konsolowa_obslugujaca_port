package pl;

public class KontenerNaToksyczneCiekle extends KontenerNaToksyczne {

    protected boolean czyPosiadaBezpicznik;

    public KontenerNaToksyczneCiekle(Nadawca nadawca, double tara,
                                     String zabezpieczenia, double wagaNetto,
                                     double wagaBrutto, String informacjeOCertyfikatach,
                                     int NrIdentyfijacyjny, boolean czyWzmocniony, boolean czyPosiadaBezpicznik,TOKSYCZNOSC_KATEGORIA kategoria) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny, czyWzmocniony,kategoria);
        this.czyPosiadaBezpicznik = czyPosiadaBezpicznik;
    }

    @Override
    public String toString() {
        return "\n" +"KontenerNaToksyczneCiekle: " +"\n"+
                "Nadawca:->" + nadawca.toString() + "\n" +
                "Tara:->" + tara + "\n" +
                "Zabezpieczenia:->" + zabezpieczenia + "\n" +
                "WagaNetto:->" + wagaNetto + "\n" +
                "WagaBrutto:->" + wagaBrutto + "\n" +
                "InformacjeOCertyfikatach:->" + informacjeOCertyfikatach+ "\n" +
                "NrIdentyfijacyjny:->" + nrID + "\n" +
                "Czas przybycia do magazynu:->"+dateOfStart+ "\n" +
                "CzyWzmocniony:->"+czyWzmocniony+ "\n" +
                "Kategoria toksycznosci:->"+kategoria+ "\n" +
                "CzyPosiadaBezpiecznik:->"+czyPosiadaBezpicznik+"\n"+
                "DataKoncaPrzechowywania:->" + dateOfEnd +"\n";
    }
}
