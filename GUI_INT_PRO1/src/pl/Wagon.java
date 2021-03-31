package pl;

import pl.interfaces.WagonAction;

import java.util.LinkedList;
import java.util.List;

public class Wagon implements WagonAction {

    List<Kontener> listaKontenerow = new LinkedList<>();
    final static int maxLiczbaKontenerowWagon = 10;

    @Override
    public void zaladuj(Kontener kontener){

        if(listaKontenerow.size() < maxLiczbaKontenerowWagon) {

            listaKontenerow.add(kontener);
            System.out.println("[OK] Zaladowano na wagon kontener o ID:"+ kontener.nrID);
        }
        else{
            System.out.println("[FAIL] Wagon pełny - załadowano 10 kontenerow, czekam na odjazd");
        }
    }
    public boolean czyPelen()
    {

        return listaKontenerow.size() == maxLiczbaKontenerowWagon;
    }

    @Override
    public String toString() {

        return "\n"+"Wagon:" +"\n";
    }
}
