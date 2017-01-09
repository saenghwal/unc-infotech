package netcracker.unc.logic.impl;

import netcracker.unc.logic.FinanceInformation;
import netcracker.unc.logic.interfaces.FinanceInfoBuilder;

public class DbFinanceInfoBuilder implements FinanceInfoBuilder {
    public FinanceInformation buildFinanceInformation() {
        System.out.println("Вызов метода для объекта DbFinanceInfoBuilder");
        return new FinanceInformation();
    }
}
