package controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import varios.GeneradorReportes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/reportes")
public class reportes {
	@Autowired
	DataSource dataSource;
	
	@RequestMapping({ "list.html" })
	public String List(Model m) {
		return "reportes/list";
	}

	@RequestMapping("report1.html")
	public void Producto(HttpServletRequest req, HttpServletResponse res) throws IOException, JRException {
		//HttpSession sesion = req.getSession(true);
		//1String login = (String) sesion.getAttribute("usuario");
		int estado = Integer.parseInt(req.getParameter("estado"));
		// Map<String, Object> paraPer = new HashMap<>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		String reportUrl = "/reportes/productos.jasper";
		System.out.println("reportes productos");
		parametros.put("username", "Admin");
		//parametros.put("estado", estado);
		System.out.println(parametros.toString());
		GeneradorReportes rep = new GeneradorReportes();
		try {
			rep.generarReporte(res, getClass().getResource(reportUrl), "pdf", parametros, dataSource.getConnection(),
					"Lista de" + "productos", "inline");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error+" + e.toString());
		}
	}
	
	@RequestMapping("cliente.html")
	public void Cliente(HttpServletRequest req, HttpServletResponse res) throws IOException, JRException {
		//HttpSession sesion = req.getSession(true);
		//1String login = (String) sesion.getAttribute("usuario");
		int estado = Integer.parseInt(req.getParameter("estado"));
		// Map<String, Object> paraPer = new HashMap<>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		String reportUrl = "/reportes/cliente.jasper";
		System.out.println("reportes productos");
		parametros.put("username", "Admin");
		//parametros.put("estado", estado);
		System.out.println(parametros.toString());
		GeneradorReportes rep = new GeneradorReportes();
		try {
			rep.generarReporte(res, getClass().getResource(reportUrl), "pdf", parametros, dataSource.getConnection(),
					"Lista de" + "Clientes", "inline");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error+" + e.toString());
		}
	}
	
	@RequestMapping("empleado.html")
	public void Empleado(HttpServletRequest req, HttpServletResponse res) throws IOException, JRException {
		//HttpSession sesion = req.getSession(true);
		//1String login = (String) sesion.getAttribute("usuario");
		int estado = Integer.parseInt(req.getParameter("estado"));
		// Map<String, Object> paraPer = new HashMap<>();
		Map<String, Object> parametros = new HashMap<String, Object>();
		String reportUrl = "/reportes/empleados.jasper";
		System.out.println("reportes productos");
		parametros.put("username", "Admin");
		//parametros.put("estado", estado);
		System.out.println(parametros.toString());
		GeneradorReportes rep = new GeneradorReportes();
		try {
			rep.generarReporte(res, getClass().getResource(reportUrl), "pdf", parametros, dataSource.getConnection(),
					"Lista de" + "empleados", "inline");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error+" + e.toString());
		}
	}

}
