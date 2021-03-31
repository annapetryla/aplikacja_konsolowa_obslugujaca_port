package pl;

import pl.interfaces.InfoAction;
import pl.interfaces.KontenerAction;
import pl.interfaces.ShipAction;

import java.util.ArrayList;

public class Statek implements KontenerAction, ShipAction, InfoAction,Comparable<Statek>{

    protected String nazwa;
    protected String port_macierzysty;
    protected String lokalizacja_zrodlowa;
    protected String lokalizacja_transportu;
    protected String lokalizacja_docelowa;
    protected int nrIdentyfikacyjny;
    private int pojemnosc ; //liczba wszystkich kontenerow
    private double nosnosc ;
    protected int maxLiczbaToksWybuch;
    protected int maxLiczbaCiezkich;
    protected int maxLiczbaElektro;
    ArrayList<Kontener> listaKontenerowNaStatku = new ArrayList<>();
    protected int dostepnaPojemnosc;

    public Statek(String nazwa, String port_macierzysty,String lokalizacja_zrodlowa,
                  String lokalizacja_transportu,
                  String lokalizacja_docelowa,int nrIdentyfikacyjny,
                  int pojemnosc,double nosnosc,
                  int maxLiczbaCiezkich,int maxLiczbaElektro,int maxLiczbaToksWybuch) {

        this.nazwa = nazwa;
        this.lokalizacja_docelowa = lokalizacja_docelowa;
        this.lokalizacja_transportu = lokalizacja_transportu;
        this.lokalizacja_zrodlowa = lokalizacja_zrodlowa;
        this.port_macierzysty = port_macierzysty;
        this.nrIdentyfikacyjny = nrIdentyfikacyjny;
        this.pojemnosc = pojemnosc;
        this.nosnosc = nosnosc;
        this.maxLiczbaCiezkich = maxLiczbaCiezkich;
        this.maxLiczbaElektro = maxLiczbaElektro;
        this.maxLiczbaToksWybuch = maxLiczbaToksWybuch;
        this.dostepnaPojemnosc = pojemnosc;
        System.out.println("[OK] Stworzono statek o ID:"+nrIdentyfikacyjny);

    }

    public int getNrIdentyfikacyjny() {

        return nrIdentyfikacyjny;
    }

    @Override
    public String toString() {

        return "\n"+"Statek:" + "\n"+
                "Nazwa:->" + nazwa + "\n" +
                "Port macierzysty:->" + port_macierzysty + "\n" +
                "Lokalizacja źródłowa:->" + lokalizacja_zrodlowa + "\n" +
                "Lokalizacja transportu:->" + lokalizacja_transportu + "\n" +
                "Lokalizacja docelowa:->" + lokalizacja_docelowa + "\n" +
                "Id:->" + nrIdentyfikacyjny + "\n"+
                "Pojemnosc:->" + pojemnosc + "\n"+
                "Nosnosc:->" + nosnosc + "\n"+
                "maxLiczbaCiezkich:->" + maxLiczbaCiezkich + "\n"+
                "maxLiczbaElektro:->" + maxLiczbaElektro + "\n"+
                "maxLiczbaToksWybuch:->" + maxLiczbaToksWybuch + "\n"+
                "dostepnaPojemnosc:->" + dostepnaPojemnosc + "\n";
    }

    public int getPojemnosc() {

        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {

        this.pojemnosc = pojemnosc;
    }

    private double getCiezarListaKontenerow ( ArrayList<? extends Kontener> listaKontenerowNaStatku) throws Exception{
        double suma = listaKontenerowNaStatku.stream().mapToDouble(kontener -> kontener.wagaBrutto).sum();
        return suma;
    }

    public double getNosnosc() {

        return nosnosc;
    }

    public void setNosnosc(double nosnosc) {

        this.nosnosc = nosnosc;
    }

    public int getDostepnaPojemnosc() {

        return dostepnaPojemnosc;
    }
    public void setDostepnaPojemnosc(int dostepnaPojemnosc) {

        this.dostepnaPojemnosc = dostepnaPojemnosc;
    }

    public ArrayList<Kontener> getListaKontenerowNaStatku() {

        return listaKontenerowNaStatku;
    }

    @Override
    public void zaladuj(Kontener kontener) throws Exception {

            int ileCiezkich = 0;
            int ileChlodniczych = 0;
            int ileToksLubWybuch = 0;

            if (listaKontenerowNaStatku.contains(kontener)) {
                System.out.println("Taki kontener jest juz zaladowany na statku.\nWybierz inny kontener");
            } else {
                for (Kontener k : listaKontenerowNaStatku) {
                    if (k instanceof KontenerCiezki) {
                        if (++ileCiezkich > maxLiczbaCiezkich)
                            System.out.println("[FAIL]Na tym statku przekroczono liczbe ciezkich.\nWybierz inny kontener");
                    }
                    if (k instanceof KontenerChlodniczy) {
                        if (++ileChlodniczych > maxLiczbaElektro)
                            System.out.println("[FAIL]Na tym statku przekroczono liczbe wymagajacych zasilania.\nWybierz inny kontener");
                    }
                    if (k instanceof KontenerNaToksyczne || k instanceof KontenerNaWybuchowe) {
                        if (++ileToksLubWybuch > maxLiczbaToksWybuch)
                            System.out.println("[FAIL]Na tym statku przekroczono liczbe toksycznych lub wybuchowych.\nWybierz inny kontener");
                    }
                }
                if (listaKontenerowNaStatku.size() == this.pojemnosc) {
                    System.out.println("[FAIL]Przekraczasz mozliwa pojemnosc");
                } else {
                    double wagaPoZaladowaniuKontenera = getCiezarListaKontenerow(listaKontenerowNaStatku) + kontener.wagaBrutto;
                    if (this.nosnosc > wagaPoZaladowaniuKontenera) {
                        dostepnaPojemnosc--;
                        this.listaKontenerowNaStatku.add(kontener);
                        System.out.println("[OK] Zaladowano na statek ID:" + this.nrIdentyfikacyjny + " kontener o ID:" + kontener.nrID + ". Aktualna dostepna pojemnosc na kontenery na statku wynosi:" + dostepnaPojemnosc);
                    } else {
                        System.out.println("[FAIL] Niepowodzenie! Probowano zaladowac na statek ID:" + this.nrIdentyfikacyjny + " kontener o ID:" + kontener.nrID + " ktory przekracza aktualna nosnosc statku:" + this.nosnosc + " waga po zaladunku kontenera wynosilaby:" + wagaPoZaladowaniuKontenera);

                    }
                }
            }
    }

    @Override
    public void wyslijDoPortu() {

        System.out.println("Statek wyrusza do innego portu");
    }

    @Override
    public void wyladuj(Kontener kontener) throws Exception {
        if (!kontener.equals(null) && listaKontenerowNaStatku.contains(kontener)) {
            dostepnaPojemnosc++;
            this.listaKontenerowNaStatku.remove(kontener);
            System.out.println(kontener.nrID + " wyjety");
        } else
            System.out.println("[FAIL]Podanego kontenera nie ma na tym statku");
    }


    @Override
    public void wyswietlInfo() throws Exception {

        System.out.println(this.toString());
        System.out.println(listaKontenerowNaStatku.toString());
    }

    public Kontener getKontenerDaneID(int nrIdentyfikacyjny) {

        Kontener tmp = null;
        for(Kontener kontener : listaKontenerowNaStatku) {
            tmp = (kontener.nrID==nrIdentyfikacyjny? kontener : null);
        }
        return tmp;
    }

    @Override
    public int compareTo(Statek o) {

        return this.nazwa.compareTo(o.nazwa);
    }
}


