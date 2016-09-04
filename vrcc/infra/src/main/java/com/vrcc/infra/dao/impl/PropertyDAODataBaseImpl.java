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

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.db.SimpleDataSource;

public class PropertyDAODataBaseImpl implements PropertyDAO {

	private final SimpleDataSource ds;

	@Inject
	public PropertyDAODataBaseImpl(SimpleDataSource ds) {
		this.ds = ds;
	}

	@Override
	public Property add(Property property) {
		long id = 0;
		try (Connection c = ds.getConnection()) {
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
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return Property.added(id, property);
	}

	@Override
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
		return results.values();
	}

}
