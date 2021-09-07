package business;

import java.util.Date;

import javax.ejb.Stateless;

import dto.LoginExitosoDTO;
import dto.loginDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import util.constantes;

@Stateless
public class crearJWT implements IcrearJWT{
	
	@Override
	public LoginExitosoDTO crearJsonWebToken(loginDTO loginDTO) {
		String token = crearJWTAux();
		String correo = loginDTO.getCorreo();
		String telefono = "1234567";
		LoginExitosoDTO login = new LoginExitosoDTO(correo, telefono, token);
		return login;
	}

	public String crearJWTAux() {
		Date ahora = new Date();
		/* 1 horas de validez */
		Date expiracion = new Date(ahora.getTime() + (1000*60*60));
		return Jwts.builder()
				.setIssuedAt(ahora)
				.setExpiration(expiracion)
				.signWith(SignatureAlgorithm.HS512, constantes.JWT_KEY)
				.compact();
	}
	
	@Override
	public boolean loginExitoso(loginDTO loginDTO) {
		
		String correoUsuario1 = "correo1@gmail.com";
		String correoUsuario2 = "correo2@gmail.com";
		
		String passUsuario1 = "Prueba1";
		String passUsuario2 = "Prueba2";
		
		if (((loginDTO.getCorreo().equals(correoUsuario1)) && (loginDTO.getPassword().equals(passUsuario1))) || ((loginDTO.getCorreo().equals(correoUsuario2)) && (loginDTO.getPassword().equals(passUsuario2)))){
			return true;
		}else {
			return false;
		}
	}
	
}
