package controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.manager.MenuManager;
import model.manager.EmpleadoManager;
import model.manager.RolManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/*")
public class home {
	@Autowired
	EmpleadoManager personalManager;
	@Autowired
	MenuManager menuManager;
	@Autowired
	RolManager rolManager;

	Sesion sesion;
	
	@RequestMapping({ "home.html" })
	public String Home(Model m) {
		//if(sesion.GetSesion()!= null){
		System.out.println(this.menuManager.getMenus().toString());
		m.addAttribute("menu", this.menuManager.getMenus());
		m.addAttribute("usuario", "admin");
		m.addAttribute("fecha", "Hoy es: " + LocalDate.now()
				.format(DateTimeFormatter.ISO_DATE));
			return "home";
//		}else{
//			m.addAttribute("mensaje", "ACCESO NO PERMITIDO... O SESSION CADUCADA!");
//			m.addAttribute("enlace", "login.html");
//			return "alerta";
//		}
	}

	@PostMapping({ "index.html" })
	public String Index(Model m) {

		// List<Rol> rol = this.rolManager.GetRol();
		// System.out.println(rol.toString());
		m.addAttribute("menu", this.menuManager.getMenus());
		// m.addAttribute("rol", this.rolManager.getMenus());
		System.out.println(this.menuManager.getMenus().toString());
		// System.out.println(this.rolManager.getMenus().toString());
		return "personal";
	}

	@RequestMapping({ "mepro.html" })
	public @ResponseBody String mepro(Model m, int menus[]) {
		JsonObject json = new JsonObject();
		if (true) {
			for (int i = 0; i < menus.length; i++) {
				System.out.println(menus[i]);
			}
			json.addProperty("exito", true);
			return json.getAsJsonObject().toString();
		} else {
			json.addProperty("exito", false);
			return json.getAsJsonObject().toString();
		}
	}
}
