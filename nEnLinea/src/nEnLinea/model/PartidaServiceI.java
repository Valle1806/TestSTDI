package nEnLinea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zul.Listbox;

import nEnLinea.base.Conexion;

public class PartidaServiceI implements PartidaService{
	
	private List<Partida> lst = new LinkedList<Partida>();
	private Conexion conexion = new Conexion();
	
	public  PartidaServiceI() {
		// TODO Auto-generated constructor stub
		conexion.conectar();
		Connection con = conexion.conectar();
		ResultSet rs = null;
		try {
			PreparedStatement ps = con.prepareStatement("select * from partida");
			rs = ps.executeQuery();
			Partida part = new Partida();
			while (rs.next()) {
				part.setNo(Integer.parseInt(rs.getString("NO")));
				part.setFecha(rs.getDate("FECHA").toString());
				part.setNombre1(rs.getString("NOMBRE1"));
				part.setNombre2(rs.getString("NOMBRE2"));
				part.setEstado(rs.getString("ESTADO"));
				part.setGanador(rs.getString("GANADOR"));
				lst.add(part);
				
			}
			System.out.println("Datos cargados ");
		} catch (Exception ex) {
			System.out.println("Error al traer las partidas " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
			}
		}
	}

	@Override
	public List<Partida> findAll() {
		// TODO Auto-generated method stub
		return lst;
	}
	@Override
	public void createPartida(String name1, String name2, String state, String winner) {
		// TODO Auto-generated method stub
		Connection con = conexion.conectar();
		int rs = 0;
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into partida(fecha, nombre1,nombre2,estado,ganador)values(current_timestamp,?,?,?,?);"
					);
			ps.setString(1, name1);
			ps.setString(2, name2);
			ps.setString(3, state);
			ps.setString(4, winner);
			/*
			ps.setString(1, part.getNombre1());
			ps.setString(2, part.getNombre2());
			ps.setString(3, part.getEstado());
			ps.setString(4, part.getGanador());*/
			rs = ps.executeUpdate();
			System.out.println("Partida creada. ");
		} catch (Exception ex) {
			System.out.println("Error al crear partida " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar conexion" + ex);
			}
		}
		
	}

}
