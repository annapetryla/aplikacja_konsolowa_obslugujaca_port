package pl;

public class KontenerChlodniczy extends Kontener {

    float temperaturaChlodzenia;

    public KontenerChlodniczy(Nadawca nadawca, double tara, String zabezpieczenia,
                              double wagaNetto, double wagaBrutto,
                              String informacjeOCertyfikatach, int NrIdentyfijacyjny,
                              float temperaturaChlodzenia) {

        super(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjeOCertyfikatach, NrIdentyfijacyjny);

        this.temperaturaChlodzenia = temperaturaChlodzenia;

    }

    @Override
    public String toString() {
        return "\n" +"KontenerChlodniczy: " + "\n" +
                "Nadawca:->" + nadawca.toString() + "\n" +
                "Tara:->" + tara + "\n" +
                "Zabezpieczenia:->" + zabezpieczenia + "\n" +
                "WagaNetto:->" + wagaNetto + "\n" +
                "WagaBrutto:->" + wagaBrutto + "\n" +
                "InformacjeOCertyfikatach:->" + informacjeOCertyfikatach+ "\n" +
                "NrIdentyfikacyjny:->" + nrID + "\n" +
                "Czas przybycia do magazynu:->"+dateOfStart+ "\n" +
                "TemperaturaChlodzenia:->" + temperaturaChlodzenia+ "\n" ;
    }
}

