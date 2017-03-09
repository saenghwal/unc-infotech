package io.smartbudget.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.smartbudget.persistence.entity.Budget;
import io.smartbudget.persistence.entity.Transaction;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.form.budget.AddBudgetForm;
import io.smartbudget.form.budget.UpdateBudgetForm;
import io.smartbudget.service.FinanceService;

@Path(ResourceURL.BUDGET)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BudgetResource extends AbstractResource {

    private final FinanceService financeService;

    public BudgetResource(FinanceService financeService) {
        this.financeService = financeService;
    }

    @Override
    public String getPath() {
        return ResourceURL.BUDGET;
    }

    @GET
    @UnitOfWork
    public List<Budget> getBudgets(@Auth User user) {
        return financeService.findBudgetsByUser(user);
    }

    @POST
    @UnitOfWork
    public Response add(@Auth User user, @Valid AddBudgetForm budgetForm) {
        Budget budget = financeService.addBudget(user, budgetForm);
        return created(budget, budget.getId());
    }

    @PUT
    @UnitOfWork
    @Path("/{id}")
    public Response update(@Auth User user, @PathParam("id") long id, @Valid UpdateBudgetForm budgetForm) {
        budgetForm.setId(id);
        Budget budget = financeService.updateBudget(user, budgetForm);
        return ok(budget);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public Response delete(@Auth User user, @PathParam("id") long id) {
        financeService.deleteBudget(user, id);
        return deleted();
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Budget findById(@Auth User user, @PathParam("id") long id) {
        return financeService.findBudgetById(user, id);
    }

    @GET
    @UnitOfWork
    @Path("/{id}/transactions")
    public List<Transaction> findTransactions(@Auth User user, @PathParam("id") long id) {
        return financeService.findTransactionsByBudget(user, id);
    }

    @GET
    @UnitOfWork
    @Path("/suggests")
    public List<String> findSuggestion(@Auth User user, @QueryParam("q") String q) {
        return financeService.findBudgetSuggestions(user, q);
    }
}
