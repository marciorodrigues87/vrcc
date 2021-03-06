package com.vrcc.infra.dao.impl;

import static com.vrcc.infra.dao.impl.PropertyQueries.SQL_INSERT_PROPERTY;
import static com.vrcc.infra.dao.impl.PropertyQueries.SQL_INSERT_PROVINCES;
import static com.vrcc.infra.dao.impl.PropertyQueries.SQL_SELECT_FILTER;
import static com.vrcc.infra.dao.impl.PropertyQueries.SQL_SELECT_ONE;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.util.Arrays.asList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.utils.cache.Cached;

@Singleton
@Deprecated
public class PropertyDAOJdbcImpl implements PropertyDAO {

	private final DataSource ds;

	@Inject
	public PropertyDAOJdbcImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public Property add(Property property) {
		long id = 0;
		Connection c = null;
		try {
			c = ds.getConnection();
			startTx(c);
			try (PreparedStatement stmt = c.prepareStatement(SQL_INSERT_PROPERTY, RETURN_GENERATED_KEYS)) {
				int i = 0;
				stmt.setInt(++i, property.getX());
				stmt.setInt(++i, property.getY());
				stmt.setString(++i, property.getTitle());
				stmt.setLong(++i, property.getPrice());
				stmt.setString(++i, property.getDescription());
				stmt.setInt(++i, property.getBeds());
				stmt.setInt(++i, property.getBaths());
				stmt.setLong(++i, property.getSquareMeters());

				stmt.executeUpdate();

				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						id = rs.getLong(1);
					}
				}

				try (PreparedStatement provinceStmt = c.prepareStatement(SQL_INSERT_PROVINCES)) {
					for (String province : property.getProvinces()) {
						int j = 0;
						provinceStmt.setLong(++j, id);
						provinceStmt.setString(++j, province);

						provinceStmt.addBatch();
					}
					provinceStmt.executeBatch();
				}
				commit(c);
			}
		} catch (Exception e) {
			rollback(c);
			throw new RuntimeException(e);
		} finally {
			close(c);
		}
		return Property.added(id, property);
	}

	private void startTx(Connection c) throws SQLException {
		c.setAutoCommit(false);
	}

	private void commit(Connection c) throws SQLException {
		c.commit();
	}

	private void close(Connection c) {
		try {
			if (c != null && !c.isClosed()) {
				c.setAutoCommit(true);
				c.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void rollback(Connection c) {
		if (c != null) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	@Override
	@Cached(ttl = 60)
	public Property get(long id) {
		try (Connection c = ds.getConnection()) {
			try (PreparedStatement stmt = c.prepareStatement(SQL_SELECT_ONE)) {
				stmt.setLong(1, id);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						final Property property = property(rs);
						while (rs.next()) {
							property.addProvince(rs.getString("name"));
						}
						return property;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	private Property property(ResultSet rs) throws SQLException {
		return Property.full(
				rs.getLong("id"),
				rs.getInt("x"),
				rs.getInt("y"),
				rs.getString("title"),
				rs.getLong("price"),
				rs.getString("description"),
				rs.getInt("beds"),
				rs.getInt("baths"),
				rs.getLong("squareMeters"),
				provinces(rs));
	}

	private List<String> provinces(ResultSet rs) throws SQLException {
		return new ArrayList<>(asList(rs.getString("name")));
	}

	@Override
	@Cached(ttl = 60)
	public Collection<Property> find(PropertyFilter filter) {
		final Map<Long, Property> results = new HashMap<>();
		try (Connection c = ds.getConnection()) {
			try (PreparedStatement stmt = c.prepareStatement(SQL_SELECT_FILTER)) {
				int i = 0;
				stmt.setInt(++i, filter.getAx());
				stmt.setInt(++i, filter.getBx());
				stmt.setInt(++i, filter.getBy());
				stmt.setInt(++i, filter.getAy());
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						final Property property = property(rs);
						final Property result = results.get(property.getId());
						if (result != null) {
							result.addProvinces(property.getProvinces());
							continue;
						}
						results.put(property.getId(), property);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return new ArrayList<>(results.values());
	}

}
