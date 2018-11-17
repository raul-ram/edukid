package model.domain;

public class Proveedor {
	private int idproveedor;
	private String nombre;
	private String direccion;
	private String email;
	private String descripcion;
	private int estado;

	public Proveedor() {
		super();
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direcion) {
		this.direccion = direcion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Proveedor [idproveedor=" + idproveedor + ", nombre=" + nombre + ", direccion=" + direccion + ", email="
				+ email + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}

}
