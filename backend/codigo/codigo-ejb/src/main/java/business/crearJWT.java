package business;

import java.util.Date;

import javax.ejb.Stateless;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import util.constantes;

@Stateless
public class crearJWT implements IcrearJWT{
	
	public String crearJsonWebToken() {
		Date ahora = new Date();
		/* 1 horas de validez */
		Date expiracion = new Date(ahora.getTime() + (1000*60*60));
		return Jwts.builder()
				.setIssuedAt(ahora)
				.setExpiration(expiracion)
				.signWith(SignatureAlgorithm.HS512, constantes.JWT_KEY)
				.compact();
	}
	
}
