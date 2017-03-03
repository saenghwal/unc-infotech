package io.smartbudget.domain.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.dropwizard.hibernate.UnitOfWork;
import io.smartbudget.service.FinanceService;

public class RecurringJob implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecurringJob.class);

    private final FinanceService financeService;

    public RecurringJob(FinanceService financeService) {
        this.financeService = financeService;
    }

    @UnitOfWork
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        LOGGER.debug("Start {} job", getName());
        financeService.updateRecurrings();
        LOGGER.debug("Complete {} job and took {}ms", getName(), System.currentTimeMillis() - start);
    }

    private String getName() {
        return "Recurring job";
    }
}
