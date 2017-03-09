package smartbudget.resource;


import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;

import io.dropwizard.testing.junit.DropwizardAppRule;
import io.smartbudget.application.BudgetApplication;
import io.smartbudget.configuration.AppConfiguration;
import io.smartbudget.persistence.entity.Category;
import io.smartbudget.domain.enums.CategoryType;
import io.smartbudget.form.budget.AddBudgetForm;
import io.smartbudget.resource.ResourceURL;

public class CategoryResourceIT extends ResourceIT {

    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
            new DropwizardAppRule<>(BudgetApplication.class, resourceFilePath("config-test.yml"));

    @Override
    protected int getPort() {
        return RULE.getLocalPort();
    }

    @Test
    public void shouldAbleCreateCategory() {

        // given
        Category category = new Category();
        category.setName(randomAlphabets());
        category.setType(CategoryType.EXPENDITURE);

        // when
        Response response = post(ResourceURL.CATEGORY, category);

        // then
        assertCreated(response);
        Assert.assertNotNull(response.getLocation());
    }

    @Test
    public void shouldAbleFindValidCategory() {

        // given
        Category category = new Category();
        category.setName(randomAlphabets());
        category.setType(CategoryType.EXPENDITURE);

        // when
        Response response = post(ResourceURL.CATEGORY, category);

        // then
        Response newReponse = get(response.getLocation().getPath());
        assertOk(newReponse);
    }


    @Test
    public void shouldNotAbleDeleteCategoryWithChild() {

        // given
        Category category = new Category();
        category.setName(randomAlphabets());
        category.setType(CategoryType.EXPENDITURE);

        // when
        Response response = post(ResourceURL.CATEGORY, category);
        AddBudgetForm budget = new AddBudgetForm();
        budget.setName(randomAlphabets());
        budget.setCategoryId(identityResponse(response).getId());
        post(ResourceURL.BUDGET, budget);

        // then
        Response newReponse = delete(response.getLocation().getPath());
        assertBadRequest(newReponse);
    }
}
