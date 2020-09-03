package net.tecgurus.banco.model.service;

import net.tecgurus.banco.model.dao.iface.UsuarioDao;
import net.tecgurus.banco.model.dao.impl.UsuarioDaoMysql;
import net.tecgurus.banco.model.dto.Usuario;

public final class UsuarioService {

	private  UsuarioDao dao;
	
	public UsuarioService() {
		this.dao = new UsuarioDaoMysql();
	}
	
	public Usuario login(String usuario, String password) {
//		String messageTemplate = "Intento de acceso usuario[%s] password[%s]";
//		String messageTemplateAccess = "Acceso de usuario[%s]: %s %s";
//		log.info(String.format(messageTemplate, usuario, password));
		Usuario usuarioABuscar = new Usuario();
		usuarioABuscar.setUsuario(usuario);
		usuarioABuscar.setPassword(password);
		
		
		Usuario usuarioEncontrado = dao.buscar(usuarioABuscar);
//		if(usuarioEncontrado != null) {
//			log.info(String.format(messageTemplateAccess, usuario, usuarioEncontrado.getNombre(), usuarioEncontrado.getApellidoPaterno()));
//		}
		return usuarioEncontrado;
	}
	
}
