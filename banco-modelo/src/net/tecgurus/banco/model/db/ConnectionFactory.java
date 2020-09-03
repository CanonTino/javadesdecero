package net.tecgurus.banco.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	// Objeto singleton
	private static ConnectionFactory connectionFactory;

	static {
		connectionFactory = new ConnectionFactory();
	}
	
	//Contructor privado para implementar patrón de diseño Singleton
		private ConnectionFactory() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
//				Class.forName(AppProperties.getPropertyValue(AppProperties.JDBC_DRIVER_CLASS));
			} catch (ClassNotFoundException e) {
				
			}
		}
		
		private Connection createConnection() {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/banco", 
						"root", 
						"root");
//				connection = DriverManager.getConnection(
//						AppProperties.getPropertyValue(AppProperties.JDBC_URL), 
//						AppProperties.getPropertyValue(AppProperties.JDBC_USER), 
//						AppProperties.getPropertyValue(AppProperties.JDBC_PASSWORD));
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return connection;
		}
		
		//Obtenemos una conexión a través del objeto singleton
		public static Connection getConnection() {
			return connectionFactory.createConnection();
		}
}
