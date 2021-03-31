package pl;

public abstract class Osoba {

    protected String imie;
    protected String nazwisko;
    protected final String pesel;
    protected Adres adres;
    protected final String dataUrodzenia;

    public Osoba(String imie, String nazwisko, String pesel, Adres adres, String dataUrodzenia) {

        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adres = adres;
        this.dataUrodzenia = dataUrodzenia;
    }

    @Override
    public String toString() {
        return "\n" +" Imie:->" + imie + "\n" +
                " Nazwisko:->" + nazwisko + "\n" +
                " Pesel:->" + pesel + "\n" +
                " Adres:->" + adres + "\n" +
                " Data urodzenia:->" + dataUrodzenia ;
    }
}
