package pl;

import pl.interfaces.ZapiszStanAction;
import pl.utils.Timer;

import java.io.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static boolean isInterrupted = false;
    final static String sciezkaDoPliku = "stanAplikacji.txt";
    public static IdGenerator idGenKontenerow = new IdGenerator(100);
    public static IdGenerator idGenStatkow = new IdGenerator(200);

    public static void main(String[] args) throws Exception {

        File stanAplikacji = new File(sciezkaDoPliku);

        if (stanAplikacji.isFile()) {

            System.out.println("Znaleziono poprzedni stan aplikacji! Wczytywanie.....");
            FileReader fr = new FileReader(stanAplikacji);
            Magazyn magazyn = wczytajMagazyn(fr);
            fr = new FileReader(stanAplikacji);
            Port port = wczytajPort(fr);
            fr = new FileReader(stanAplikacji);
            Wagon wagon = wczytajWagon(fr);
            fr = new FileReader(stanAplikacji);
            Timer zegar = wczytajzegar(fr, magazyn, wagon);
            zegar.start();
            wystartujAplikacje(magazyn,port,zegar,wagon,idGenStatkow,idGenKontenerow);

        } else {
            // Inicjalizacja poczatkowymi wartosciami
            Port port = new Port();
            Magazyn magazyn = new Magazyn(20);
            Wagon wagon = new Wagon();
            Timer zegar = new Timer(magazyn, wagon);
            zegar.start();
            Nadawca nadawca = new Nadawca("Konrad", "Wronka", "90080229867", new Adres("Dluga", "34-400", "Pcim", 8), "20-01-1990");
            Nadawca nadawca2 = new Nadawca("Kazik", "Sieka", "90083329867", new Adres("Polna", "34-400", "Warszawa", 4), "11-08-1980");
            Nadawca nadawca3 = new Nadawca("Kamil", "Zuch", "90080221167", new Adres("Krotka", "34-400", "Plock", 3), "24-05-1990");
            Kontener kontener1C = new KontenerCiezki(nadawca, 0.5, "certyfikat A+", 0.5, 1.0, "jest", idGenKontenerow.generateId(), true);
            Kontener kontener2C = new KontenerCiezki(nadawca2, 0.5, "certyfikat A+", 0.5, 2.0, "jest", idGenKontenerow.generateId(), true);
            Kontener kontener3C = new KontenerCiezki(nadawca, 0.8, "certyfikat A+", 0.2, 1.0, "jest", idGenKontenerow.generateId(), true);
            Kontener kontener1T = new KontenerNaToksyczneSypkie(nadawca3, 3.0, "tak", 4.00, 7.0, "tak", idGenKontenerow.generateId(), true, "sypki", KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.A);
            Kontener kontener2T = new KontenerNaToksyczneSypkie(nadawca2, 3.0, "tak", 4.00, 7.0, "tak", idGenKontenerow.generateId(), true, "sypki", KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.B);
            Kontener kontener3T = new KontenerNaToksyczneSypkie(nadawca, 3.0, "tak", 4.00, 7.0, "tak", idGenKontenerow.generateId(), true, "sypki", KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.C);
            Kontener kontenerCh = new KontenerChlodniczy(nadawca, 3.0, "nie", 1.0, 4.0, "mamy", idGenKontenerow.generateId(), -19.0f);
            Kontener kontenerCh2 = new KontenerChlodniczy(nadawca2, 3.0, "nie", 1.5, 3.5, "mamy", idGenKontenerow.generateId(), -19.0f);
            Kontener kontenerCh3 = new KontenerChlodniczy(nadawca3, 1.0, "nie", 4.0, 5.0, "mamy", idGenKontenerow.generateId(), -19.0f);
            Kontener kontenerW1 = new KontenerNaWybuchowe(nadawca, 3.0, "nie", 4.0, 7.0, "mamy", idGenKontenerow.generateId(), true, 0.67);
            Kontener kontenerW2 = new KontenerNaWybuchowe(nadawca, 4.0, "nie", 4.0, 7.0, "mamy", idGenKontenerow.generateId(), true, 0.67);
            Kontener kontenerW3 = new KontenerNaWybuchowe(nadawca, 1.0, "nie", 4.0, 7.0, "mamy", idGenKontenerow.generateId(), true, 0.67);
            Kontener kontenerW4 = new KontenerNaWybuchowe(nadawca, 3.0, "nie", 4.0, 7.0, "mamy", idGenKontenerow.generateId(), true, 0.67);
            Kontener kontenerTc = new KontenerNaToksyczneCiekle(nadawca, 3.0, "tak", 4.00, 7.0, "tak", idGenKontenerow.generateId(), true, true, KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.C);
            Kontener kontenerTc1 = new KontenerNaToksyczneCiekle(nadawca, 2.0, "tak", 4.00, 6.0, "tak", idGenKontenerow.generateId(), true, true, KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.C);
            Kontener kontenerTc2 = new KontenerNaToksyczneCiekle(nadawca, 1.0, "tak", 1.00, 2.0, "tak", idGenKontenerow.generateId(), true, true, KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.C);
            Kontener kontenerTc3 = new KontenerNaToksyczneCiekle(nadawca, 0.3, "tak", 0.40, 0.7, "tak", idGenKontenerow.generateId(), true, true, KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.C);
            Statek s1 = new Statek("Zibi", "Gdynia", "Hamburg", "Sopot", "Amsterdam", idGenStatkow.generateId(), 6, 22.0, 4, 5, 5);
            Statek s2 = new Statek("Alfa", "Gdańsk", "Hamburg", "Sopot", "Amsterdam", idGenStatkow.generateId(), 5, 25.0, 4, 5, 5);
            Statek s3 = new Statek("Beta", "Sopot", "Hamburg", "Sopot", "Amsterdam", idGenStatkow.generateId(), 7, 20.0, 4, 5, 6);
            Statek s4 = new Statek("Gamma", "Gdynia", "Sopot", "Hamburg", "Amsterdam", idGenStatkow.generateId(), 6, 25.0, 4, 5, 6);
            Statek s5 = new Statek("Delta", "Gdynia", "Hamburg", "Sopot", "Amsterdam", idGenStatkow.generateId(), 6, 20.0, 4, 5, 5);
            port.statkiWPorcie.add(s1);
            port.statkiWPorcie.add(s2);
            port.statkiWPorcie.add(s3);
            port.statkiWPorcie.add(s4);
            port.statkiWPorcie.add(s5);
            magazyn.zaladuj(kontener2T, zegar.getActualDate());
            magazyn.zaladuj(kontener3T, zegar.getActualDate());
            magazyn.zaladuj(kontener1C, zegar.getActualDate());
            magazyn.zaladuj(kontenerW2, zegar.getActualDate());
            magazyn.zaladuj(kontenerW3, zegar.getActualDate());
            s1.zaladuj(kontener1T);
            s1.zaladuj(kontener3C);
            s2.zaladuj(kontenerW1);
            s2.zaladuj(kontenerTc3);
            s3.zaladuj(kontenerCh2);
            s4.zaladuj(kontener2C);
            s4.zaladuj(kontenerTc);
            s4.zaladuj(kontenerTc1);
            s5.zaladuj(kontenerTc2);
            s5.zaladuj(kontenerW4);
            s5.zaladuj(kontenerCh3);
            s5.zaladuj(kontenerCh);
            wystartujAplikacje(magazyn,port,zegar,wagon,idGenStatkow,idGenKontenerow);
        }
    }
    static void wystartujAplikacje(Magazyn magazyn,Port port,Timer zegar,Wagon wagon,IdGenerator idGenStatkow,IdGenerator idGenKontenerow) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (!isInterrupted) {
            wyswietlGlowneMenu();
            try {
            int option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        System.out.println("Koncze prace programu. Do widzenia!");
                        zegar.setFlaga(false);
                        zegar.join();
                        isInterrupted = true;
                        break;
                    }
                    case 2: {
                        System.out.println("(2)Zładuj kontener ze statku do magazynu (wybierz 1) / do wagonu (wybierz 2) ");
                        int wybor = scanner.nextInt();
                        System.out.println("Podaj id kontenera");
                        int idKontenera = scanner.nextInt();
                        int idStatku;
                        Kontener tmpKontener;
                        switch (wybor) {
                            case 1:
                                System.out.println("Podaj id statku:");
                                idStatku = scanner.nextInt();
                                tmpKontener = port.getStatekPrzezId(idStatku).getKontenerDaneID(idKontenera);
                                port.getStatekPrzezId(idStatku).wyladuj(tmpKontener);
                                magazyn.zaladuj(tmpKontener, zegar.getActualDate());
                                break;
                            case 2:
                                System.out.println("Podaj id statku :");
                                idStatku = scanner.nextInt();
                                tmpKontener = port.getStatekPrzezId(idStatku).getKontenerDaneID(idKontenera);
                                port.getStatekPrzezId(idStatku).wyladuj(tmpKontener);
                                wagon.zaladuj(tmpKontener);
                                break;
                            default:
                                System.out.println("Podaj poprawną opcję:");
                                break;
                        }
                    }
                    case 3: {
                        System.out.println("(3)– Załadowania na wskazany statek wskazanego kontenera");
                        System.out.println("Podaj id statku:");
                        int idStatku = scanner.nextInt();
                        System.out.println("Podaj id kontenera:");
                        int idKontenera = scanner.nextInt();
                        Kontener tmpKontener = magazyn.getKontenerPrzezId(idKontenera);
                        magazyn.wyladuj(tmpKontener);
                        port.getStatekPrzezId(idStatku).zaladuj(tmpKontener);
                        break;
                    }
                    case 4: {
                        System.out.println("Podaj id statku :");
                        int idStatku = scanner.nextInt();
                        System.out.println("(4)Wyświetl zawartość statku");
                        port.getStatekPrzezId(idStatku).wyswietlInfo();
                        break;
                    }
                    case 5: {
                        System.out.println("(5)Wyświetl zawartość magazynu");
                        magazyn.wyswietlZawartosc();
                        break;
                    }
                    case 6: {
                        System.out.println("(6)Utylizuj kontener poprzez dodanie go na wagon");
                        System.out.println("Podaj id kontenera:");
                        int idKonteneraDoUtylizacji = scanner.nextInt();
                        magazyn.utylizujKontener(idKonteneraDoUtylizacji, wagon);
                        break;
                    }
                    case 7: {
                        dodajStatek(port, idGenStatkow);
                        break;
                    }
                    case 8: {
                        dodajKontener(idGenKontenerow, magazyn, zegar.getActualDate());
                        break;
                    }
                    case 9: {
                        zapiszStanAplikacji(port, magazyn, wagon, zegar);
                        break;
                    }
                    default: {
                        System.out.println("podaj inną cyfre");
                        break;
                    }
                }
            } catch (Exception e)
            {
                System.out.println("Podano niepoprawne dane, sprobuj ponownie");
            }
        }
    }

    static void wyswietlGlowneMenu() {

        System.out.println("(1) Zakończ program");
        System.out.println("(2) Zładuj kontener ze statku do magazynu/wagonu");
        System.out.println("(3) Załaduj na wskazany statek wskazany kontener");
        System.out.println("(4) Wypisz informacje o statku i znajdujących się na nim kontenerach");
        System.out.println("(5) Wyświetl stan magazynowy ");
        System.out.println("(6) Utylizacja wskazanego kontenera z magazynu ");
        System.out.println("(7) Stworzenie nowego statku o wskazanych parametrach ");
        System.out.println("(8) Stworzenie nowego kontenera wskazanego typu o wskazanych parametrach ");
        System.out.println("(9) Zapisz aktualny stan portu");
        System.out.print("Wybierz swoja opcje: ->");
    }

    private static void zapiszStanAplikacji(Port port, Magazyn magazyn,Wagon wagon,Timer zegar) throws IOException {

        FileWriter fw = new FileWriter(new File(sciezkaDoPliku));
        System.out.println("[OK] Przygotowanie do zapisu, sortowanie kontenerow w magazynie, sortowanie kontenerow na kazdym staku oraz sortowanie stakow w porcie");
        Collections.sort(magazyn.konteneryWMagazynie,Kontener::compareToData);
        Collections.sort(port.statkiWPorcie,Statek::compareTo);
        port.statkiWPorcie.forEach(Statek -> Collections.sort(Statek.listaKontenerowNaStatku,Kontener::compareTo));
        ZapiszStanAction zapiszMagazynPortWagon = new ZapiszStanAction() {
            @Override
            public void zapiszStan() throws IOException {
                fw.write("#Start Magazyn");
                fw.write(magazyn.toString());
                magazyn.konteneryWMagazynie.forEach(kontener-> {
                    try {
                        fw.write(kontener.toString());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });
                fw.write("#Koniec Magazyn"+"\n");
                fw.write("\n"+"#Start Port");
                port.statkiWPorcie.forEach(statek-> {
                    try {
                        fw.write(statek.toString());
                        fw.write("#Przewozone Kontenery na danym statku");
                        statek.listaKontenerowNaStatku.forEach(kontener-> {
                            try {
                                fw.write(kontener.toString());
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                        });
                        fw.write("#To wszystkie Kontenery na danym statku");

                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });
                fw.write("\n#Koniec Port"+"\n");
                fw.write("\n"+"#Start Wagon");
                fw.write(wagon.toString());
                wagon.listaKontenerow.forEach(kontener-> {
                    try {
                        fw.write(kontener.toString());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                });
                fw.write("#Koniec Wagon"+"\n");
                fw.write("\n"+"#Start Zegar");
                fw.write(zegar.toString());
                fw.write("\n"+"#Koniec Zegar"+"\n");
            }
        };
        zapiszMagazynPortWagon.zapiszStan();
        System.out.println("[OK] Zapisano poprawnie stan aplikacji do pliku: " + sciezkaDoPliku);
        fw.close();
    }

    private static Timer wczytajzegar(FileReader fr, Magazyn magazyn, Wagon wagon) {
        Timer zegar= new Timer(magazyn,wagon);
        BufferedReader reader = new BufferedReader(fr);
        String line = "";
        try{
            while (!line.equals("#Start Zegar")) {
                line = reader.readLine();
            }
            String pomin1 = reader.readLine();
            LocalDate aktualnyCzas=LocalDate.parse(reader.readLine().split(":->")[1]);
            int counter=Integer.parseInt(reader.readLine().split(":->")[1]);
            zegar.setActualTime(aktualnyCzas);
            zegar.setCounter(counter);
            System.out.println("[OK] Wczytano poprawnie Zegar!");
            reader.close();
        }catch (IOException exception) {
            exception.printStackTrace();
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return zegar;
    }

    private static Wagon wczytajWagon(FileReader fr) {
        Wagon wagon = null;
        try {
            BufferedReader reader = new BufferedReader(fr);
            String line = "";
            while (!line.equals("#Start Wagon")) {
                line = reader.readLine();
            }
            String pomin2 = reader.readLine(); // Wagon:
            wagon = new Wagon();
            line = "";
            while (!line.equals("#Koniec Wagon")) {
                line = reader.readLine();
                if(line.equals(""))
                {
                    continue;
                }else if(line.equals("KontenerChlodniczy: "))
                {
                    String pomin3 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin4 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    float temperaturaChlodzenia=Float.parseFloat(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    wagon.zaladuj(new KontenerChlodniczy(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,temperaturaChlodzenia));
                } else if(line.equals("KontenerCiezki: "))
                {
                    String pomin5 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin6 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    wagon.zaladuj(new KontenerCiezki(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony));
                }else if(line.equals("KontenerNaWybuchowe: "))
                {
                    String pomin7 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin8 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    double gestosc=Double.parseDouble(reader.readLine().split(":->")[1]);
                    LocalDate dataKoncaPrzechowywania=LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    wagon.zaladuj(new KontenerNaWybuchowe(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,gestosc));
                }
                else if(line.equals("KontenerNaToksyczneCiekle: "))
                {
                    String pomin9 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin10 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                    boolean czyPosiadaBezpiecznik=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    LocalDate dataKoncaPrzechowywania=LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    wagon.zaladuj(new KontenerNaToksyczneCiekle(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,czyPosiadaBezpiecznik,kategoriaToksycznosci));
                }else if(line.equals("KontenerNaToksyczneSypkie: "))
                {
                    String pomin11 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin12 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                    String jakiRodzajGranulatu=reader.readLine().split(":->")[1];
                    LocalDate dataKoncaPrzechowywania=LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    wagon.zaladuj(new KontenerNaToksyczneSypkie(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,jakiRodzajGranulatu,kategoriaToksycznosci));
                }
                else{
                    continue;
                }
            }
            System.out.println("[OK] Wczytano poprawnie Wagon!");
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return wagon;
    }

    private static Port wczytajPort(FileReader fr) {
        Port port = new Port();
        try {
            BufferedReader reader = new BufferedReader(fr);
            String line ="";
            while (!line.equals("#Start Port")) {
                line=reader.readLine();
            }
            line = reader.readLine();
            while (!line.equals("#Koniec Port")) {
                String nazwaStatku = reader.readLine().split(":->")[1];
                String portMacierzysty = reader.readLine().split(":->")[1];
                String lokalizacjaZrodlowa = reader.readLine().split(":->")[1];
                String lokalizacjaTransportu = reader.readLine().split(":->")[1];
                String lokalizacjaDocelowa = reader.readLine().split(":->")[1];
                int nrIdentyfikacyjnystatku=Integer.parseInt(reader.readLine().split(":->")[1]);
                int pojemnosc=Integer.parseInt(reader.readLine().split(":->")[1]);
                double nosnosc=Double.parseDouble(reader.readLine().split(":->")[1]);
                int maxLiczbaCiezkich=Integer.parseInt(reader.readLine().split(":->")[1]);
                int maxLiczbaElektro=Integer.parseInt(reader.readLine().split(":->")[1]);
                int maxLiczbaToksWybuch=Integer.parseInt(reader.readLine().split(":->")[1]);
                int dostepnaPojemnosc=Integer.parseInt(reader.readLine().split(":->")[1]);
                Statek s = new Statek(nazwaStatku,portMacierzysty,lokalizacjaZrodlowa,lokalizacjaTransportu,lokalizacjaDocelowa,nrIdentyfikacyjnystatku,pojemnosc,nosnosc,maxLiczbaCiezkich,maxLiczbaElektro,maxLiczbaToksWybuch);
                while(!line.equals("#To wszystkie Kontenery na danym statku")) {
                    line = reader.readLine();
                    if (line.equals("")) {
                        continue;
                    } else if (line.equals("KontenerChlodniczy: ")) {
                        String pomin1 = reader.readLine(); // Nadawca:->
                        String imie = reader.readLine().split(":->")[1];
                        String nazwisko = reader.readLine().split(":->")[1];
                        String pesel = reader.readLine().split(":->")[1];
                        String pomin2 = reader.readLine(); //  Adres:->
                        String adres = reader.readLine();
                        String ulica = adres.split(":->")[1].split(" ")[0];
                        String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                        String miasto = adres.split(":->")[3].split(" ")[0];
                        int nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                        String dataUrodzenia = reader.readLine().split(":->")[1];
                        double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String zabezpieczenia = reader.readLine().split(":->")[1];
                        double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                        int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                        LocalDate czasPrzybyciaDoMagazynu;
                        String SczasPrzybyciaDoMagazynu = reader.readLine().split(":->")[1];
                        if (SczasPrzybyciaDoMagazynu.compareTo("null")>0) {
                            czasPrzybyciaDoMagazynu = LocalDate.parse(SczasPrzybyciaDoMagazynu);
                        }
                        float temperaturaChlodzenia = Float.parseFloat(reader.readLine().split(":->")[1]);
                        Adres tmpadres = new Adres(ulica, kodPocztowy, miasto, nrDomuMieszkania);
                        Nadawca nadawca = new Nadawca(imie, nazwisko, pesel, tmpadres, dataUrodzenia);
                        s.zaladuj(new KontenerChlodniczy(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjaOCertyfikatach, nrIdentyfikacyjny, temperaturaChlodzenia));
                    } else if (line.equals("KontenerCiezki: ")) {
                        String pomin3 = reader.readLine(); // Nadawca:->
                        String imie = reader.readLine().split(":->")[1];
                        String nazwisko = reader.readLine().split(":->")[1];
                        String pesel = reader.readLine().split(":->")[1];
                        String pomin4 = reader.readLine(); // Adres:->
                        String adres = reader.readLine();
                        String ulica = adres.split(":->")[1].split(" ")[0];
                        String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                        String miasto = adres.split(":->")[3].split(" ")[0];
                        int nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                        String dataUrodzenia = reader.readLine().split(":->")[1];
                        double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String zabezpieczenia = reader.readLine().split(":->")[1];
                        double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                        int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                        LocalDate czasPrzybyciaDoMagazynu;
                        String SczasPrzybyciaDoMagazynu = reader.readLine().split(":->")[1];
                        if (SczasPrzybyciaDoMagazynu.compareTo("null")>0) {
                            czasPrzybyciaDoMagazynu = LocalDate.parse(SczasPrzybyciaDoMagazynu);
                        }
                        boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                        Adres tmpadres = new Adres(ulica, kodPocztowy, miasto, nrDomuMieszkania);
                        Nadawca nadawca = new Nadawca(imie, nazwisko, pesel, tmpadres, dataUrodzenia);
                        s.zaladuj(new KontenerCiezki(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjaOCertyfikatach, nrIdentyfikacyjny, czyWzmocniony));
                    } else if (line.equals("KontenerNaWybuchowe: ")) {
                        String pomin5 = reader.readLine(); // Nadawca:->
                        String imie = reader.readLine().split(":->")[1];
                        String nazwisko = reader.readLine().split(":->")[1];
                        String pesel = reader.readLine().split(":->")[1];
                        String pomin6 = reader.readLine(); // Adres:->
                        String adres = reader.readLine();
                        String ulica = adres.split(":->")[1].split(" ")[0];
                        String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                        String miasto = adres.split(":->")[3].split(" ")[0];
                        int nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                        String dataUrodzenia = reader.readLine().split(":->")[1];
                        double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String zabezpieczenia = reader.readLine().split(":->")[1];
                        double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                        int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                        LocalDate czasPrzybyciaDoMagazynu;
                        String SczasPrzybyciaDoMagazynu = reader.readLine().split(":->")[1];
                        if (SczasPrzybyciaDoMagazynu.compareTo("null")>0) {
                            czasPrzybyciaDoMagazynu = LocalDate.parse(SczasPrzybyciaDoMagazynu);
                        }
                        boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                        double gestosc = Double.parseDouble(reader.readLine().split(":->")[1]);
                        LocalDate dataKoncaPrzechowywania;
                        String SdataKoncaPrzechowywania = reader.readLine().split(":->")[1];
                        if (SdataKoncaPrzechowywania.compareTo("null")>0)
                        {
                            dataKoncaPrzechowywania = LocalDate.parse(SdataKoncaPrzechowywania);
                        }
                        Adres tmpadres = new Adres(ulica, kodPocztowy, miasto, nrDomuMieszkania);
                        Nadawca nadawca = new Nadawca(imie, nazwisko, pesel, tmpadres, dataUrodzenia);
                        s.zaladuj(new KontenerNaWybuchowe(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjaOCertyfikatach, nrIdentyfikacyjny, czyWzmocniony, gestosc));
                    } else if (line.equals("KontenerNaToksyczneCiekle: ")) {
                        String pomin6 = reader.readLine(); // Nadawca:->
                        String imie = reader.readLine().split(":->")[1];
                        String nazwisko = reader.readLine().split(":->")[1];
                        String pesel = reader.readLine().split(":->")[1];
                        String pomin7 = reader.readLine(); // Adres:->
                        String adres = reader.readLine();
                        String ulica = adres.split(":->")[1].split(" ")[0];
                        String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                        String miasto = adres.split(":->")[3].split(" ")[0];
                        int nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                        String dataUrodzenia = reader.readLine().split(":->")[1];
                        double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String zabezpieczenia = reader.readLine().split(":->")[1];
                        double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                        int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                        LocalDate czasPrzybyciaDoMagazynu;
                        String SczasPrzybyciaDoMagazynu = reader.readLine().split(":->")[1];
                        if (SczasPrzybyciaDoMagazynu.compareTo("null")>0) {
                            czasPrzybyciaDoMagazynu = LocalDate.parse(SczasPrzybyciaDoMagazynu);
                        }
                        boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                        KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                        boolean czyPosiadaBezpiecznik = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                        LocalDate dataKoncaPrzechowywania;
                        String SdataKoncaPrzechowywania = reader.readLine().split(":->")[1];
                        if (SdataKoncaPrzechowywania.compareTo("null")>0)
                        {
                            dataKoncaPrzechowywania = LocalDate.parse(SdataKoncaPrzechowywania);
                        }
                        Adres tmpadres = new Adres(ulica, kodPocztowy, miasto, nrDomuMieszkania);
                        Nadawca nadawca = new Nadawca(imie, nazwisko, pesel, tmpadres, dataUrodzenia);
                        s.zaladuj(new KontenerNaToksyczneCiekle(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjaOCertyfikatach, nrIdentyfikacyjny, czyWzmocniony, czyPosiadaBezpiecznik, kategoriaToksycznosci));
                    } else if (line.equals("KontenerNaToksyczneSypkie: ")) {
                        String pomin8 = reader.readLine(); // Nadawca:->
                        String imie = reader.readLine().split(":->")[1];
                        String nazwisko = reader.readLine().split(":->")[1];
                        String pesel = reader.readLine().split(":->")[1];
                        String pomin9 = reader.readLine(); // Adres:->
                        String adres = reader.readLine();
                        String ulica = adres.split(":->")[1].split(" ")[0];
                        String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                        String miasto = adres.split(":->")[3].split(" ")[0];
                        int nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                        String dataUrodzenia = reader.readLine().split(":->")[1];
                        double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String zabezpieczenia = reader.readLine().split(":->")[1];
                        double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                        String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                        int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                        LocalDate czasPrzybyciaDoMagazynu;
                        String SczasPrzybyciaDoMagazynu = reader.readLine().split(":->")[1];
                        if (SczasPrzybyciaDoMagazynu.compareTo("null")>0) {
                            czasPrzybyciaDoMagazynu = LocalDate.parse(SczasPrzybyciaDoMagazynu);
                        }
                        boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                        KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                        String jakiRodzajGranulatu = reader.readLine().split(":->")[1];
                        LocalDate dataKoncaPrzechowywania;
                        String SdataKoncaPrzechowywania = reader.readLine().split(":->")[1];
                        if (SdataKoncaPrzechowywania.compareTo("null")>0)
                        {
                            dataKoncaPrzechowywania = LocalDate.parse(SdataKoncaPrzechowywania);
                        }
                        Adres tmpadres = new Adres(ulica, kodPocztowy, miasto, nrDomuMieszkania);
                        Nadawca nadawca = new Nadawca(imie, nazwisko, pesel, tmpadres, dataUrodzenia);
                        s.zaladuj(new KontenerNaToksyczneSypkie(nadawca, tara, zabezpieczenia, wagaNetto, wagaBrutto, informacjaOCertyfikatach, nrIdentyfikacyjny, czyWzmocniony, jakiRodzajGranulatu, kategoriaToksycznosci));
                    }
                }
                port.statkiWPorcie.add(s);
                line = reader.readLine();
            }
            System.out.println("[OK] Wczytano poprawnie Port!");
            reader.close();
    }catch (IOException e) {
        e.printStackTrace();
    } catch (Exception exception) {
        exception.printStackTrace();
    }
        return port;
}

    private static Magazyn wczytajMagazyn(FileReader fr) {
        int maxNrOfKontenery;
        int dostepnaPojemnosc;
        Magazyn magazyn = null;
        try {
            BufferedReader reader = new BufferedReader(fr);
            String pomin1 = reader.readLine(); // #Start Magazyn
            String pomin2 = reader.readLine(); // Magazyn:
            String SmaxNrOfKontenery = reader.readLine();
            String SdostepnaPojemnosc = reader.readLine();
            maxNrOfKontenery = Integer.parseInt(SmaxNrOfKontenery.split(":->")[1]);
            dostepnaPojemnosc = Integer.parseInt(SdostepnaPojemnosc.split(":->")[1]);
            magazyn = new Magazyn(maxNrOfKontenery);
            magazyn.setDostepnaPojemnosc(dostepnaPojemnosc);
            String line ="";
            while (!line.equals("#Koniec Magazyn")) {
                line = reader.readLine();
                if(line.equals(""))
                {
                        continue;
                }else if(line.equals("KontenerChlodniczy: "))
                {
                    String pomin3 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin4 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia = reader.readLine().split(":->")[1];
                    double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia = reader.readLine().split(":->")[1];
                    double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu = LocalDate.parse(reader.readLine().split(":->")[1]);
                    float temperaturaChlodzenia = Float.parseFloat(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    magazyn.zaladuj(new KontenerChlodniczy(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,temperaturaChlodzenia),czasPrzybyciaDoMagazynu);
                } else if(line.equals("KontenerCiezki: "))
                {
                    String pomin5 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin6 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    magazyn.zaladuj(new KontenerCiezki(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony),czasPrzybyciaDoMagazynu);
                }else if(line.equals("KontenerNaWybuchowe: "))
                {
                    String pomin7 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin8 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia=reader.readLine().split(":->")[1];
                    double wagaNetto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto=Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach=reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny=Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu=LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony=Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    double gestosc=Double.parseDouble(reader.readLine().split(":->")[1]);
                    LocalDate dataKoncaPrzechowywania=LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    magazyn.zaladuj(new KontenerNaWybuchowe(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,gestosc),czasPrzybyciaDoMagazynu);
                }
                else if(line.equals("KontenerNaToksyczneCiekle: "))
                {
                    String pomin9 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin10 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia = reader.readLine().split(":->")[1];
                    double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu = LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                    boolean czyPosiadaBezpiecznik = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    LocalDate dataKoncaPrzechowywania = LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    magazyn.zaladuj(new KontenerNaToksyczneCiekle(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,czyPosiadaBezpiecznik,kategoriaToksycznosci),czasPrzybyciaDoMagazynu);
                }else if(line.equals("KontenerNaToksyczneSypkie: "))
                {
                    String pomin9 = reader.readLine();
                    String imie = reader.readLine().split(":->")[1];
                    String nazwisko = reader.readLine().split(":->")[1];
                    String pesel = reader.readLine().split(":->")[1];
                    String pomin10 = reader.readLine();
                    String adres = reader.readLine();
                    String ulica = adres.split(":->")[1].split(" ")[0];
                    String kodPocztowy = adres.split(":->")[2].split(" ")[0];
                    String miasto = adres.split(":->")[3].split(" ")[0];
                    int  nrDomuMieszkania = Integer.parseInt(adres.split(":->")[4].split(" ")[0]);
                    String dataUrodzenia= reader.readLine().split(":->")[1];
                    double tara = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String zabezpieczenia = reader.readLine().split(":->")[1];
                    double wagaNetto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    double wagaBrutto = Double.parseDouble(reader.readLine().split(":->")[1]);
                    String informacjaOCertyfikatach = reader.readLine().split(":->")[1];
                    int nrIdentyfikacyjny = Integer.parseInt(reader.readLine().split(":->")[1]);
                    LocalDate czasPrzybyciaDoMagazynu = LocalDate.parse(reader.readLine().split(":->")[1]);
                    boolean czyWzmocniony = Boolean.parseBoolean(reader.readLine().split(":->")[1]);
                    KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoriaToksycznosci = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(reader.readLine().split(":->")[1]);
                    String jakiRodzajGranulatu=reader.readLine().split(":->")[1];
                    LocalDate dataKoncaPrzechowywania=LocalDate.parse(reader.readLine().split(":->")[1]);
                    Adres tmpadres = new Adres(ulica,kodPocztowy,miasto,nrDomuMieszkania);
                    Nadawca nadawca = new Nadawca(imie,nazwisko,pesel,tmpadres,dataUrodzenia);
                    magazyn.zaladuj(new KontenerNaToksyczneSypkie(nadawca,tara,zabezpieczenia,wagaNetto,wagaBrutto,informacjaOCertyfikatach,nrIdentyfikacyjny,czyWzmocniony,jakiRodzajGranulatu,kategoriaToksycznosci),czasPrzybyciaDoMagazynu);
                }
                else{
                    continue;
                }
            }
            System.out.println("[OK] Wczytano poprawnie Magazyn!");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return magazyn;
    }

    private static void dodajStatek(Port port,IdGenerator idGenStatkow) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("(7)Dodaj statek");
            System.out.println("Podaj nazwe statku (np:DarMlodziezy, Posejdon):");
            String nazwa = scanner.next();
            System.out.println("Podaj port_macierzysty (np:Gdansk, Gdynia):");
            String port_macierzysty = scanner.next();
            System.out.println("Podaj lokalizacja_zrodlowa (np:Gdansk, Gdynia):");
            String lokalizacja_zrodlowa = scanner.next();
            System.out.println("Podaj lokalizacja_transportu (np:Lublin, Szczecin):");
            String lokalizacja_transportu = scanner.next();
            System.out.println("Podaj lokalizacja_docelowa (np:Lublin, Szczecin):");
            String lokalizacja_docelowa = scanner.next();
            System.out.println("Podaj pojemnosc (np:10):");
            int pojemnosc = scanner.nextInt();
            System.out.println("Podaj nosnosc (np 5,1):");
            double nosnosc = scanner.nextDouble();
            System.out.println("Podaj maxLiczbaCiezkich:");
            int maxLiczbaCiezkich = scanner.nextInt();
            System.out.println("Podaj maxLiczbaElektro:");
            int maxLiczbaElektro = scanner.nextInt();
            System.out.println("Podaj maxLiczbaToksWybuch:");
            int maxLiczbaToksWybuch = scanner.nextInt();
            port.statkiWPorcie.add(new Statek(nazwa, port_macierzysty, lokalizacja_zrodlowa, lokalizacja_transportu, lokalizacja_docelowa,
                    idGenStatkow.generateId(), pojemnosc, nosnosc, maxLiczbaCiezkich, maxLiczbaElektro, maxLiczbaToksWybuch));
        } catch (Exception e)
        {
            System.out.println("Podales niewlasciwy format danych wejsciowych. Sprobuj ponownie");
        }
    }

    private static void dodajKontener(IdGenerator idGenKontenerow,Magazyn magazyn,LocalDate aktualnaData)  {
        Scanner scanner = new Scanner(System.in);
        System.out.println("(8) Dodaj kontener)");
        System.out.println("Podaj jakie zabezpieczenie (dowolne informacje)");
        try {
            String zabezpieczenia = scanner.next();
            System.out.println("Podaj tarę (np: 4,1 6,2): ");
            double tara = scanner.nextDouble();
            while (tara <= 0) {
                System.out.println("Nieprawidlowa wartosc.Podaj tarę : ");
                tara = scanner.nextDouble();
            }
            System.out.println("Podaj wagę netto : ");
            double wagaNetto = scanner.nextDouble();
            while (wagaNetto <= 0) {
                System.out.println("Nieprawidlowa wartosc.Podaj wagę netto : ");
                wagaNetto = scanner.nextDouble();
            }
            System.out.println("Podaj wagę brutto : ");
            double wagaBrutto = wagaNetto + tara;
            System.out.println("Podaj informacje o certyfikatach : ");
            String infoCertyfikaty = scanner.next();
            System.out.println("Podaj jaki typ kontenera: (1) - ciężki, (2) - chłodniczy, (3) - na toksyczne sypkie, (4) - na toksyczne ciekłe, (5) - na wybuchowe");
            int nrTypu = scanner.nextInt();
            KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA kategoria;
            boolean wzmocnionyToks;
            switch (nrTypu) {
                case 1: {
                    System.out.println("Czy wzmocniony : (true/false)");
                    wzmocnionyToks = scanner.nextBoolean();
                    magazyn.zaladuj(new KontenerCiezki(podajUtworzNadawceKontenera(), tara, zabezpieczenia, wagaNetto, wagaBrutto, infoCertyfikaty, idGenKontenerow.generateId(), wzmocnionyToks), aktualnaData);
                    break;
                }
                case 2: {
                    System.out.println("Podaj temperaturę chłodzenia : ");
                    float tempChlodzenia = scanner.nextFloat();
                    magazyn.zaladuj(new KontenerChlodniczy(podajUtworzNadawceKontenera(), tara, zabezpieczenia, wagaNetto, wagaBrutto, infoCertyfikaty, idGenKontenerow.generateId(), tempChlodzenia), aktualnaData);
                    break;
                }
                case 3: {
                    System.out.println("Jaki rodzaj granulatu : ");
                    String jakiRodzajGranulatu = scanner.next();
                    System.out.println("Czy wzmocniony : (true/false)");
                    wzmocnionyToks = scanner.nextBoolean();
                    System.out.println("KategoriaToksycznosci : (A/B/C)");
                    kategoria = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(scanner.next());
                    magazyn.zaladuj(new KontenerNaToksyczneSypkie(podajUtworzNadawceKontenera(), tara, zabezpieczenia, wagaNetto, wagaBrutto, infoCertyfikaty, idGenKontenerow.generateId(), wzmocnionyToks, jakiRodzajGranulatu, kategoria), aktualnaData);
                    break;
                }
                case 4: {
                    System.out.println("Czy wzmocniony : (true/false)");
                    wzmocnionyToks = scanner.nextBoolean();
                    System.out.println("Czy posiada bezpiecznik : (true/false)");
                    boolean czyPosiadaBezpiecznik = scanner.nextBoolean();
                    System.out.println("KategoriaToksycznosci : (A/B/C)");
                    kategoria = KontenerNaToksyczne.TOKSYCZNOSC_KATEGORIA.valueOf(scanner.next());
                    magazyn.zaladuj(new KontenerNaToksyczneCiekle(podajUtworzNadawceKontenera(), tara, zabezpieczenia, wagaNetto, wagaBrutto, infoCertyfikaty, idGenKontenerow.generateId(), wzmocnionyToks, czyPosiadaBezpiecznik, kategoria), aktualnaData);
                    break;
                }
                case 5: {
                    System.out.println("Czy wzmocniony : (true/false)");
                    wzmocnionyToks = scanner.nextBoolean();
                    System.out.println("Podaj gęstość : ");
                    double gestosc = scanner.nextDouble();
                    magazyn.zaladuj(new KontenerNaWybuchowe(podajUtworzNadawceKontenera(), tara, zabezpieczenia, wagaNetto, wagaBrutto, infoCertyfikaty, idGenKontenerow.generateId(), wzmocnionyToks, gestosc), aktualnaData);
                    break;
                }
            }
        }catch (Exception e)
        {
            System.out.println("Podales niewlasciwy format danych wejsciowych. Sprobuj ponownie");
        }
    }

    private static Nadawca podajUtworzNadawceKontenera(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imie nadawcy");
        String imie = scanner.next();
        System.out.println("Podaj nazwisko nadawcy");
        String nazwisko = scanner.next();
        System.out.println("Podaj pesel nadawcy");
        String pesel = scanner.next();
        System.out.println("Podaj ulica nadawcy");
        String ulica =scanner.next();
        System.out.println("Podaj kod pocztowy nadawcy");
        String kodPocztowy =scanner.next();
        System.out.println("Podaj miasto nadawcy");
        String miasto =scanner.next();
        System.out.println("Podaj nrDomu nadawcy");
        int nrDomu =scanner.nextInt();
        Adres adres = new Adres(ulica,kodPocztowy,miasto,nrDomu);
        String data_urodzenia = scanner.next();
        Nadawca danyNadawca = new Nadawca(imie,nazwisko,pesel,adres,data_urodzenia);
        return danyNadawca;
    }


}
