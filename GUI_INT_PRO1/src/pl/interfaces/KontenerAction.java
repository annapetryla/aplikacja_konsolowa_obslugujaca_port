package pl.interfaces;

import pl.Kontener;

import java.time.LocalDate;
import java.util.ArrayList;

public interface KontenerAction {

    default void zaladuj(Kontener kontener, LocalDate date) throws Exception{};

    void wyladuj(Kontener kontener) throws Exception;

    default void utylizujKontener(int idKontener, ArrayList<Kontener> kontenery){};
}