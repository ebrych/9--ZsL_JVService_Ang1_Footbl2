package com.Football2Services.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.Football2Services.Beans.*;
import com.Football2Services.Model.*;

@Path("/Login")
public class LoginController {
	
	DataAccessModel model = new DataAccessModel();
	
	@POST
	@Path("/iniciarSession")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario iniciarSession(@QueryParam("usuario") String user,@QueryParam("pass") String pass){
		Usuario us = new Usuario();
		us=model.inicioSesion(user, pass);
		return us;
	}
	
	
	
	
	
	
}
