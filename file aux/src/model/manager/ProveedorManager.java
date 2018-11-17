package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Proveedor;
import model.domain.Rol;
import model.manager.RolManager.Mapear;
import varios.Conexion;

@Service
public class ProveedorManager extends Conexion {

	public class Mapear implements RowMapper<Proveedor> {
		@Override
		public Proveedor mapRow(ResultSet res, int arg1) throws SQLException {
			Proveedor o = new Proveedor();
			o.setIdproveedor(res.getInt("idproveedor"));
			o.setNombre(res.getString("nombre"));
			o.setDireccion(res.getString("direccion"));
			o.setEmail(res.getString("email"));
			o.setDescripcion(res.getString("descripcion"));
			o.setEstado(res.getInt("estado"));
			return o;
		}
	}

	public List<Proveedor> GetProveedores() {
		String sql = "select * from proveedor";
		return db.query(sql, new Mapear());
	}

	public Proveedor GetProveedor(int id) {
		String sql = "select * from proveedor where idproveedor=?";
		return db.queryForObject(sql, new Mapear(), id);
	}
	
	public boolean Save(Proveedor p) {
		String sql = "INSERT INTO proveedor(nombre, direccion, email, descripcion, estado) VALUES (?, ?, ?, ?, ?)";
		db.update(sql, p.getNombre(), p.getDireccion(), p.getEmail(), p.getDescripcion(), 1);
		return true;
	}
	
	public boolean Delete(int id) {
		String sql1 = "update proveedor set estado=0 where idproveedor=?";
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
			String sql1 = "update proveedor set estado=1 where idproveedor=?";
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean Update(Proveedor p) {
		String sql = "update proveedor set nombre=?, direccion=?, email=?, descripcion=? where idproveedor=? ";
		db.update(sql, p.getNombre(), p.getDireccion(), p.getEmail(), p.getDescripcion(), p.getIdproveedor());
		return true;
	}

	public List<Proveedor> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from proveedor where nombre LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}
}
