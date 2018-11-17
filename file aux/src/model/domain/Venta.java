package model.domain;

import java.sql.Date;

public class Venta {
	private int idventa;
	private int cantidad;
	private int total;
	private Date fecha;
	private int estado;
	private String idusuario;
	private int idcliente;
	private String descripcion;

	public Venta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdventa() {
		return idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Venta [idventa=" + idventa + ", cantidad=" + cantidad + ", total=" + total + ", fecha=" + fecha
				+ ", estado=" + estado + ", idusuario=" + idusuario + ", idcliente=" + idcliente + ", descripcion="
				+ descripcion + ", getIdventa()=" + getIdventa() + ", getCantidad()=" + getCantidad() + ", getTotal()="
				+ getTotal() + ", getFecha()=" + getFecha() + ", getEstado()=" + getEstado() + ", getIdusuario()="
				+ getIdusuario() + ", getIdcliente()=" + getIdcliente() + ", getDescripcion()=" + getDescripcion()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
