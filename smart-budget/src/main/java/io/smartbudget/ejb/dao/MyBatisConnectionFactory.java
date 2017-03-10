package io.smartbudget.ejb.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import io.smartbudget.persistence.mappers.BudgetsMapper;
import io.smartbudget.persistence.mappers.UsersMapper;


/**
 * MyBatis Connection Factory, which reads the configuration data from a XML file.
 *
 */
public class MyBatisConnectionFactory {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

                sqlSessionFactory.getConfiguration().addMapper(UsersMapper.class);
                sqlSessionFactory.getConfiguration().addMapper(BudgetsMapper.class);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

}
