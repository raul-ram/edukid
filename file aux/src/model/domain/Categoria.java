package model.domain;

public class Categoria {
	private int idcategoria;
	private String nombre;
	private int estado;

	public Categoria() {
		super();
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
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
		return "Categoria [idcategoria=" + idcategoria + ", nombre=" + nombre + ", estado=" + estado + "]";
	}

}
