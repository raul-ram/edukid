package model.domain;

public class Rol {
	private int idrol;
	private String nombre;
	private int estado;

	public Rol() {
		super();
	}

	public int getIdrol() {
		return idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Rol [idrol=" + idrol + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

}
