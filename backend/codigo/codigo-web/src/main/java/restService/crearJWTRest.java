package restService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import business.IcrearJWT;
import dto.LoginExitosoDTO;
import dto.loginDTO;


@RequestScoped
@Path("/obtenerJWT")
@Consumes("application/json")
@Produces("application/json")
public class crearJWTRest {
	
	@EJB
	IcrearJWT crearJWT;
	
	//@GET
	@PUT
	public Response crearJWT(loginDTO loginDTO) {
		RespuestaREST<LoginExitosoDTO> respuesta = null;
		if (crearJWT.loginExitoso(loginDTO)) {
			LoginExitosoDTO login = crearJWT.crearJsonWebToken(loginDTO);
			respuesta = new RespuestaREST<LoginExitosoDTO>(true, "JWT creado con éxito.", login);
			return Response.ok(respuesta).build();
		}else {
			respuesta = new RespuestaREST<>(false, "El usuario no está registrado en el sistema");
			return Response.ok(respuesta).build();
		}
	
}
}	