package netcracker.unc.logic;

import netcracker.unc.annotation.AutoWired;
import netcracker.unc.logic.interfaces.SomeInterface;
import netcracker.unc.logic.interfaces.SomeOtherInterface;

public class SomeBean {
    @AutoWired
    private SomeInterface someField;

    @AutoWired
    private SomeOtherInterface otherField;

    public void doSome() {
        someField.doSome();
        otherField.doSome();
    }
}
