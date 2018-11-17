package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import model.domain.Menu;
import varios.Conexion;

@Service
public class MenuManager extends Conexion {

	public class Mapear implements RowMapper<Menu> {
		@Override
		public Menu mapRow(ResultSet res, int arg1) throws SQLException {
			Menu m = new Menu();
			m.setIdmenu(res.getInt("idmenu"));
			m.setNombre(res.getString("nombre"));
			m.setEnlace(res.getString("enlace"));
			m.setIcon(res.getString("icon"));
			return m;
		}
	}

	public List<Menu> getMenus() {
		String sql = "select * from menu";
		return db.query(sql, new Mapear());
	}

	public List<Map<String, Object>> personal() {
		String sql = "select * from personal";
		return db.queryForList(sql, new Object[] {});
	}
	
	/*public int SaveMenu(MenusModel c) {
		String sql = "insert into menus(nombre, estado) values( ?, 1);";
		return this.db.update(sql, new Object[] { c.getNombre() });
	}

	public int DeleteMenu(MenusModel c) {
		String sql = "update menus set estado=0 where codm=?";
		return this.db.update(sql, new Object[] { c.getCodm() });
	}

	// recuperar menu por codigo para modificar

	public List<Map<String, Object>> datoMenu(MenusModel c) {
		String sql = "select * from menus where codm=?";
		return this.db.queryForList(sql, new Object[] { c.getCodm() });
	}

	// guardar lo modificado
	public int SaveModMenu(MenusModel c) {
		System.out.println(c.toString());
		String sql = "update menus set nombre=?, estado=? where codm=?;";
		return this.db.update(sql, new Object[] { c.getNombre(), c.getEstado(), c.getCodm() });
	}

	public List<Map<String, Object>> getProcesos() {
		String sql = "select * from procesos";
		return this.db.queryForList(sql, new Object[] {});
	}

	
	//procesos
	public List<Map<String, Object>> submenus() {
		String sql = "select m.codm, p.nombre, p.enlace from mepro m, procesos p where m.codp = p.codp";
		return this.db.queryForList(sql, new Object[] {});
	}
	
	//Lista de procesos asignados al menu
	public List<Map<String, Object>> asignados(int codm) {
		String xsql = "select p.* from menus m, procesos p, mepro mp "
				+ " where mp.codm = m.codm and mp.codp = p.codp and m.codm=?";
		return this.db.queryForList(xsql, new Object[] { codm });
	}

	// lista de los procesos  no asignados
	public List<Map<String, Object>> noAsignados(int codm) {
		System.out.println("paso " + codm);

			String xsql = "select distinct p.* from procesos p, mepro m  where p.estado=1 and p.codp=m.codp and p.codp not in (select p.codp "
					+ "from menus m, procesos p, mepro mp where mp.codm=m.codm and mp.codp=p.codp and m.codm=?)";
		return this.db.queryForList(xsql, new Object[] { codm });
	}*/
}
