package pl;

import pl.interfaces.KontenerAction;
import pl.interfaces.ZapiszStanAction;

import java.util.ArrayList;
import java.time.LocalDate;

public class Magazyn implements KontenerAction, ZapiszStanAction {

    private int maxNrOfKontenery; //pojemnosc
    private int dostepnaPojemnosc;
    public ArrayList<Kontener> konteneryWMagazynie = new ArrayList<>();

    Magazyn (int maxNrOfKontenery) {
        this.maxNrOfKontenery = maxNrOfKontenery;
        this.dostepnaPojemnosc = maxNrOfKontenery;
    }

    @Override
    public String toString() {

        return "\n"+"Magazyn:" +"\n"+
                "Maksymalna liczba konterow:->" + maxNrOfKontenery + "\n"+
                "Dostepna Pojemnosc:->" + dostepnaPojemnosc + "\n";

    }

    public void setDostepnaPojemnosc(int dostepnaPojemnosc) {

        this.dostepnaPojemnosc = dostepnaPojemnosc;
    }

    public void wyswietlZawartosc() {
        if(konteneryWMagazynie.isEmpty()) {
            System.out.println("Magazyn jest pusty");
        }
        else{
            System.out.println(konteneryWMagazynie.toString());
        }
    }

    public synchronized void utylizujKontener(int idKontenera,Wagon wagon) {
        boolean flaga;
        konteneryWMagazynie.forEach(kontener -> {
            try {
                if(kontener.nrID == idKontenera)
                    wagon.zaladuj(kontener);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        flaga = konteneryWMagazynie.removeIf(k -> (k.nrID == idKontenera));
        if(flaga)
            System.out.println("Wyladowano kontener o id: " + idKontenera+" z magazynu na wagon");
        else
            System.out.println("Nie znaleziono kontenera o id: " + idKontenera);
    }

    @Override
    public synchronized void zaladuj(Kontener kontener,LocalDate actualDate) throws Exception {
        LocalDate dataZaladowania = actualDate;
        if (this.konteneryWMagazynie.size() != maxNrOfKontenery && !this.konteneryWMagazynie.contains(kontener)) {
            if (kontener instanceof KontenerNaWybuchowe) {
                mojaProceduraZaladowania(kontener, actualDate, 5);
            }
            if (kontener instanceof KontenerChlodniczy) {
                mojaProceduraZaladowania(kontener,actualDate,0);
            }
            if (kontener instanceof KontenerCiezki) {
                if (kontener instanceof KontenerNaToksyczneSypkie) {
                    mojaProceduraZaladowania(kontener, actualDate, 14);
                }
            }
            if (kontener instanceof KontenerNaToksyczneCiekle) {
                mojaProceduraZaladowania(kontener, actualDate, 10);
            }
            if (!(kontener instanceof KontenerNaToksyczne) && !(kontener instanceof KontenerNaWybuchowe)) {
                mojaProceduraZaladowania(kontener,actualDate,0);
            }
        } else if (this.konteneryWMagazynie.contains(kontener)) {
            System.out.println("[FAIL] W tym magazynie juz jest kontener o takim id : " + kontener.nrID);
        } else {
            System.out.println("[FAIL] W tym magazynie " + this + " za wiele kontenerow");
        }
    }

    private <T extends Kontener> void mojaProceduraZaladowania(T kontener,LocalDate actualDate,int liczbaDni) {
        if (sprawdzIleOstrzezenNadawca(kontener.nadawca)) {
            konteneryWMagazynie.add(kontener);
            dostepnaPojemnosc--;
            System.out.println("[OK] Dodano do magazynu kontener : " +kontener.getClass().getName().replace("pl.","")+" "+ kontener.nrID + " ");
            kontener.setDateOfStart(actualDate);
            kontener.setDateOfEnd(actualDate.plusDays(liczbaDni));
        } else {
            System.out.println("[FAIL] Nakaz odesłania do nadawcy: "+kontener.nadawca);
        }
    }

    @Override
    public synchronized void wyladuj(Kontener kontener) {
        if (!kontener.equals(null) && konteneryWMagazynie.contains(kontener)) {
            dostepnaPojemnosc++;
            konteneryWMagazynie.remove(kontener);
            System.out.println(kontener.nrID + " wyjety");
        } else
            System.out.println("[FAIL] Podanego kontenera nie ma w tym magazynie");
    }

    public boolean sprawdzIleOstrzezenNadawca(Nadawca nadawca) {
        if( nadawca.getAktualneostrzezenia() >= 2 ) {
            System.out.println("[FAIL] Nie mogę przyjac kolejnych kontenerow, za wiele ostrzeżeń. Nakaz odesłania do portu");
            return false;
        }
        return true;
    }

    protected Kontener getKontenerPrzezId(int nrIdentyfikacyjny) {
        Kontener tmp = null;
        for( Kontener kontener : konteneryWMagazynie ) {
            tmp = (kontener.nrID == nrIdentyfikacyjny? kontener : null);
        }
        return tmp;
    }

}

