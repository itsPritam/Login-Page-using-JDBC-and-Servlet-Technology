package mySqlConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionProvider {

	static Connection con = null;

	private ConnectionProvider() {

	}

	public static Connection getConnection() {

		if (con == null) {
			try {

				Class.forName(ConnectionBuilder.DATABASE_DRIVER);
				con = DriverManager.getConnection(ConnectionBuilder.DATABASE_URL, ConnectionBuilder.DATABASE_USER,
						ConnectionBuilder.DATABASE_PASS);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return con;
	}

}
