package net.tecgurus.banco.model.dto;

import java.util.Date;

public class Persona {

	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Date fechaNacimiento;
	
	public Persona() {
		
	}
	
	public Persona(String nombre, String apellidoPaterno, String apellidoMaterno) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
	}
	
	public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Override
	public String toString() {
		return nombre + apellidoPaterno + apellidoMaterno;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
}
