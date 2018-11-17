package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Venta;
import varios.Conexion;

@Service
public class VentaManager extends Conexion {

	public class Mapear implements RowMapper<Venta> {
		@Override
		public Venta mapRow(ResultSet res, int arg1) throws SQLException {
			Venta i = new Venta();
			i.setIdventa(res.getInt("idventa"));
			i.setCantidad(res.getInt("cantidad"));
			i.setTotal(res.getInt("total"));
			i.setFecha(res.getDate("fecha"));
			i.setEstado(res.getInt("estado"));
			i.setIdusuario(res.getString("idusuario"));
			i.setIdcliente(res.getInt("idcliente"));
			i.setDescripcion(res.getString("descripcion"));
			return i;
		}
	}

	public List<Venta> GetVentas() {
		String sql = "select * from venta order by  idventa asc";
		return db.query(sql, new Mapear());
	}

	public Venta GetVenta(int id) {
		String sql = "select * from venta where idventa=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public boolean Save(Venta v) {
		String sql = "INSERT INTO venta(cantidad, total, fecha, estado, idusuario, idcliente, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
		db.update(sql,  1);
		return true;
	}

	public boolean Delete(int id) {
		String sql1 = "update categoria set estado=0 where idcategoria=?";
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
			String sql1 = "update categoria set estado=1 where idcategoria=?";
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean Update(Venta c) {
		String sql = "update categoria set nombre=? where idcategoria=? ";
		db.update(sql, c.getIdcliente());
		return true;
	}

	public List<Venta> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from categoria where nombre LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}
}
