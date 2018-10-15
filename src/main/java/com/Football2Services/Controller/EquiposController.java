package com.Football2Services.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Football2Services.Beans.Equipo;
import com.Football2Services.Beans.Torneo;
import com.Football2Services.Model.DataAccessModel;

@Path("/Equipo")
public class EquiposController {
	DataAccessModel model = new DataAccessModel();
	
	@POST
	@Path("/lista")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Equipo> listarEquipos(@QueryParam("token") String tk,@QueryParam("idtorneo") String id){
		List<Equipo> equipos = new ArrayList<Equipo>();
		equipos=model.listarEquipos(tk, id);
		return equipos;
	}
	
	@POST
	@Path("/agregar")
	@Produces({MediaType.APPLICATION_JSON})
	public void agregarEquipo(@QueryParam("token") String tk,@QueryParam("id") String id,@QueryParam("nombre") String nombre,@QueryParam("logo") String logo,@QueryParam("idTorneo") String idTorneo){
		model.agregarEquipo(tk,nombre,logo,id,idTorneo);
	}
	
	@POST
	@Path("/buscar")
	@Produces({MediaType.APPLICATION_JSON})
	public Equipo buscarEquipo(@QueryParam("token") String tk,@QueryParam("id") String id){
		Equipo equipo = new Equipo();
		equipo=model.buscarEquipo(tk, id);
		return equipo;
	}
	
	
	
	
	
	//generaCod
	public String generaCode() {
		char[] elementos={'0','1','2','3','4','5','6','7','8','9' ,'a',
				'b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t',
				'u','v','w','x','y','z'};
		char[] conjunto = new char[5];
		for(int i=0;i<5;i++){
			int el = (int)(Math.random()*37);
			conjunto[i] = (char)elementos[el];
		}
		return new String(conjunto);
	}
	
}
