package com.proyecto.dao;

import com.proyecto.conexion.ConexionManager;
import com.proyecto.entidades.Contacto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ContactoDAO {
	
	//Metodo para Agregar Contacto
	public void agregarContacto(Contacto c) throws SQLException {
        String sql = "INSERT INTO contactos(nombre, telefono, email, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionManager.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setInt(2, c.getTelefono());
            stmt.setString(3, c.getEmail());
            stmt.setDate(4, Date.valueOf(c.getFecha_nacimiento()));
            stmt.executeUpdate();
        }
    }

	//Metodo listar todos los contactos
    public List<Contacto> obtenerTodos() throws SQLException {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT * FROM contactos";
        try (Connection conn = ConexionManager.obtenerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contacto c = new Contacto();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getInt("telefono"));
                c.setEmail(rs.getString("email"));
                c.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                lista.add(c);
            }
        }
        return lista;
    }

    //Metodo para actualizar contacto
    public void actualizarContacto(Contacto c) throws SQLException {
        String sql = "UPDATE contactos SET nombre=?, telefono=?, email=?, fecha_nacimiento=? WHERE id=?";
        try (Connection conn = ConexionManager.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNombre());
            stmt.setInt(2, c.getTelefono());
            stmt.setString(3, c.getEmail());
            stmt.setDate(4, Date.valueOf(c.getFecha_nacimiento()));
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();
        }
    }

    //Metodo para eliminar contacto
    public void eliminarContacto(int id) throws SQLException {
        String sql = "DELETE FROM contactos WHERE id=?";
        try (Connection conn = ConexionManager.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    //Metodo para buscar un contacto por nombre
    public Contacto buscarPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM contactos WHERE nombre=?";
        try (Connection conn = ConexionManager.obtenerConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Contacto c = new Contacto();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setTelefono(rs.getInt("telefono"));
                    c.setEmail(rs.getString("email"));
                    c.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                    return c;
                }
            }
        }
        return null;
    }
	
}
