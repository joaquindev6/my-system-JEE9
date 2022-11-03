package com.jfarro.app.interceptors;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.TransactionalMysql;
import com.jfarro.app.exceptions.ServiceJdbcException;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.sql.Connection;

@Interceptor
@TransactionalMysql
public class TransactionalMysqlInterceptor {

    @Inject
    @ConnectionMySQL
    private Connection conn;

    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) throws Exception {
        if (this.conn.getAutoCommit()) {
            this.conn.setAutoCommit(false);
        }
        try {
            Object result = invocationContext.proceed();
            this.conn.commit();
            return result;
        } catch (ServiceJdbcException e) {
            this.conn.rollback();
            throw e;
        }
    }
}
