# aplikacja_konsolowa_obslugujaca_port

=====================================================

Wazna czescia projektu jest wykorzystanie miedzy innymi: dziedziczenia, kolekcji, interfejsów lub klas abstrakcyjnych, lambda-wyrazen, typów generycznych, dodatkowych funkcjonalnosci lub struktur oraz innych elementów charakterystycznych

======================================================

Napisz aplikacje, która bedzie słuzyła do obsługi logistycznej morskiego portu w zakresie
terminalu przeładunkowego dla statków transportowych przystosowanych do przewozu kontenerów.
Aplikacja bedzie słuzyła do załadunku i rozładunku kontenerów z/na statek. W przypadku
rozładunku kontenery moga trafic do magazynu lub bezposrednio na wagon transportowy.
Kazdy statek ma inna pojemnosc i nosnosc definiowana przez:

• maksymalna liczbe kontenerów z ładunkiem toksycznym lub wybuchowym mozliwych do
załadowania w ramach statku
• maksymalna liczbe ciezkich kontenerów
• maksymalna liczbe kontenerów wymagajacych podłaczenia do sieci elektrycznej
• maksymalna liczba wszystkich kontenerów
• maksymalna ładownosc wagowa statku

Dodatkowo kazdy statek posiada podstawowe informacje o sobie (nazwe, port macierzysty,
lokalizacja zródłowa transportu oraz docelowa). Kazdy statek równiez posiada swój unikatowy
numer identyfikacyjny nadawany automatycznie podczas tworzenia obiektu.
W przypadku ładownosci kontenerów toksycznych i wybuchowych - wartosc ta jest wspólna
dla obu typów kontenerów, co oznacza, iz jesli w statku zdefiniowano np. w przypadku trzech
miejsc na kontenery z materiałem wybuchowym lub toksycznym, to miejsca te moga byc załadowane
odpowiednio przez:

• 3 kontenery z materiałami wybuchowymi

• 2 kontenery z materiałami wybuchowymi i 1 kontener z materiałami toksycznymi

• 1 kontener z materiałami wybuchowymi i 2 kontenery z materiałami toksycznymi

• 3 kontenery z materiałami toksycznymi

Kazdy z typów kontenerów posiada inny zestaw cech (np. nadawca, tara, zabezpieczenia,
waga netto, waga brutto, informacje o certyfikatach, itp.). W przypadku kazdego typu kontenera
nalezy wymyslic co najmniej jedna niepowtarzajaca sie i unikalna ceche na wskros przez
wszystkie typy kontenerów. Dodatkowo kazdy kontener posiada swój unikatowy numer identyfikacyjny
nadawany automatycznie podczas tworzenia obiektu.

Mamy do dyspozycji miedzy innymi:

• kontener podstawowy,

• kontener ciezki, bedacy rodzajem kontenera podstawowego,

• kontener chłodniczy, bedacy rodzajem kontenera ciezkiego, wymagajacy podłaczenia do
sieci elektrycznej statku

• kontener na materiały ciekłe, bedacy rodzajem kontenera podstawowego

• kontener ma materiały wybuchowe, bedacy rodzajem kontenera ciezkiego

• kontener na materiały toksyczne, bedacy rodzajem kontenera ciezkiego, który dostepny
jest w dwóch wersjach: kontener na toksyczne materiały sypkie oraz kontener na ciekłe
materiały toksyczne, który nie tylko jest rodzajem kontenera ciezkiego, ale równiez posiada
cechy kontenera na materiały ciekłe.

W ramach statku jestesmy ograniczeni nie tylko maksymalna liczba kontenerów, ale równiez
maksymalna ładownoscia wagowa statku, w zwiazku z czym przed załadowaniem kolejnego
kontenera musimy byc pewni, czy kolejny ładunek nie przekroczy dopuszczalnej bezpiecznej nosnosci
statku.

W ramach aplikacji musimy miec mozliwosc z poziomu menu stworzenia nowego statku oraz
kontenerów kazdego typu, załadowania kontenera na statek oraz rozładowania kontenera na
wagon lub do magazynu.

W sytuacji, gdy rozładowujemy kontener ma wagon kolejowy, zakładamy, ze jeden skład moze
pomiescic 10 kontenerów. Po zapełnieniu tego, nalezy odczekac 30 sekund na odjazd aktualnego
składu i przyjazd kolejnego.

Magazyn przeładunkowy posiada pewna ograniczona liczbe kontenerów jakie moze pomiescic,
definiowana podczas tworzenia obiektu typu Magazyn.

W ramach magazynu wolno przechowywac
kazdy typ kontenera, jednak z zastrzezeniem, iz kontenery z materiałami wybuchowymi
moga byc magazynowane jedynie przez 5 dni, kontenery z płynnymi materiałami toksycznymi
przez 10 dni, zas kontenery z sypkimi materiałami toksycznymi maksymalnie przez przez 14 dni.

W zwiazku z powyzszym, nalezy zaimplementowac mechanizm upływajacego czasu za posrednictwem
watków.

Watek powinien co 5 sekund przesuwac date o 1 dzien do przodu, symulujac
upływ czasu. Równolegle powinny byc sprawdzane kwestie magazynowe i w przypadku,
gdyby magazynowany kontener przekraczałby maksymalny czas magazynowania, zostawałby on
w zwiazku z decyzjami celniczymi dot. bezpieczenstwa składowania i magazynowania towarów
utylizowany poprzez wywiezienie z terenu portu, a nastepnie zapomniany z punku widzenia aplikacji.

Równiez nalezy zaimplementowac odliczanie 30 sekund po zapełnieniu składu kolejowego
do przyjazdu kolejnego składu, umozliwiajacego dalsze rozładowywanie kontenerów na wagony.

W sytuacji, gdy kontener zostanie zutylizowany, nadawca otrzymuje ostrzezenie w postaci
obiektu wyjatku typu IrresponsibleSenderWithDangerousGoods zawierajacego informacje
dot. kontenera, tj. daty przybycia do magazynu, daty utylizacji oraz unikalnego numeru
identyfikacyjnego).
W przypadku, gdy nadawca uzbiera w ramach dostaw dwa ostrzezenia, to
w przypadku kolejnych kontenerów wymagajacych magazynowania, takowy towar nie zostanie
przyjety i zostanie ma statku transportowym z nakazem odesłania do nadawcy.
Nadawca posiada ponad wymienione wczesniej informacje takie dane jak imie, nazwisko, pesel,
adres, date urodzenia.
W ramach kazdego statku musimy zapewnic mozliwosc rozładowania i załadowania kontenera,
jak i wypuszczenia statku w dalsza droge z portu. Analogicznie w przypadku magazynu
musimy zapewnic co najmniej metody umozliwiajace zmagazynowanie kontenera, wyjecie kon-
tenera z magazynu i załadowanie na wskazany statek, sprawdzenie aktualnego stanu magazynowego
wyswietlenie zawartosci magazynu oraz manualna utylizacje wskazanego kontenera.
Nalezy zapewnic trwałosc aplikacji, tj. umozliwic zapis aktualnego stanu całego portu do
pliku tekstowego (z uwzglednieniem wszystkich informacji z aplikacji, takich jak m.in. dane
nadawców, statków, magazynu, kontenerów oraz kompletu informacji o nich). 
Zapisane w pliku
powinny byc zapisane przejrzyscie i czytelnie dla człowieka z zachowaniem ponizszych reguł:

• Kontenery znajdujace sie na statkach powinny byc posortowane rosnaco według wagi.

• Statki powinny byc posortowane malejaco wzgledem nazwy statku

• Kontenery magazynowane powinny byc posortowane malejaco Wzgledem daty rozpoczecia
magazynowania (od najdłuzej magazynowanych do najkrócej), a w sytuacji, gdy czas
magazynowania bedzie taki sam, to kolejnym kryterium sortujacym jest nazwa nadawcy.
Nie wolno korzystac z serializacji!
Zapisane dane do pliku musza byc wczytywane podczas kolejnego uruchomienia aplikacji,
aby mozna było rozpoczac prace od ostatniego zapisanego stanu.
W programie nalezy obsłuzyc wszystkie powstajace wyjatki i odpowiednio zakomunikowac
uzytkownikowi nastepujaca sytuacje bez potrzeby przerywania u uruchamiania na nowo programu.
Zasada działania programu:

• W metodzie main nalezy utworzyc port z co najmniej piecioma statkami róznego róznego
rodzaju załadowanymi róznymi kontenerami. Dodatkowo nalezy w magazynie umiescic
kilka kontenerów róznego typu i rodzaju.

• Po uruchomieniu programu, uzytkownik powinien miec mozliwosc za posrednictwem konsoli
polecen wywołania wszystkich wspomnianych funkcjonalnosci. Sa to m.in:

– Zakonczenia programu w dowolnym momencie

– Zładowania wskazanego kontenera ze statku na wagon kolejowy/do magazynu

– Załadowania na wskazany statek wskazanego kontenera

– Czytelnego wypisania informacji o statku i znajdujacych sie na nim kontenerach

– Czytelnego wyswietlenia stanu magazynowego wraz z czasami pozostałymi do utylizacji
kontenerów przechowujacych materiały wybuchowe lub materiały toksyczne

– Utylizacji wskazanego kontenera z magazynu

– Stworzenia nowego statku o wskazanych parametrach

– Stworzenia nowego kontenera wskazanego typu o wskazanych parametrach

– wykonania polecenia zapisujacego aktualny stan portu
