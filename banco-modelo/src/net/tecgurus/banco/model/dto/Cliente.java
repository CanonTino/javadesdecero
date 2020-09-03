package net.tecgurus.banco.model.dto;

import java.io.Serializable;
import java.util.Date;

public class Cliente extends Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numeroCliente;
	private Date fechaNacimiento;
	private boolean status;
	private Banco banco;
	
	public Cliente() {
		
	}
	
	public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, boolean status, Banco banco) {
		super(nombre, apellidoPaterno, apellidoMaterno);
		this.fechaNacimiento = fechaNacimiento;
		this.banco = banco;
		this.status = status;
	}

	public Cliente( int numeroCliente,String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, boolean status, Banco banco) {
		super(nombre, apellidoPaterno, apellidoMaterno);
		this.numeroCliente = numeroCliente;
		this.fechaNacimiento = fechaNacimiento;
		this.banco = banco;
		this.status = status;
	}

	public Cliente(int numeroCliente, Date fechaNacimiento, Banco banco, boolean status) {
		super();
		this.numeroCliente = numeroCliente;
		this.fechaNacimiento = fechaNacimiento;
		this.banco = banco;
		this.status = status;
	}

	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getNombre() + getApellidoPaterno() + getApellidoMaterno();
	}

}
