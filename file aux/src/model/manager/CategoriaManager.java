package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Categoria;
import varios.Conexion;

@Service
public class CategoriaManager extends Conexion {

	public class Mapear implements RowMapper<Categoria> {
		@Override
		public Categoria mapRow(ResultSet res, int arg1) throws SQLException {
			Categoria i = new Categoria();
			i.setIdcategoria(res.getInt("idcategoria"));
			i.setNombre(res.getString("nombre"));
			i.setEstado(res.getInt("estado"));
			return i;
		}
	}

	public List<Categoria> GetCategorias() {
		String sql = "select * from categoria order by  idcategoria asc";
		return db.query(sql, new Mapear());
	}

	public Categoria GetCategoria(int id) {
		String sql = "select * from categoria where idcategoria=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public boolean Save(Categoria c) {
		String sql = "INSERT INTO categoria(nombre, estado)    VALUES (?, ?)";
		db.update(sql, c.getNombre(), 1);
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

	public boolean Update(Categoria c) {
		String sql = "update categoria set nombre=? where idcategoria=? ";
		db.update(sql, c.getNombre());
		return true;
	}

	public List<Categoria> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from categoria where nombre LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}
}
