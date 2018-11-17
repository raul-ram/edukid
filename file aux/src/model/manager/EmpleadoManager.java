package model.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import model.domain.Empleado;
import varios.Conexion;

@Service
public class EmpleadoManager extends Conexion {
	public class Mapear implements RowMapper<Empleado> {
		@Override
		public Empleado mapRow(ResultSet res, int arg1) throws SQLException {
			Empleado p = new Empleado();
			p.setIdempleado(res.getInt("idempleado"));
			p.setCedula(res.getString("cedula"));
			p.setNombre(res.getString("nombre"));
			p.setAp(res.getString("ap"));
			p.setAm(res.getString("am"));
			p.setFnac(res.getString("fnac"));
			p.setSexo(res.getString("sexo"));
			p.setDireccion(res.getString("direccion"));
			p.setEmail(res.getString("email"));
			p.setTelefono(res.getString("telefono"));
			p.setEstado(res.getInt("estado"));

			return p;
		}
	}

	public List<Empleado> GetEmpleados() {
		System.out.println("getEmpleados");
		String sql = "select * from empleado order by  idempleado asc";
		return db.query(sql, new Mapear());
	}

	public Empleado GetEmpleado(int id) {
		String sql = "select * from empleado where idempleado=?";
		return db.queryForObject(sql, new Mapear(), id);
	}

	public Boolean Save(Empleado e) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		java.sql.Date fnac = new java.sql.Date(sdf.parse(e.getFnac()).getTime());
		try {
			String sql = "insert into empleado values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			db.update(sql, e.getCedula(), e.getNombre(), e.getAp(), e.getAm(), fnac, e.getSexo(), e.getEmail(),
					e.getTelefono(), 1);
			return true;
		} catch (Exception e2) {
			return false;
		}
	}

	public boolean Update(Empleado i) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		java.sql.Date fnac = new java.sql.Date(sdf.parse(i.getFnac()).getTime());
		String sql = "UPDATE empleado SET cedula=?, nombre=?, ap=?, am=?, fnac=?, sexo=?, direccion=?, email=?, telefono=? WHERE idempleado=?";
		try {
			db.update(sql, i.getCedula(), i.getNombre(), i.getAp(), i.getAm(), fnac, i.getSexo(), i.getDireccion(),
					i.getEmail(), i.getTelefono(), i.getIdempleado());
			return true;
		} catch (Exception e2) {
			e2.printStackTrace();
			return false;
		}
	}

	public Boolean Modificar(Empleado e) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		java.sql.Date fnac = new java.sql.Date(sdf.parse(e.getFnac()).getTime());

		try {
			String sql = "select * from empleado set cedula=?, nombre=?, ap=?, am=?, fnac=?, exo=?, direccion=?, email=?, telefono=?, estado=? where idempleado=?";
			db.update(sql, e.getCedula(), e.getNombre(), e.getAp(), e.getAm(), fnac, e.getSexo(), e.getEmail(),
					e.getTelefono(), e.getEstado());
			return true;

		} catch (Exception e2) {
			return false;
		}
	}

	public boolean Delete(int id) {
		String sql1 = "update empleado set estado=0 where idempleado=?";
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
			String sql1 = "update empleado set estado=1 where idempleado=?";
			this.db.update(sql1, id);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public List<Empleado> ListFilteredOut(String buscador, int estado) {
		String sql = "select * from empleado where concat(nombre,' ',ap,' ',am) LIKE ? and (estado=? or ?=-1)";
		return db.query(sql, new Mapear(), "%" + buscador + "%", estado, estado);
	}

}
