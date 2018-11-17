package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Cliente;
import model.manager.CategoriaManager;
import model.manager.ClienteManager;
import model.manager.ProductoManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/venta")
public class admVenta {
	@Autowired
	ClienteManager clienteManager;
	
	@Autowired
	ProductoManager productoManager;
	
	JsonObject json = new JsonObject();

	
	@RequestMapping({ "list.html" })
	public String List(Model m) {
		return "venta/venta";
	}

	@RequestMapping({ "getFormAdd.html" })
	public String GetFormAdd(Model m) {
		m.addAttribute("producto", this.productoManager.GetProductos());
		return "venta/getFormAdd";
	}
	@RequestMapping({ "save.html" })
	public @ResponseBody String Save(Model m, Cliente c) {
		System.out.println(c.toString());
		json.addProperty("ok", this.clienteManager.Save(c));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "delete.html" })
	public @ResponseBody String Delete(Model m, int id) {
		json.addProperty("ok", this.clienteManager.Delete(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		json.addProperty("ok", this.clienteManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}
	
	@RequestMapping({ "getModificar.html" })
	public String GetModificar(Model m, int id) {
		m.addAttribute("i", this.clienteManager.GetCliente(id));
		return "cliente/modCli";
	}
	@RequestMapping({ "modify.html" })
	public @ResponseBody String Modify(Model m, Cliente c) {
		System.out.println(c.toString());
		json.addProperty("ok", this.clienteManager.Update(c));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "ver.html" })
	public String Ver(Model m, int id) {
		m.addAttribute("i", this.clienteManager.GetCliente(id));
		return "cliente/verCli";
	}

	@RequestMapping({ "tableVenta.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println("search " + buscador + " " + estado);
		//System.out.println(this.centeManager.ListFilteredOut(buscador, estado));
		m.addAttribute("clientes", this.clienteManager.ListFilteredOut(buscador, estado));
		return "cliente/tableCli";
	}
}
