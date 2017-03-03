package io.smartbudget.ejb.persistence.enums.entity;

public enum RecurringType {

    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly");

    private final String display;

    RecurringType(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}