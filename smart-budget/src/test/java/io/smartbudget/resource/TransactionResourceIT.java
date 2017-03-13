package io.smartbudget.resource;


import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit.DropwizardAppRule;
import io.smartbudget.application.BudgetApplication;
import io.smartbudget.configuration.AppConfiguration;
import io.smartbudget.modal.IdentityResponse;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.Category;
import io.smartbudget.domain.enums.CategoryType;
import io.smartbudget.form.TransactionForm;
import io.smartbudget.form.budget.AddBudgetForm;

public class TransactionResourceIT extends ResourceIT {

    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(BudgetApplication.class, resourceFilePath("config-test.yml"));

    @Override
    protected int getPort() {
        return RULE.getLocalPort();
    }

    @Test
    public void shouldAbleCreateTransaction() {

        // given
        TransactionForm transaction = new TransactionForm();
        transaction.setAmount(10.00);
        transaction.setBudget(defaultBudget);

        // when
        Response response = post(ResourceURL.TRANSACTION, transaction);

        // then
        assertCreated(response);
        Assert.assertNotNull(response.getLocation());
    }

    @Test
    public void shouldAbleDeleteValidTransaction() {

        // given
        TransactionForm transaction = new TransactionForm();
        transaction.setAmount(10.00);
        transaction.setBudget(defaultBudget);
        Response response = post(ResourceURL.TRANSACTION, transaction);
        IdentityResponse identityResponse = identityResponse(response);

        // when
        Response deleteResponse = delete(ResourceURL.TRANSACTION + "/" + identityResponse.getId());

        // then
        assertDeleted(deleteResponse);
    }

    @Test
    public void shouldNotAbleDeleteInvalidTransaction() {

        // given
        long transactionId = Long.MAX_VALUE;

        // when
        Response deleteResponse = delete("/api/transactions/" + transactionId);

        // then
        assertNotFound(deleteResponse);
    }

    @Test
    public void shouldAbleFindValidTransaction() {

        // given
        TransactionForm transaction = new TransactionForm();
        transaction.setAmount(10.00);
        transaction.setBudget(defaultBudget);

        // when
        Response response = post(ResourceURL.TRANSACTION, transaction);

        // then
        Response newReponse = get(response.getLocation().getPath());
        assertOk(newReponse);
    }

    @Test
    public void shouldAbleFindTransactionsByBudget() {
        // given
        Category category = new Category();
        category.setName(randomAlphabets());
        category.setType(CategoryType.EXPENDITURE);

        AddBudgetForm budget = new AddBudgetForm();
        budget.setName(randomAlphabets());

        TransactionForm transaction = new TransactionForm();
        transaction.setAmount(10.00);

        // when
        Response categoryResponse = post(ResourceURL.CATEGORY, category);
        Long categoryId = identityResponse(categoryResponse).getId();
        budget.setCategoryId(categoryId);

        Response budgetResponse = post(ResourceURL.BUDGET, budget);
        Long budgetId = identityResponse(budgetResponse).getId();

        transaction.setBudget(new Budget(budgetId));
        post(ResourceURL.TRANSACTION, transaction);

        // then
        Response newResponse = get("/api/budgets/" + budgetId + "/transactions");
        List<IdentityResponse> ids = identityResponses(newResponse);
        assertOk(newResponse);
        Assert.assertEquals(1, ids.size());
    }
}
