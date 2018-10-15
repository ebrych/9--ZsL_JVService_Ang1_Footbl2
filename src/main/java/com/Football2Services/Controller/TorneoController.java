package com.Football2Services.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Football2Services.Beans.Torneo;
import com.Football2Services.Beans.Usuario;
import com.Football2Services.Model.DataAccessModel;

@Path("/Torneos")
public class TorneoController {
	DataAccessModel model = new DataAccessModel();
	
	@POST
	@Path("/lista")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Torneo> listarTorneo(){
		List<Torneo> torneos =new ArrayList<Torneo>();
		torneos=model.listaTorneos();
		return torneos;
	}
	
	@POST
	@Path("/agregar")
	@Produces({MediaType.APPLICATION_JSON})
	public void agregarTorneo(@QueryParam("token") String tk,@QueryParam("id") String id,@QueryParam("nombre") String nom,@QueryParam("inicio") String inicio,@QueryParam("fin") String fin,@QueryParam("logo") String logo,@QueryParam("estado") String estado){
		System.out.println(tk+"/"+id+"/"+nom+"/"+inicio+"/"+fin+"/"+logo+"/"+estado);
		model.agregarTorneo(tk,id,nom, inicio, fin,logo,estado);
	}
	
	
	@POST
	@Path("/buscar")
	@Produces({MediaType.APPLICATION_JSON})
	public Torneo buscaTorneo(@QueryParam("token") String tk,@QueryParam("id") String id) {
		Torneo tr = new Torneo();
		tr=model.buscarTorneo(tk, id);
		return tr;
	}
	
	
	
	
}
