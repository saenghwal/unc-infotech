package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entity.BudgetsEntity;

public class AccountSummary implements Serializable {

    private static final long serialVersionUID = 5319703962527161534L;

    private List<BudgetsEntity> income;
    private List<Group> groups = new ArrayList<>();

    public List<BudgetsEntity> getIncome() {
        return income;
    }

    public void setIncome(List<BudgetsEntity> income) {
        this.income = income;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
