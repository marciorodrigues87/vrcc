package com.vrcc.utils.hibernate;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;

@Singleton
public class SessionContext {

	private final ThreadLocal<Session> session = new ThreadLocal<>();

	private final SessionFactory sf;

	@Inject
	public SessionContext(SessionFactory sf) {
		this.sf = sf;
	}

	public void open() {
		session.set(sf.openSession());
	}

	public Session get() {
		return session.get();
	}

	public StatelessSession stateless() {
		return sf.openStatelessSession();
	}

	public void clean() {
		session.remove();
	}

}
