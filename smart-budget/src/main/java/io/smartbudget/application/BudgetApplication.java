package io.smartbudget.application;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.DefaultUnauthorizedHandler;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.smartbudget.auth.DefaultAuthorizer;
import io.smartbudget.auth.TokenAuthenticator;
import io.smartbudget.configuration.AppConfiguration;
import io.smartbudget.crypto.PasswordEncoder;
import io.smartbudget.domain.job.RecurringJob;
import io.smartbudget.ejb.persistence.entity.AuthToken;
import io.smartbudget.ejb.persistence.entity.Budget;
import io.smartbudget.ejb.persistence.entity.BudgetType;
import io.smartbudget.ejb.persistence.entity.Category;
import io.smartbudget.ejb.persistence.entity.Recurring;
import io.smartbudget.ejb.persistence.entity.Transaction;
import io.smartbudget.ejb.persistence.entity.User;
import io.smartbudget.exception.ConstraintViolationExceptionMapper;
import io.smartbudget.exception.DataConstraintExceptionMapper;
import io.smartbudget.exception.NotFoundExceptionMapper;
import io.smartbudget.exception.SQLConstraintViolationExceptionMapper;
import io.smartbudget.hibernate.dao.AuthTokenDAO;
import io.smartbudget.hibernate.dao.BudgetDAO;
import io.smartbudget.hibernate.dao.BudgetTypeDAO;
import io.smartbudget.hibernate.dao.CategoryDAO;
import io.smartbudget.hibernate.dao.RecurringDAO;
import io.smartbudget.hibernate.dao.TransactionDAO;
import io.smartbudget.hibernate.dao.UserDAO;
import io.smartbudget.managed.JobsManaged;
import io.smartbudget.managed.MigrationManaged;
import io.smartbudget.resource.BudgetResource;
import io.smartbudget.resource.CategoryResource;
import io.smartbudget.resource.HealthCheckResource;
import io.smartbudget.resource.RecurringResource;
import io.smartbudget.resource.ReportResource;
import io.smartbudget.resource.TransactionResource;
import io.smartbudget.resource.UserResource;
import io.smartbudget.service.FinanceService;

public class BudgetApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new BudgetApplication().run(args);
    }

    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(
            User.class, Category.class, Budget.class, BudgetType.class, Transaction.class,
            Recurring.class, AuthToken.class) {

        @Override
        protected Hibernate5Module createHibernate5Module() {
            Hibernate5Module module = super.createHibernate5Module();
            // allow @Transient JPA annotation process by Jackson
            module.disable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
            module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
            return module;
        }

        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public String getName() {
        return "budget-app";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        MigrationsBundle<AppConfiguration> migrationBundle = new MigrationsBundle<AppConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        };

        // allow using Environment variable in yaml
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );

        bootstrap.addBundle(migrationBundle);
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ConfiguredAssetsBundle("/app", "/app", "index.html"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {

        // password encoder
        final PasswordEncoder passwordEncoder = new PasswordEncoder();

        // DAO
        final CategoryDAO categoryDAO = new CategoryDAO(hibernate.getSessionFactory(), configuration);
        final BudgetDAO budgetDAO = new BudgetDAO(hibernate.getSessionFactory(), configuration);
        final BudgetTypeDAO budgetTypeDAO = new BudgetTypeDAO(hibernate.getSessionFactory());
        final UserDAO userDAO = new UserDAO(hibernate.getSessionFactory());
        final TransactionDAO transactionDAO = new TransactionDAO(hibernate.getSessionFactory());
        final RecurringDAO recurringDAO = new RecurringDAO(hibernate.getSessionFactory());
        final AuthTokenDAO authTokenDAO = new AuthTokenDAO(hibernate.getSessionFactory());

        // service
        final FinanceService financeService = new FinanceService(hibernate.getSessionFactory(), userDAO, budgetDAO, budgetTypeDAO, categoryDAO, transactionDAO, recurringDAO, authTokenDAO, passwordEncoder);

        // jobs
        final RecurringJob recurringJob = new UnitOfWorkAwareProxyFactory(hibernate).create(RecurringJob.class, FinanceService.class, financeService);

        // resource
        environment.jersey().register(new UserResource(financeService));
        environment.jersey().register(new CategoryResource(financeService));
        environment.jersey().register(new BudgetResource(financeService));
        environment.jersey().register(new TransactionResource(financeService));
        environment.jersey().register(new RecurringResource(financeService));
        environment.jersey().register(new ReportResource(financeService));

        // health check
        environment.jersey().register(new HealthCheckResource(environment.healthChecks()));


        // managed
        environment.lifecycle().manage(new MigrationManaged(configuration));
        environment.lifecycle().manage(new JobsManaged(recurringJob));

        // auth
        TokenAuthenticator tokenAuthenticator = new UnitOfWorkAwareProxyFactory(hibernate).create(TokenAuthenticator.class, FinanceService.class, financeService);
        final OAuthCredentialAuthFilter<User> authFilter =
                new OAuthCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(tokenAuthenticator)
                        .setPrefix("Bearer")
                        .setAuthorizer(new DefaultAuthorizer())
                        .setUnauthorizedHandler(new DefaultUnauthorizedHandler())
                        .buildAuthFilter();
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthDynamicFeature(authFilter));
        environment.jersey().register(new AuthValueFactoryProvider.Binder(User.class));

        // filters
        FilterRegistration.Dynamic urlRewriteFilter = environment.servlets().addFilter("rewriteFilter", UrlRewriteFilter.class);
        urlRewriteFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");
        urlRewriteFilter.setInitParameter("confPath", "urlrewrite.xml");

        // only enable for dev
        // FilterRegistration.Dynamic filterSlow = environment.servlets().addFilter("slowFilter", SlowNetworkFilter.class);
        // filterSlow.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, "/*");

        // exception mapper
        environment.jersey().register(new NotFoundExceptionMapper());
        environment.jersey().register(new DataConstraintExceptionMapper());
        environment.jersey().register(new ConstraintViolationExceptionMapper());
        environment.jersey().register(new SQLConstraintViolationExceptionMapper());

    }

}
