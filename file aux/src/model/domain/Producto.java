package model.domain;

public class Producto {
	private int idproducto;
	private String nombre;
	private String marca;
	private float precio;
	private int estado;
	private int idcategoria;
	private String foto;
	private String categoria;
	
	
	public Producto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Producto [idproducto=" + idproducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio
				+ ", estado=" + estado + ", idcategoria=" + idcategoria + ", foto=" + foto + ", categoria=" + categoria
				+ "]";
	}

}