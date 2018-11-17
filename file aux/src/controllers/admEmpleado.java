package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Categoria;
import model.domain.Cliente;
import model.domain.Empleado;
import model.manager.CategoriaManager;
import model.manager.EmpleadoManager;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/empleado")
public class admEmpleado {
	@Autowired
	EmpleadoManager empleadoManager;
	JsonObject json = new JsonObject();

	@RequestMapping({ "list.html" })
	public String List(Model m) {
		return "empleado/empleado";
	}

	@RequestMapping({ "save.html" })
	public @ResponseBody String Save(Model m, Empleado e) throws ParseException {
		System.out.println(e.toString());
		json.addProperty("ok", this.empleadoManager.Save(e));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "delete.html" })
	public @ResponseBody String Delete(Model m, int id) {
		json.addProperty("ok", this.empleadoManager.Delete(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		json.addProperty("ok", this.empleadoManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "getModificar.html" })
	public String GetModificar(Model m, int id) {
		System.out.println("getmodificar "+id);
		m.addAttribute("e", this.empleadoManager.GetEmpleado(id));
		return "empleado/modEmpleado";
	}

	@RequestMapping({ "modify.html" })
	public @ResponseBody String Modify(Model m, Empleado e) throws ParseException {
		System.out.println("modificar "+e.toString());
		json.addProperty("ok", this.empleadoManager.Update(e));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "verEmp.html" })
	public String Ver(Model m, int id) {
		System.out.println("ver "+id);
		m.addAttribute("i", this.empleadoManager.GetEmpleado(id));
		return "empleado/verEmp";
	}

	@RequestMapping({ "table.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println("search " + buscador + " " + estado);
		System.out.println(this.empleadoManager.ListFilteredOut(buscador, estado));
		m.addAttribute("clientes", this.empleadoManager.ListFilteredOut(buscador, estado));
		return "empleado/table";
	}
}