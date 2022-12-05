package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends configs {
	Connection dbconnection;

	public Connection getConnection() {
		String connectionString = "jdbc:mysql://" + configs.dbhost + ":" + configs.dbport + "/" + configs.dbname
				+ "?autoReconnect=true&useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dbconnection = DriverManager.getConnection(connectionString, configs.dbuser, configs.dbpass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbconnection;
	}
}
