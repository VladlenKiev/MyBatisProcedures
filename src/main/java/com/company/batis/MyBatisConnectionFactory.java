package com.company.batis;

import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Class {@code MyBatisConnectionFactory} is Model.
 *
 * @author V.Mankivskiy
 * @see com.company.batis.MyBatisConnectionFactory
 * @since 1.0.0
 * @date 15/09/2017
 */
class MyBatisConnectionFactory {

    @SuppressWarnings("CanBeFinal")
    private static SqlSessionFactory sqlSessionFactory;

    private MyBatisConnectionFactory(){}

    private static Logger log = Logger.getLogger(MyBatisConnectionFactory.class.getName());

    static {
        try {

            @SuppressWarnings("UnusedAssignment") String resource = "com/hmkcode/mybatis/config.xml"; //NOSONAR
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");

            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (IOException iOException) {
            iOException.printStackTrace(); //NOSONAR
            log.fine("File doesn't not found");

        }
    }
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}