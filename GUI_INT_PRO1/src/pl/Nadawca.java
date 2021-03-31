package pl;

import pl.exceptions.IrresponsibleSenderWithDangerousGoods;

import java.util.ArrayList;

public class Nadawca extends Osoba{

    private int aktualneostrzezenia = 0;
    private ArrayList<IrresponsibleSenderWithDangerousGoods> listaKontenerowOdeslanych = new ArrayList<>();

    public Nadawca(String imie, String nazwisko, String pesel, Adres adres, String dataUrodzenia) {
        super(imie, nazwisko, pesel, adres, dataUrodzenia);
    }
    public Nadawca getNadawca() {

        return this;
    }
    public void setNadawca(Nadawca nadawca) {

        nadawca = this;
    }

    public ArrayList<IrresponsibleSenderWithDangerousGoods> getListaKontenerowOdeslanych() {

        return listaKontenerowOdeslanych;
    }
    public void dodajOstrzezenie()
    {
        this.aktualneostrzezenia++;
    }

    public void setListaKontenerowOdeslanych(ArrayList<IrresponsibleSenderWithDangerousGoods> listaKontenerow) {

        this.listaKontenerowOdeslanych = listaKontenerow;

    }
    public int getAktualneostrzezenia() {

        return aktualneostrzezenia;
    }
}
