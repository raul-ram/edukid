package model.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Cliente;
import varios.Conexion;

@Service
public class ClienteManager extends Conexion {
	public class Mapear implements RowMapper<Cliente> {
		@Override
		public Cliente mapRow(ResultSet res, int arg1) throws SQLException {
			Cliente p = new Cliente();
			p.setIdcliente(res.getInt("idcliente"));
			p.setCedula(res.getString("cedula"));
			p.setNombre(res.getString("nombre"));
			p.setAp(res.getString("ap"));
			p.setAm(res.getString("am"));
			p.setEmail(res.getString("email"));
			p.setTelefono(res.getString("telefono"));
			p.setEstado(res.getInt("estado"));

			return p;
		}
	}

	public List<Cliente> GetClientes() {
		String sql = "select * from categoria order by  idcategoria asc";
		return db.query(sql, new Mapear());
	}

	public Cliente GetCliente(int id) {
		String sql = "select * from cliente where idcliente=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public Boolean Save(Cliente e) {
		String sql = "INSERT INTO cliente(cedula, nombre, ap, am, email, telefono, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			db.update(sql, e.getCedula(), e.getNombre(), e.getAp(), e.getAm(), e.getEmail(), e.getTelefono(), 1);
			System.out.println("nuevo registro " + e.toString());
			return true;
		} catch (Exception e2) {
			return false;
		}
	}

	public boolean Delete(int id) {
		String sql1 = "update cliente set estado=0 where idcliente=?";
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
			String sql1 = "update cliente set estado=1 where idcliente=?";
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public boolean Update(Cliente c) {
		String sql = "UPDATE cliente SET cedula=?, nombre=?, ap=?, am=?, email=?, telefono=? WHERE idcliente=?";
		try {
			db.update(sql, c.getCedula(), c.getNombre(), c.getAp(), c.getAm(), c.getEmail(), c.getTelefono(),
					c.getIdcliente());
			return true;

		} catch (Exception e2) {
			return false;
		}
	}

	public List<Cliente> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from cliente where concat(nombre,' ',ap,' ',am) LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}

}
