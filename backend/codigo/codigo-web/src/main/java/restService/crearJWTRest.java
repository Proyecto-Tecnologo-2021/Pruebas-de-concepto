package restService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import business.IcrearJWT;


@RequestScoped
@Path("/obtenerJWT")
@Consumes("application/json")
@Produces("application/json")
public class crearJWTRest {
	
	@EJB
	IcrearJWT crearJWT;
	
	@GET
	public Response crearJWT() {
		RespuestaREST<String> respuesta = null;
		String jwt = crearJWT.crearJsonWebToken();
		respuesta = new RespuestaREST<String>(true, "JWT creado con éxito.", jwt);
		return Response.ok(respuesta).build();
	}
	
}
