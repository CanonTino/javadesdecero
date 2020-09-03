package net.tecgurus.banco.model.dto;

import java.io.Serializable;

public class Banco  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String clave;
	private String nombre;
	private boolean status;
	
	public Banco() {
		
	}

	public Banco(String clave, String nombre, boolean status) {
		this.clave = clave;
		this.nombre = nombre;
		this.status = status;
	}

	@Override
	public String toString() {
		return nombre;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isStatus() {
		return status;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
