package pl.exceptions;

import java.time.LocalDate;

public class IrresponsibleSenderWithDangerousGoods extends Throwable {

    protected final LocalDate dataPrzybycia;
    protected final LocalDate dataUtylizacji;
    protected int unikalneID;

    public IrresponsibleSenderWithDangerousGoods(LocalDate dataPrzybycia, LocalDate dataUtylizacji,int unikalneID) {
        this.dataPrzybycia = dataPrzybycia;
        this.dataUtylizacji = dataUtylizacji;
        this.unikalneID = unikalneID;
    }
}
