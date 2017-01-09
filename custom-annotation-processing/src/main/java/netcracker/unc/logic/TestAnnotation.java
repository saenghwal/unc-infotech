package netcracker.unc.logic;

import netcracker.unc.annotation.TIgnore;
import netcracker.unc.annotation.TMarker;

public class TestAnnotation {
    @TIgnore
    public static void method1() {
        int[] j = new int[0];
        j[1] = 2;
    }

    @TMarker(expected = ArrayIndexOutOfBoundsException.class)
    public static void method2() {
        int[] j = new int[0];
        j[1] = 2;
    }
}
