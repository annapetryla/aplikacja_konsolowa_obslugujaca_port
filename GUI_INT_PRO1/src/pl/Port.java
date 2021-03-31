package pl;

import pl.interfaces.ZapiszStanAction;

import java.util.ArrayList;

public class Port implements ZapiszStanAction {

    ArrayList<Statek> statkiWPorcie = new ArrayList<>();

    protected void wyswietlZawartosc(){

        System.out.println(statkiWPorcie.toString());

    }
    protected Statek getStatekPrzezId(int nrIdentyfikacyjny) {
        Statek tmp = null;
        for(Statek statek : statkiWPorcie) {
            tmp = (statek.nrIdentyfikacyjny == nrIdentyfikacyjny? statek : null);
        }
        return tmp;
    }
}
