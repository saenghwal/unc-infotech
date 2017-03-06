package io.smartbudget.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.smartbudget.domain.Point;
import io.smartbudget.domain.entity.Transaction;
import io.smartbudget.domain.entity.User;
import io.smartbudget.form.TransactionForm;
import io.smartbudget.service.FinanceService;

@Path(ResourceURL.TRANSACTION)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource extends AbstractResource {

    private final FinanceService financeService;

    public TransactionResource(FinanceService financeService) {
        this.financeService = financeService;
    }

    @Override
    public String getPath() {
        return ResourceURL.TRANSACTION;
    }

    @GET
    @UnitOfWork
    public List<Transaction> findAllTransactions(@Auth User user, @QueryParam("limit") Integer limit) {
        return financeService.findRecentTransactions(user, limit);
    }

    @POST
    @UnitOfWork
    public Response add(@Auth User user, TransactionForm transactionForm) {
        Transaction transaction = financeService.addTransaction(user, transactionForm);
        return created(transaction, transaction.getId());
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    public Transaction findById(@PathParam("id") long id) {
        return financeService.findTransactionById(id);
    }

    @DELETE
    @UnitOfWork
    @Path("/{id}")
    public Response delete(@Auth User user, @PathParam("id") long id) {
        boolean deleted = financeService.deleteTransaction(user, id);
        if(deleted) {
            return deleted();
        } else {
            return notFound();
        }
    }

    @GET
    @UnitOfWork
    @Path("/summary")
    public List<Point> findSummary(@Auth User user, @QueryParam("month") Integer month, @QueryParam("year") Integer year) {
        return financeService.findTransactionUsage(user, month, year);
    }

    @GET
    @UnitOfWork
    @Path("/monthly")
    public List<Point> findMonthly(@Auth User user) {
        return financeService.findMonthlyTransactionUsage(user);
    }

    @GET
    @UnitOfWork
    @Path("/today")
    public Response findTodayRecurringTransactions(@Auth User user) {
        return ok(financeService.findTodayRecurringsTransactions(user));
    }

}
