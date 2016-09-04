package com.vrcc.infra.db;

import java.sql.Connection;

public interface SimpleDataSource {

	Connection getConnection();
	
}
