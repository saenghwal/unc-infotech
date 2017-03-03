package io.smartbudget.form.budget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBudgetForm extends BudgetForm implements Serializable {

    private static final long serialVersionUID = 1610773759602819442L;

    private Long categoryId;

    @NotNull(message = "{validation.category.required}")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
