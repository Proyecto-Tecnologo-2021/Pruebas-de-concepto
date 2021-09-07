package business;

import javax.ejb.Local;

import dto.LoginExitosoDTO;
import dto.loginDTO;

@Local
public interface IcrearJWT {

	public LoginExitosoDTO crearJsonWebToken(loginDTO loginDTO);
	public boolean loginExitoso(loginDTO loginDTO);
	
}
