package io.smartbudget.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.smartbudget.ejb.persistence.entity.User;
import io.smartbudget.form.report.SearchFilter;
import io.smartbudget.service.FinanceService;


@Path(ResourceURL.REPORT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportResource extends AbstractResource {

    private final FinanceService financeService;

    public ReportResource(FinanceService financeService) {
        this.financeService = financeService;
    }

    @Override
    public String getPath() {
        return ResourceURL.REPORT;
    }

    @POST
    @UnitOfWork
    @Path("/transactions")
    public Response findTransactions(@Auth User user, SearchFilter filter) {
        return ok(financeService.findTransactions(user, filter));
    }
}
