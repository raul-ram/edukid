package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Proveedor;
import model.domain.Rol;
import model.manager.ProveedorManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/proveedor" })
public class admProveedor {
	@Autowired
	ProveedorManager proveedorManager;
	JsonObject json = new JsonObject();

	@RequestMapping({ "list.html" })
	public String List(Model m) {
		m.addAttribute("proveedor", this.proveedorManager.GetProveedores());
		System.out.println(this.proveedorManager.GetProveedores().toString());
		return "proveedor/proveedor";
	}

	@RequestMapping({ "save.html" })
	public @ResponseBody String Save(Model m, Proveedor p) {
		System.out.println(p.toString());
		json.addProperty("ok", this.proveedorManager.Save(p));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "delete.html" })
	public @ResponseBody String Delete(Model m, int id) {
		json.addProperty("ok", this.proveedorManager.Delete(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		json.addProperty("ok", this.proveedorManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "getModificar.html" })
	public String GetModificar(Model m, int id) {
		m.addAttribute("p", this.proveedorManager.GetProveedor(id));
		return "proveedor/modProv";
	}
	@RequestMapping({ "modify.html" })
	public @ResponseBody String Modify(Model m, Proveedor p) {
		System.out.println(p.toString());
		json.addProperty("ok", this.proveedorManager.Update(p));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "ver.html" })
	public String Ver(Model m, int id) {
		m.addAttribute("p", this.proveedorManager.GetProveedor(id));
		return "proveedor/verProv";
	}

	@RequestMapping({ "tableProv.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println("search " + buscador + " " + estado);
		m.addAttribute("proveedor", this.proveedorManager.ListFilteredOut(buscador, estado));
		return "proveedor/tableProv";
	}

}
