package pl;

import java.time.LocalDate;

public abstract class Kontener  implements Comparable<Kontener> {

    public int nrID;
    public Nadawca nadawca;
    public double tara;
    public String zabezpieczenia;
    public double wagaNetto;
    public double wagaBrutto;
    public String informacjeOCertyfikatach;
    public LocalDate dateOfStart;
    public LocalDate dateOfEnd;

    public Kontener(Nadawca nadawca,
                    double tara,
                    String zabezpieczenia,
                    double wagaNetto,
                    double wagaBrutto,
                    String informacjeOCertyfikatach, int NrIdentyfijacyjny) {

        this.nadawca = nadawca;
        this.tara = tara;
        this.zabezpieczenia = zabezpieczenia;
        this.wagaBrutto = wagaBrutto;
        this.wagaNetto = wagaNetto;
        this.informacjeOCertyfikatach = informacjeOCertyfikatach;
        this.nrID = NrIdentyfijacyjny;
    }

    public LocalDate getDateOfEnd() {

        return dateOfEnd;
    }

    public LocalDate getDateOfStart() {

        return dateOfStart;

    }
    public void setDateOfStart(LocalDate dateOfStart) {

        this.dateOfStart = dateOfStart;

    }
    public void setDateOfEnd(LocalDate dateOfEnd) {

        this.dateOfEnd = dateOfEnd;
    }

    public void setNadawca(Nadawca nadawca) {

        this.nadawca = nadawca;
    }

    public Nadawca getNadawca() {

        return nadawca;
    }

    public int getNrID() {

        return nrID;
    }

    @Override
    public int compareTo(Kontener o) {

        if(this.wagaBrutto==o.wagaBrutto) {
            return 0;
        }
        else if(this.wagaBrutto>o.wagaBrutto) {
            return 1;
        }
        else {
            return -1;
        }
    }
    public int compareToData(Kontener o)  {

        if (this.dateOfStart == o.dateOfStart) {
            return this.nadawca.nazwisko.compareTo(o.nadawca.nazwisko);
        }
        else if (this.dateOfStart.isAfter(o.dateOfStart)) {
            return 1;
        } else {
            return -1;
        }
    }
}


