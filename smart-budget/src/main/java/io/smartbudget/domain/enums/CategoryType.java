package io.smartbudget.domain.enums;

public enum CategoryType {

    INCOME("Income"),
    EXPENDITURE("Expenditure");

    private final String display;

    CategoryType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
