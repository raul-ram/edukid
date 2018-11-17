package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Rol;
import varios.Conexion;

@Service
public class RolManager extends Conexion {

	public class Mapear implements RowMapper<Rol> {
		@Override
		public Rol mapRow(ResultSet res, int arg1) throws SQLException {
			Rol m = new Rol();
			m.setIdrol(res.getInt("idrol"));
			m.setNombre(res.getString("nombre"));
			m.setEstado(res.getInt("estado"));
			return m;
		}
	}

	public int GenerateIdrol() {
		String sql = "select count(*) from rol";
		return this.db.queryForObject(sql, Integer.class) + 1;
	}

	public List<Rol> GetRoles() {
		String sql = "select * from rol order by  idrol asc";
		return db.query(sql, new Mapear());
	}

	public Rol GetRol(int id) {
		String sql = "select * from rol where idrol=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public boolean Delete(int id) {
		String sql1 = "update rol set estado=0 where idrol=?";
		try {
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}

	public boolean Habilitar(int id) {
		try {
			String sql1 = "update rol set estado=1 where idrol=?";
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public List<Rol> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from rol where nombre LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}

	/********* ROLME *******/
	public boolean AddRolme(Rol r, int menus[]) {
		try {
			int idrol = GenerateIdrol();
			String sql1 = "insert into rol values(?,?,1)";
			String sql2 = "insert into rolme values(?,?)";
			this.db.update(sql1, idrol, r.getNombre());
			for (int j = 0; j < menus.length; j++) {
				db.update(sql2, menus[j], idrol);
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean ModRolme(Rol r, int menus[]) {
		try {

			String sql1 = "delete from rolme where idrol=?";
			String sql2 = "insert into rolme values(?,?)";
			this.db.update(sql1, r.getIdrol());

			for (int j = 0; j < menus.length; j++) {
				db.update(sql2, menus[j], r.getIdrol());
			}
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public List<Map<String, Object>> MenuAsignado(int id) {
		String sql = "select m.* from rol r, menu m, rolme rm where rm.idmenu = m.idmenu and rm.idrol = r.idrol and r.idrol=?";
		return this.db.queryForList(sql, new Object[] { id });
	}

	public List<Map<String, Object>> MenuNoAsignado(int id) {
		String sql = "select distinct m.* from menu m, rolme r where m.idmenu not in (select m.idmenu from rol ro, menu m, rolme rm where rm.idrol=ro.idrol and rm.idmenu=m.idmenu and ro.idrol=?)";
		return this.db.queryForList(sql, new Object[] { id });
	}
}
