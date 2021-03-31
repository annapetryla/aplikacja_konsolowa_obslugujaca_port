package pl;

public class Adres {

    protected String ulica;
    protected String kodPocztowy;
    protected String miasto;
    protected int nrDomuMieszkania;

    public Adres(String ulica, String kodPocztowy, String miasto,int nrDomuMieszkania) {

        this.miasto = miasto;
        this.ulica= ulica;
        this.kodPocztowy = kodPocztowy;
        this.nrDomuMieszkania = nrDomuMieszkania;

    }

    @Override
    public String toString() {
        return  "\n"+
                "       ulica:->" + ulica +
                "       kodPocztowy:->" + kodPocztowy  +
                "       miasto:->" + miasto  +
                "       nrDomuMieszkania:->" + nrDomuMieszkania;
    }
}


