package pl.interfaces;

import pl.Kontener;
import pl.interfaces.KontenerAction;

public interface ShipAction extends KontenerAction {

    void wyslijDoPortu();

    @Override
    void wyladuj(Kontener kontener) throws Exception;

    void zaladuj(Kontener kontener) throws Exception;

}
