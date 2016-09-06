package com.vrcc.utils.hibernate;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Transaction;

public class HibernateTransactionInterceptor implements MethodInterceptor {

	@Inject
	private SessionContext session;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		session.open();
		Transaction tx = null;
		try {
			tx = session.get().beginTransaction();
			final Object object = invocation.proceed();
			tx.commit();
			return object;
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.get().close();
			session.clean();
		}
	}

}
