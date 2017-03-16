package io.smartbudget.form.recurring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.smartbudget.domain.enums.RecurringType;


@JsonIgnoreProperties(ignoreUnknown = true)
public class AddRecurringForm implements Serializable {

    private static final long serialVersionUID = -3317443535487916735L;

    private Long budgetId;
    private Double amount;
    private Timestamp recurringAt;
    private RecurringType recurringType;
    private String remark;

    @NotNull(message = "{validation.budget.required}")
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @NotNull(message = "{validation.amount.required}")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @NotNull(message = "{validation.recurringAt.required}")
    public Timestamp getRecurringAt() {
        return recurringAt;
    }

    public void setRecurringAt(Timestamp recurringAt) {
        this.recurringAt = recurringAt;
    }

    @NotNull(message = "{validation.recurringType.required}")
    public RecurringType getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
