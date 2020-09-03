package net.tecgurus.banco.model.dto;

public class Perfil {

	private int id;
	private String descripcion;
	
	public Perfil() {
		
	}
	
	public Perfil(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
