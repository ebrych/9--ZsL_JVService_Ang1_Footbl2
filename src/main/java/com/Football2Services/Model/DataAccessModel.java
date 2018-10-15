package com.Football2Services.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.Football2Services.Beans.*;

public class DataAccessModel {
	
	Conection cnx = new Conection();
	Connection conn= null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	//test
	public void test(){
		try {
			conn = cnx.dbConnect();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//inicioSesion
	public Usuario inicioSesion(String usr,String ps) {
		Usuario us = new Usuario();
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_INICIAR_SESION '"+usr+"','"+ps+"' ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				us.setNombre(rs.getString("nombre"));
				us.setToken(rs.getString("token"));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return us;
	}
	
	//lista de torneos
	public List<Torneo> listaTorneos(){
		List<Torneo> lista = new ArrayList<Torneo>();
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_TORNEO_LISTAR";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Torneo tr = new Torneo();
				tr.setId(rs.getInt("TORNEO_ID"));
				tr.setNombre(rs.getString("NOMBRE_TORNEO"));
				tr.setFechaInicio(rs.getDate("FECHA_INICIO").toString());
				tr.setFechaFin(rs.getDate("FECHA_FIN").toString());
				tr.setLogo(rs.getString("LOGO"));
				tr.setEstado(rs.getString("ESTADO"));
				lista.add(tr);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return lista;
	}
	
	//agregarTorneo
	public void agregarTorneo(String tk,String id,String nom,String inicio,String fin,String logo,String estado) {
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_TORNEO_CREAR_ACTUALIZAR '"+tk+"','"+nom+"','"+inicio+"','"+fin+"','"+logo+"','"+estado+"','"+id+"' ";
			pstm = conn.prepareStatement(sql);
			pstm.executeQuery();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//buscarTorneo
	public Torneo buscarTorneo (String tk, String id) {
		Torneo tr = new Torneo();
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_TORNEO_BUSCAR '"+tk+"','"+id+"' ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				tr.setId(rs.getInt("TORNEO_ID"));
				tr.setNombre(rs.getString("NOMBRE_TORNEO"));
				tr.setFechaInicio(rs.getDate("FECHA_INICIO").toString());
				tr.setFechaFin(rs.getDate("FECHA_FIN").toString());
				tr.setLogo(rs.getString("LOGO"));
				tr.setEstado(rs.getString("ESTADO"));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return tr;
	}
	
	
	
	//listarEquipos
	public List<Equipo> listarEquipos (String tk,String idTorneo){
		List<Equipo> lista = new ArrayList<Equipo>();
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_EQUIPO_LISTAR '"+tk+"',"+idTorneo+" ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				Equipo eq = new Equipo();
				eq.setId(rs.getInt("EQUIPO_ID"));
				eq.setNombre(rs.getString("NOMBRE_EQUIPO"));
				eq.setLogo(rs.getString("LOGO"));
				lista.add(eq);
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return lista;
	}
	
	//agregarEquipo
	public void agregarEquipo(String tk,String nombre,String logo,String id,String idTorneo) {
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_EQUIPO_CREAR_ACTUALIZAR '"+tk+"','"+nombre+"','"+logo+"',"+id+","+idTorneo+" ";
			pstm = conn.prepareStatement(sql);
			pstm.executeQuery();
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	//buscarEquipo
	public Equipo buscarEquipo (String tk,String id) {
		Equipo eq = new Equipo();
		try {
			conn = cnx.dbConnect();
			String sql ="exec SP_EQUIPO_BUSCAR '"+tk+"','"+id+"' ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				eq.setId(rs.getInt("EQUIPO_ID"));
				eq.setNombre(rs.getString("NOMBRE_EQUIPO"));
				eq.setLogo(rs.getString("LOGO"));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return eq;
	}
	
	
	
	
	

}
