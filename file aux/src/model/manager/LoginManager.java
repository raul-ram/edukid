package model.manager;

import org.springframework.stereotype.Service;

import com.sun.org.apache.regexp.internal.recompile;

import varios.Conexion;

@Service
public class LoginManager extends Conexion {

	public int Autentificar(String usuario, String password) {
		try {
			String xsql = "select count(login) from usuario where usuario=? and clave=?";
			return this.db.queryForObject(xsql, Integer.class, new Object[] { usuario, password });
		} catch (Exception e) {
			return -1;
		}
	}

}
