package com.proyecto.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionManager {

	private static final String URL = "jdbc:mariadb://localhost:3306/bd_agenda_telefonica";
	private static final String USER = "root";
	private static final String PASSWORD = "3252";
	
	public static Connection obtenerConexion() throws SQLException {
		
		return DriverManager.getConnection(URL, USER, PASSWORD);
		
	}
	
}
