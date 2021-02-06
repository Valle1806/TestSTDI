package nEnLinea.base;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	
	public Connection conectar() {
		Connection con = null;
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:file:C:\\Users\\Cristian Vallecilla\\Documents\\workspace\\nEnLinea\\src\\nEnLinea\\base\\partidas","cristian", "");
			System.out.println("En linea ...");
		}catch(Exception ex) {
			System.out.println("Error "+ex);
		}
		return con;
	}
}
