package model.domain;

public class Menu {
	private int idmenu;
	private String nombre;
	private String enlace;
	private String icon;

	public Menu() {
		super();
	}

	public int getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Menu [idmenu=" + idmenu + ", nombre=" + nombre + ", enlace=" + enlace + ", icon=" + icon + "]";
	}

	}
