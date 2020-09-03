package net.tecgurus.banco.model.dto;

public class Usuario extends Persona {
	
	private String usuario;
	private String password;
	private boolean status;
	private Perfil perfil;
	
	public Usuario() {
		
	}
	
	public Usuario(String usuario, String password, String nombre, String apellidoPaterno, String apellidoMaterno,
			boolean status, Perfil perfil) {
		super(nombre, apellidoPaterno, apellidoMaterno);
		this.usuario = usuario;
		this.password = password;
		this.status = status;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer("");
		buffer.append(super.getNombre()).append(" ").append(super.getApellidoPaterno()).append(" ").append(super.getApellidoMaterno());
		
		return buffer.toString();
	}

}
