package com.vrcc.infra.dao.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class PropertyDAOJdbcImplTest {

	private static final long ID = 1;
	private static final String PROVINCE = "Gode";

	@InjectMocks
	private PropertyDAOJdbcImpl dao;

	@Mock
	private DataSource ds;

	@Mock
	private Property property;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private PropertyFilter filter;

	@Test
	public void shouldAddProperty() throws SQLException {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(stmt);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
		when(stmt.getGeneratedKeys()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		when(rs.getLong(1)).thenReturn(ID);
		when(property.getProvinces()).thenReturn(asList(PROVINCE));
		final Property addedProperty = dao.add(property);
		assertEquals(ID, addedProperty.getId());
		verify(connection).setAutoCommit(false);
		verify(connection).commit();
		verify(connection).setAutoCommit(true);
		verify(connection).close();
	}

	@Test(expected = RuntimeException.class)
	public void shouldAddProvinceOnPropertyError() throws SQLException {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(stmt);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
		when(stmt.getGeneratedKeys()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		when(rs.getLong(1)).thenReturn(ID);
		when(property.getProvinces()).thenReturn(asList(PROVINCE));
		when(stmt.executeBatch()).thenThrow(new SQLException("mock!"));
		try {
			dao.add(property);
		} catch (Exception e) {
			verify(connection).setAutoCommit(false);
			verify(connection).rollback();
			verify(connection).setAutoCommit(true);
			verify(connection).close();
			throw e;
		}
	}

	@Test
	public void shouldGetProperty() throws SQLException {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true, true, false);
		final Property property = dao.get(ID);
		assertNotNull(property);
	}

	@Test
	public void shouldNotGetProperty() throws SQLException {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);
		final Property property = dao.get(ID);
		assertNull(property);
	}

	@Test
	public void shouldGetProperties() throws SQLException {
		when(ds.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true, true, false);
		final Collection<Property> properties = dao.find(filter);
		assertEquals(1, properties.size());
	}

}
