package io.smartbudget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import liquibase.Liquibase;
import liquibase.database.jvm.HsqlConnection;
import liquibase.resource.FileSystemResourceAccessor;

public class HSQLDatabaseConnectionProvider {

    private static final Logger LOG = LoggerFactory.getLogger(HSQLDatabaseConnectionProvider.class);

    private static final String CHANGELOG_FILE = "src/main/resources/migrations.xml";

    private JdbcProperties jdbcProperties;

    public HSQLDatabaseConnectionProvider() {
        jdbcProperties = loadProperties();
    }

    public HSQLDatabaseConnectionProvider(JdbcProperties jdbcProperties) {
        if (jdbcProperties == null)
            throw new IllegalArgumentException(
                    "Illegal null-reference jdbcProperties");

        this.jdbcProperties = jdbcProperties;
    }

    public void setUp(String contexts) {
        try {
            Class.forName(jdbcProperties.getDriverClassName()).newInstance();
            Connection connection = DriverManager.getConnection(jdbcProperties
                    .getUrl());

            HsqlConnection conn = new HsqlConnection(connection);

            Liquibase liquibase = new Liquibase(CHANGELOG_FILE,
                    new FileSystemResourceAccessor(), conn);
            liquibase.update(contexts);

            conn.close();
            connection.close();
        } catch (Exception ex) {
            LOG.error("Error during in-memory database setup", ex);
            throw new RuntimeException(ex);
        }
    }


    private JdbcProperties loadProperties() {
        Properties properties = new Properties();
        JdbcProperties result = null;
        InputStream istream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("test-config.properties");
        try {
            properties.load(istream);

            result = new JdbcProperties();

            String driverClassName = properties
                    .getProperty("db.driver");
            result.setDriverClassName(driverClassName);

            String url = properties.getProperty("db.url");
            result.setUrl(url);

            String username = properties.getProperty("db.username");
            result.setUserName(username);

            String password = properties.getProperty("db.password");
            result.setPassword(password);

            istream.close();
        } catch (IOException ex) {
            LOG.error("Error loading properties", ex);
            throw new IllegalStateException("Can't load jdbc properties");
        }

        return result;
    }
}
