package io.smartbudget.application;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.loginbox.dropwizard.mybatis.MybatisBundle;

import org.apache.ibatis.session.SqlSessionFactory;

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
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.smartbudget.configuration.AppConfiguration;
import io.smartbudget.crypto.PasswordEncoder;
import io.smartbudget.hibernate.dao.CategoryDAO;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.ejb.dao.impl.UserDAOImpl;
import io.smartbudget.exception.ConstraintViolationExceptionMapper;
import io.smartbudget.exception.DataConstraintExceptionMapper;
import io.smartbudget.exception.NotFoundExceptionMapper;
import io.smartbudget.exception.SQLConstraintViolationExceptionMapper;
import io.smartbudget.managed.MigrationManaged;
import io.smartbudget.resource.BudgetResource;
import io.smartbudget.resource.CategoryResource;
import io.smartbudget.resource.RecurringResource;
import io.smartbudget.resource.ReportResource;
import io.smartbudget.resource.TransactionResource;
import io.smartbudget.resource.UserResource;
import io.smartbudget.service.FinanceService;

public class BudgetApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new BudgetApplication().run(args);
    }

    private final MybatisBundle<AppConfiguration> mybatisBundle
            = new MybatisBundle<AppConfiguration>("io.smartbudget") {
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
        //bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ConfiguredAssetsBundle("/app", "/app", "index.html"));
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        SqlSessionFactory sessionFactory = mybatisBundle.getSqlSessionFactory();

        // password encoder
        final PasswordEncoder passwordEncoder = new PasswordEncoder();

        // DAO
        //final CategoryDAO categoryDAO = new CategoryDAO(hibernate.getSessionFactory(), configuration);
        //final BudgetDAO budgetDAO = new BudgetDAO(hibernate.getSessionFactory(), configuration);
        //final BudgetTypeDAO budgetTypeDAO = new BudgetTypeDAO(hibernate.getSessionFactory());
        //final io.smartbudget.hibernate.dao.UserDAO userDAO = new io.smartbudget.hibernate.dao.UserDAO(hibernate.getSessionFactory(), configuration);
        final UserDAOImpl userDAO = new UserDAOImpl(sessionFactory);
        //final TransactionDAO transactionDAO = new TransactionDAO(hibernate.getSessionFactory());
        //final RecurringDAO recurringDAO = new RecurringDAO(hibernate.getSessionFactory());
        //final AuthTokenDAO authTokenDAO = new AuthTokenDAO(hibernate.getSessionFactory());

        // service
//        final FinanceService financeService = new FinanceService(sessionFactory,
//                userDAO, passwordEncoder);

        // jobs
        //final RecurringJob recurringJob = new UnitOfWorkAwareProxyFactory(hibernate).create(RecurringJob.class, FinanceService.class, financeService);

        // resource
        //environment.jersey().register(new UserResource(financeService));
//        environment.jersey().register(new CategoryResource(financeService));
//        environment.jersey().register(new BudgetResource(financeService));
//        environment.jersey().register(new TransactionResource(financeService));
//        environment.jersey().register(new RecurringResource(financeService));
//        environment.jersey().register(new ReportResource(financeService));

        // managed
        environment.lifecycle().manage(new MigrationManaged(configuration));
        //environment.lifecycle().manage(new JobsManaged(recurringJob));

        // exception mapper
        environment.jersey().register(new NotFoundExceptionMapper());
        environment.jersey().register(new DataConstraintExceptionMapper());
        environment.jersey().register(new ConstraintViolationExceptionMapper());
        environment.jersey().register(new SQLConstraintViolationExceptionMapper());

    }

}
