package pl;

public class KontenerCiezki extends Kontener {

    protected boolean czyWzmocniony;

    public KontenerCiezki(Nadawca nadawca, double tara,
                          String zabezpieczenia, double wagaNetto,
                          double wagaBrutto, String informacjeOCertyfikatach,
                          int NrIdentyfijacyjny, boolean czyWzmocniony) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny);
        this.czyWzmocniony = czyWzmocniony;

    }

    @Override
    public String toString() {
        return "\n" +"KontenerCiezki: " +"\n"+
                "Nadawca:->" + nadawca.toString() + "\n" +
                "Tara:->" + tara + "\n" +
                "Zabezpieczenia:->" + zabezpieczenia + "\n" +
                "WagaNetto:->" + wagaNetto + "\n" +
                "WagaBrutto:->" + wagaBrutto + "\n" +
                "InformacjeOCertyfikatach:->" + informacjeOCertyfikatach+ "\n" +
                "NrIdentyfijacyjny:->" + nrID + "\n" +
                "Data przybycia do magazynu:->"+dateOfStart+ "\n" +
                "CzyWzmocniony:->"+czyWzmocniony+ "\n" ;
    }
}
