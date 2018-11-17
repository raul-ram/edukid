package controllers;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Proveedor;
import model.domain.Rol;
import model.manager.MenuManager;
import model.manager.EmpleadoManager;
import model.manager.RolManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/rol")
public class admRol {
	@Autowired
	RolManager rolManager;
	@Autowired
	MenuManager menuManager;
	JsonObject json = new JsonObject();

	@RequestMapping({ "list.html" })
	public String Home(Model m) {
		m.addAttribute("rol", this.rolManager.GetRoles());
		m.addAttribute("menus", this.menuManager.getMenus());
		return "rol/rol";
	}

	@RequestMapping({ "tableRol.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println("search " + buscador + " " + estado);
		m.addAttribute("rol", this.rolManager.ListFilteredOut(buscador, estado));
		m.addAttribute("menus", this.menuManager.getMenus());
		return "rol/tableRol";
	}

	@RequestMapping({ "save.html" })
	public @ResponseBody String Save(Model m, Rol r, int menus[]) {
		json.addProperty("ok", this.rolManager.AddRolme(r, menus));
		return json.getAsJsonObject().toString();
	}
	
	@RequestMapping({ "getModificar.html" })
	public String GetModificar(Model m, int id) {
		System.out.println(this.rolManager.GetRol(id));
		m.addAttribute("rol", this.rolManager.GetRol(id));
		m.addAttribute("asignado", this.rolManager.MenuNoAsignado(id));
		m.addAttribute("noAsignado", this.rolManager.MenuAsignado(id));
		System.out.println( this.rolManager.MenuNoAsignado(id));
		System.out.println(this.rolManager.MenuAsignado(id));
		return "rol/modRol";
	}
	
	@RequestMapping({ "modify.html" })
	public @ResponseBody String Modify(Model m, Rol r, int menus[]) {
		System.out.println(r.toString());
		json.addProperty("ok", this.rolManager.ModRolme(r, menus));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "delete.html" })
	public String Delete(Model m, int id) {
		// json.addProperty("ok", this.rolManager.Delete(id));
		if (this.rolManager.Delete(id)) {
			m.addAttribute("class", "alert-success");
			m.addAttribute("mensaje", "Exito al eliminar");
			return "mensaje";
		} else {
			m.addAttribute("class", "alert-danger");
			m.addAttribute("mensaje", "Error al eliminar");
			return "mensaje";/* json.getAsJsonObject().toString(); */
		}
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		json.addProperty("ok", this.rolManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "ver.html" })
	public String Ver(Model m, int id) {
		System.out.println(this.rolManager.GetRol(id));
		m.addAttribute("rol", this.rolManager.GetRol(id));
		m.addAttribute("menus", this.rolManager.MenuAsignado(id));
		return "rol/verRol";
	}

}
