package model.manager;

import java.sql.ResultSet;
import java.util.List;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Categoria;
import model.domain.Producto;
import varios.Conexion;

@Service
public class ProductoManager extends Conexion {

	public class Mapear implements RowMapper<Producto> {
		@Override
		public Producto mapRow(ResultSet res, int arg1) throws SQLException {
			Producto i = new Producto();
			i.setIdproducto(res.getInt("idproducto"));
			i.setNombre(res.getString("nombre"));
			i.setMarca(res.getString("marca"));
			i.setPrecio(res.getFloat("precio"));
			i.setFoto(res.getString("foto"));
			i.setEstado(res.getInt("estado"));
			i.setIdcategoria(res.getInt("idcategoria"));
			i.setCategoria(res.getString("categoria"));
			return i;
		}
	}

	public int GenerateIdProducto() {
		String sql = "select count(*) from producto";
		return this.db.queryForObject(sql, Integer.class) + 1;
	}

	public List<Producto> GetProductos() {
		String sql = "select p.*, c.idcategoria, c.nombre as categoria from producto p, categoria c where c.idcategoria = p.idcategoria order by  idproducto asc";
		return db.query(sql, new Mapear());
	}

	public Producto GetProducto(int id) {
		String sql = "select * from producto where idproduct=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public boolean Save(Producto p) {
		try {
			String sql1 = "INSERT INTO producto(nombre, marca, precio, estado, idcategoria, foto) VALUES (?, ?, ?, ?, ?, ?)";
			this.db.update(sql1, p.getNombre(), p.getMarca(), p.getPrecio(), p.getEstado(), p.getIdcategoria(),
					p.getFoto());
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean Delete(int id) {
		System.out.println("Eliminar "+id);
		try {
			String sql1 = "update producto set estado=0 where idproducto=?";
			int res = this.db.update(sql1, id);
			System.out.println("res "+res);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean Habilitar(int id) {
		System.out.println("Habilitar "+id);
		String sql1 = "update producto set estado=1 where idproducto=?";
		int res = this.db.update(sql1, id);
		System.out.println("res "+res);
		return true;
	}

	public boolean Update(Producto p) {
		String sql = "UPDATE producto SET nombre=?, marca=?, precio=?, estado=?, idcategoria=?, foto=? WHERE idproducto=?";
		db.update(sql, p.getNombre(), p.getMarca(), p.getPrecio(), p.getEstado(), p.getIdcategoria(), p.getFoto(),
				p.getIdproducto());
		return true;
	}

	public List<Producto> ListFiltro(String buscador, int estado) {
		String sql = "select p.*, c.idcategoria, c.nombre as categoria from producto p, categoria c where p.nombre LIKE ? and (p.estado=? or ?=-1) and c.idcategoria = p.idcategoria order by  p.idproducto asc";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}

}
