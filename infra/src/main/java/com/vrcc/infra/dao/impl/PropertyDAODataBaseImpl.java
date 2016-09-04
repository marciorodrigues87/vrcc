package com.vrcc.infra.dao.impl;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.db.SimpleDataSource;

public class PropertyDAODataBaseImpl implements PropertyDAO {

	private static final String SQL_INSERT = "INSERT INTO property (x, y, title, price, description, beds, baths, squareMeters) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ONE = "SELECT id, x, y, title, price, description, beds, baths, squareMeters FROM property WHERE id = ?";
	private static final String SQL_SELECT_FILTER = "SELECT id, x, y, title, price, description, beds, baths, squareMeters FROM property WHERE x >= ? AND x <= ? AND y >= ? AND y <= ?";
	private final SimpleDataSource ds;

	@Inject
	public PropertyDAODataBaseImpl(SimpleDataSource ds) {
		this.ds = ds;
	}

	@Override
	public Property add(Property property) {
		long id = 0;
		try (Connection c = ds.getConnection()) {
			try (PreparedStatement stmt = c.prepareStatement(SQL_INSERT, RETURN_GENERATED_KEYS)) {
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
				
				try (ResultSet rs = stmt.getGeneratedKeys()){
					if (rs.next()) {
						id = rs.getLong(1);
					}
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
						return property(rs);
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
				null);
	}

	@Override
	public Collection<Property> find(PropertyFilter filter) {
		final Collection<Property> result = new ArrayList<>();
		try (Connection c = ds.getConnection()) {
			try (PreparedStatement stmt = c.prepareStatement(SQL_SELECT_FILTER)) {
				int i = 0;
				stmt.setInt(++i, filter.getAx());
				stmt.setInt(++i, filter.getBx());
				stmt.setInt(++i, filter.getBy());
				stmt.setInt(++i, filter.getAy());
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						result.add(property(rs));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

}
