package net.tecgurus.banco.model.dao.impl.test;

import org.junit.Assert;
import org.junit.Test;

import net.tecgurus.banco.model.dao.iface.UsuarioDao;
import net.tecgurus.banco.model.dao.impl.UsuarioDaoMysql;
import net.tecgurus.banco.model.dto.Usuario;

public class UsuarioDaoMySqlTest {

	@Test
	public void loginCorrecto() {
		UsuarioDao dao = new UsuarioDaoMysql();
		
		Usuario usuarioABuscar = new Usuario();
		usuarioABuscar.setUsuario("ana");
		usuarioABuscar.setPassword("ana");
		
		Usuario usuarioEncontrado = dao.buscar(usuarioABuscar);
		Assert.assertNotNull(usuarioEncontrado);
		Assert.assertTrue(usuarioEncontrado.getUsuario().equalsIgnoreCase(usuarioABuscar.getUsuario()));
		System.out.println("Usaurio encontrado: " + usuarioEncontrado.getNombre() + " " + usuarioEncontrado.getApellidoPaterno());
	}
}
