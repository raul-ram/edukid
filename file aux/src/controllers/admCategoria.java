package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import model.domain.Categoria;
import model.domain.Proveedor;
import model.manager.CategoriaManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/categoria")
public class admCategoria {
	@Autowired
	CategoriaManager cateManager;
	JsonObject json = new JsonObject();

	@RequestMapping({ "list.html" })
	public String List(Model m) {
		m.addAttribute("cate", this.cateManager.GetCategorias());
		return "categoria/categoria";
	}

	@RequestMapping({ "save.html" })
	public @ResponseBody String Save(Model m, Categoria c) {
		json.addProperty("ok", this.cateManager.Save(c));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "delete.html" })
	public @ResponseBody String Delete(Model m, int id) {
		json.addProperty("ok", this.cateManager.Delete(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		json.addProperty("ok", this.cateManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}
	
	@RequestMapping({ "getModificar.html" })
	public String GetModificar(Model m, int id) {
		m.addAttribute("p", this.cateManager.GetCategoria(id));
		return "categoria/modCat";
	}
	@RequestMapping({ "modify.html" })
	public @ResponseBody String Modify(Model m, Categoria c) {
		System.out.println(c.toString());
		json.addProperty("ok", this.cateManager.Update(c));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "ver.html" })
	public String Ver(Model m, int id) {
		m.addAttribute("i", this.cateManager.GetCategoria(id));
		return "categoria/verCat";
	}

	@RequestMapping({ "tableCat.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println("search " + buscador + " " + estado);
		m.addAttribute("cate", this.cateManager.ListFilteredOut(buscador, estado));
		return "categoria/tableCat";
	}
}
