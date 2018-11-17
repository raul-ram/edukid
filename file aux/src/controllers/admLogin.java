package controllers;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Rol;
import model.manager.MenuManager;
import model.manager.EmpleadoManager;
import model.manager.LoginManager;
import model.manager.RolManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/*")
public class admLogin {
	JsonObject json = new JsonObject();
	@Autowired 
	LoginManager logiManager;
	Sesion Sesion;
	@RequestMapping({ "login.html" })
	public String Home() {

		return "login";
	}

	@RequestMapping({ "verifica.html" })
	public @ResponseBody String Verifica(String username, String password) {
		System.out.println(username + " " + password);
		int verifica = this.logiManager.Autentificar(username, password);
		if (verifica != 1) {
			//crear sesion con el nombre del usuario
			//System.out.println(Sesion.CreateSesion(username));
			json.addProperty("exito", true);
			return json.getAsJsonObject().toString();
		} else {
			json.addProperty("exito", false);
			return json.getAsJsonObject().toString();
		}
	}

}
