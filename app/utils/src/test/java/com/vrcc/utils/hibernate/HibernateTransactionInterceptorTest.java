package com.vrcc.utils.hibernate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HibernateTransactionInterceptorTest {

	@Mock
	private SessionContext session;

	@InjectMocks
	private HibernateTransactionInterceptor interceptor;

	@Mock
	private MethodInvocation invocation;

	@Mock
	private Session opened;

	@Mock
	private Transaction tx;

	@Mock
	private Object value;

	@Test
	public void shouldCommitTransaction() throws Throwable {
		when(session.get()).thenReturn(opened);
		when(opened.beginTransaction()).thenReturn(tx);
		when(invocation.proceed()).thenReturn(value);
		final Object result = interceptor.invoke(invocation);
		assertEquals(value, result);
		verify(session).open();
		verify(opened).beginTransaction();
		verify(invocation).proceed();
		verify(tx).commit();
		verify(opened).close();
		verify(session).clean();
	}

	@Test(expected = RuntimeException.class)
	public void shouldRollbackTransactionOnException() throws Throwable {
		when(session.get()).thenReturn(opened);
		when(opened.beginTransaction()).thenReturn(tx);
		when(invocation.proceed()).thenThrow(new RuntimeException("mocked"));
		try {
			interceptor.invoke(invocation);
		} catch (RuntimeException e) {
			verify(session).open();
			verify(opened).beginTransaction();
			verify(invocation).proceed();
			verify(tx).rollback();
			verify(opened).close();
			verify(session).clean();
			throw e;
		}
	}

}
