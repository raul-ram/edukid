package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Sesion {
	private HttpSession sesion;
	private HttpServletRequest req;

	public boolean CreateSesion(String username) {
		try {
			sesion = req.getSession(true);
			sesion.setAttribute("usuario", username);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean CloseSession(String username) {
		try {
			sesion = req.getSession(true);
			sesion.removeAttribute(username);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String GetSesion(){
		return (String) sesion.getAttribute("usuario");
	}
}
