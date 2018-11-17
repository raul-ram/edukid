package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import model.domain.Producto;
import model.manager.ProductoManager;
import model.manager.CategoriaManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/producto")
public class admProducto {
	@Autowired
	ProductoManager productoManager;
	@Autowired
	CategoriaManager categoriaManager;
	JsonObject json;

	@RequestMapping({ "list.html" })
	public String List(Model m) {
		m.addAttribute("producto", this.productoManager.GetProductos());
		m.addAttribute("categoria", this.categoriaManager.GetCategorias());
		return "producto/producto";
	}

	@RequestMapping({ "tablePro.html" })
	public String Table(Model m, String buscador, int estado) {
		System.out.println(buscador + " " + estado);
		System.out.println(this.productoManager.ListFiltro(buscador, estado));
		m.addAttribute("producto", this.productoManager.ListFiltro(buscador, estado));
		m.addAttribute("categoria", this.categoriaManager.GetCategorias());
		return "producto/tablePro";
	}

	@RequestMapping({ "getForm.html" })
	public String GetForm(Model m) {
		m.addAttribute("categoria", this.categoriaManager.GetCategorias());
		return "producto/getForm";
	}

	@RequestMapping({ "guardar.html" })
	@Transactional()
	public String Guardar(@RequestParam("foto") MultipartFile file,Producto p, Model model, HttpServletRequest req,
			HttpServletResponse res) throws IOException, ParseException {
		// recepcionamos todo del formulario nuevaPersona
		// para subir la foto del usuario
		System.out.println(p.toString());
		
		ServletContext ctx = req.getSession().getServletContext();
		System.out.println("Es el Path=" + ctx.getRealPath("/"));

		String fileName = null;
		String foto = null;

		System.out.println(fileName);

		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				System.out.println("nombre completo=" + fileName);

				String[] x = fileName.split("\\.");
				foto = p.getNombre() + p.getMarca() + "." + x[x.length - 1];
				System.out.println("mandar: " + foto);

				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File(ctx.getRealPath("/") + "uploads/" + foto)));

				buffStream.write(bytes);
				buffStream.close();

			} catch (Exception e) {
				return "ERROR AL ENVIAR " + fileName + ": " + e.getMessage();
			}
		} else {
			return "NO HAY ARCHIVOS SELECCIONADOS PARA EL ENVIO..";
		}

		return "mensaje";
	}

	@RequestMapping({ "Gu.html" })
	public String Save(@RequestParam("foto") MultipartFile file, Model m, Producto p, HttpServletRequest req)
			throws IOException, Exception {
		System.out.println("holaaaa  "+p.toString());
		// json.addProperty("ok", this.productoManager.Save(p));
		// return json.getAsJsonObject().toString();
		ServletContext ctx = req.getSession().getServletContext();
		System.out.println("Es el Path=" + ctx.getRealPath("/"));

		String fileName = null;
		String foto = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				System.out.println("fileName Original=" + fileName);

//				String[] x = fileName.split("\\.");
//				foto = p.getNombre() + p.getCategoria() + p.getMarca() + "." + x[x.length - 1];
//				System.out.println("mandar cambio: " + foto);
//				p.setFoto(foto);
//				byte[] bytes = file.getBytes();
//				BufferedOutputStream buffStream = new BufferedOutputStream(
//						new FileOutputStream(new File(ctx.getRealPath("/") + "uploads/productos/" + foto)));
//				buffStream.write(bytes);
//				buffStream.close();
				return "producto/VerPro";
			} catch (Exception e) {
				System.out.println("errorr");
				e.printStackTrace();
				return "ERROR AL ENVIAR " + fileName + ": " + e.getMessage();
			}
		} else {
			return "NO HAY ARCHIVOS SELECCIONADOS PARA EL ENVIO..";
		}
	}
	
	@RequestMapping({ "save.html" })
	public String v2(Model model, HttpServletRequest req, HttpServletResponse res,
			@RequestParam("foto") MultipartFile file) throws IOException {
		System.out.println("llego al mapeo para agregar imagen");
		
		// para subir la foto del usuario
		ServletContext ctx = req.getSession().getServletContext();
		System.out.println("Es el Path=" + ctx.getRealPath("/"));

		String fileName = null;
		String foto = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				System.out.println("fileName Edwin=" + fileName);

//				String[] x = fileName.split("\\.");
//				foto = cod_grl + cod_ser + cod_cat + "." + x[x.length - 1];
//				System.out.println("mandar cambio: " + foto);
//
//				byte[] bytes = file.getBytes();
//				BufferedOutputStream buffStream = new BufferedOutputStream(
//						new FileOutputStream(new File(ctx.getRealPath("/") + "imagenes/Servicios/" + foto)));
//
//				buffStream.write(bytes);
//				buffStream.close();
				// int respuesta = this.managerservicio.addServicio(nombre,
				// precio, foto);

			} catch (Exception e) {
				// return "ERROR AL ENVIAR " + fileName + ": " + e.getMessage();
				System.out.println(e.getMessage());
			}
		} else {
			return null;
		}
		// System.out.println(nombre + " " + precio);
//		String cod_ser_es = cod_grl + cod_ser + cod_cat;
//		int resul = this.managerservicio.addServicio(cod_ser_es, Integer.parseInt(cod_ser), Integer.parseInt(cod_grl),
//				Integer.parseInt(cod_cat), Integer.parseInt(precio), foto);
//		System.out.println("respuesta:" + resul);
		return null;
	}
	

	@RequestMapping({ "GetModificar.html" })
	public String GetModificar(Model m, int id) {
		Producto p = this.productoManager.GetProducto(id);
		System.out.println(p.toString());
		m.addAttribute("producto", this.productoManager.GetProducto(id));
		m.addAttribute("categoria", this.categoriaManager.GetCategorias());
		return "producto/VerPro";
	}

	@RequestMapping({ "delete.html" })
	public @ResponseBody String Delete(Model m, int id) {
		json.addProperty("ok", this.productoManager.Delete(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "habilitar.html" })
	public @ResponseBody String Habilitar(Model m, int id) {
		System.out.println("ctrl habilitar " + id);
		json.addProperty("ok", this.productoManager.Habilitar(id));
		return json.getAsJsonObject().toString();
	}

	@RequestMapping({ "verTodo.html" })
	public String VerTodo(Model m) {
		m.addAttribute("producto", this.productoManager.GetProductos());
		m.addAttribute("categoria", this.categoriaManager.GetCategorias());
		return "producto/verProductos";
	}
}
