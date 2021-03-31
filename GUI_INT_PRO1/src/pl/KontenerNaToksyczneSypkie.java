package pl;

public class KontenerNaToksyczneSypkie extends KontenerNaToksyczne {

    protected String jakiRodzajGranulatu;

    public KontenerNaToksyczneSypkie(Nadawca nadawca, double tara,
                                     String zabezpieczenia, double wagaNetto,
                                     double wagaBrutto, String informacjeOCertyfikatach,
                                     int NrIdentyfijacyjny, boolean czyWzmocniony,
                                     String jakiRodzajGranulatu,TOKSYCZNOSC_KATEGORIA kategoria) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny, czyWzmocniony,kategoria);
        this.jakiRodzajGranulatu = jakiRodzajGranulatu;
    }

    @Override
    public String toString() {
        return "\n" +"KontenerNaToksyczneSypkie: " +"\n"+
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
                "JakiRodzajGranulatu:->" + jakiRodzajGranulatu + "\n"+
                "DataKoncaPrzechowywania:->" + dateOfEnd+"\n" ;
    }
}
