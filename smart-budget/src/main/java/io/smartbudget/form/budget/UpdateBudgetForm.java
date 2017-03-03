package io.smartbudget.form.budget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateBudgetForm extends BudgetForm implements Serializable {

    private static final long serialVersionUID = 7677505567308081026L;

    private Long id;
    private double actual;

    @NotNull(message = "{validation.id.required}")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getActual() {
        return actual;
    }

    public void setActual(double actual) {
        this.actual = actual;
    }
}
