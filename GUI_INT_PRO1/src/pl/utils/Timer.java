package pl.utils;

import pl.*;
import pl.exceptions.IrresponsibleSenderWithDangerousGoods;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Timer extends Thread  {

    public LocalDate actualTime = LocalDate.now();
    private boolean flaga = true;
    int counter = 1;
    Magazyn magazyn;
    Wagon wagon;

    public Timer(Magazyn magazyn, Wagon wagon) {
        this.magazyn = magazyn;
        this.wagon = wagon;
    }

    public void run() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("uplywCzasuLog.txt", true));
            while (flaga) {
                try {
                    bw.write(actualTime.toString());
                    bw.newLine();
                    bw.flush();

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
                try {
                    sleep(5000);
                } catch (InterruptedException exc) {
                    exc.getStackTrace();
                    return;
                }
                sprawdzStan();
                if(wagon.czyPelen() && counter==6)
                {
                    System.out.println("============odjazd wagonu===============");
                    this.wagon = null;
                    this.wagon = new Wagon();
                    counter = 1;
                }
                setActualTime(actualTime.plusDays(1));
                if(wagon.czyPelen()) {
                    counter++;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    public void sprawdzStan() {

        ArrayList<Integer> doutylizacji = new ArrayList<>();

        for (Kontener kontener : magazyn.konteneryWMagazynie) {
            try {
                if (kontener instanceof KontenerNaWybuchowe || kontener instanceof KontenerNaToksyczne) {

                    if (actualTime.isAfter(kontener.getDateOfEnd())) {
                        System.out.println("Dodalem kontener do utylizacji: " + kontener.getClass().getName().replace("pl.","") + " o ID: " + kontener.nrID + " ponieważ przekroczył czas: "+actualTime);
                        doutylizacji.add(kontener.nrID);
                        System.out.println("Data zakonczenia przechowywania kontenera " + kontener.getDateOfEnd());
                        throw new IrresponsibleSenderWithDangerousGoods(kontener.getDateOfStart(), kontener.getDateOfEnd(), kontener.nrID);
                    }
                }
            } catch (IrresponsibleSenderWithDangerousGoods irresponsibleSenderWithDangerousGoods) {
                kontener.nadawca.dodajOstrzezenie();
            }
        }
        for(int id: doutylizacji)
        {
            magazyn.utylizujKontener(id,wagon);
            System.out.println("Przygotowano do załadowania na wagon do utylizacji o id: "+id+" aktualna data: "+ actualTime);
        }
    }
    @Override
    public String toString() {

        return "\n"+ "Zegar:" + "\n"+
                "AktualnyCzas:->" + actualTime + "\n" +
                "Licznik:->" + counter ;
    }
    public  int getCounter()
    {

        return this.counter;
    }
    public void setFlaga(boolean flaga) {

        this.flaga = flaga;

    }
    public  LocalDate getActualDate() {

        return actualTime;

    }
    public  void setCounter(int newCounter)
    {

        this.counter = newCounter;
    }
    public  void setActualTime(LocalDate actualTime) {
        this.actualTime = actualTime;

    }

    }
