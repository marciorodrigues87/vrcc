package com.vrcc.infra.db.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Singleton;

import com.vrcc.infra.db.SimpleDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Singleton
public class MySqlDataSource implements SimpleDataSource {

	private final HikariDataSource ds;

	public MySqlDataSource() {
		ds = new HikariDataSource(new HikariConfig("/hikari.properties"));
	}

	@Override
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		new MySqlDataSource();
	}
	
}
