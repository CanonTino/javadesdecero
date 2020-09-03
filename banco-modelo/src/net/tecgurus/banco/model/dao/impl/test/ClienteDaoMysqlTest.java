package net.tecgurus.banco.model.dao.impl.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.tecgurus.banco.model.dao.iface.ClienteDao;
import net.tecgurus.banco.model.dao.impl.ClienteDaoMysql;
import net.tecgurus.banco.model.dto.Banco;
import net.tecgurus.banco.model.dto.Cliente;

public class ClienteDaoMysqlTest {

	@Test
	public void filtrarClientes() {
		ClienteDao dao = new ClienteDaoMysql();
		Cliente cliente = new Cliente();
		cliente.setApellidoMaterno("");
		cliente.setApellidoPaterno("");
		cliente.setNombre("juan");
		cliente.setStatus(true);
		Banco b = new Banco("BBVAB", null, true);
		cliente.setBanco(b);
		List<Cliente> clientes = dao.listar(cliente);
		Assert.assertNotNull(clientes);
		clientes.forEach(c ->{
			System.out.println(c.getNombre());
		});
	}
	
	@Test
	public void crearCliente() {
		ClienteDao dao = new ClienteDaoMysql();
		Cliente cliente = new Cliente();
		cliente.setApellidoMaterno("Bejarano");
		cliente.setApellidoPaterno("Duarte");
		cliente.setNombre("Ana");
		cliente.setStatus(true);
		cliente.setFechaNacimiento(new Date());
		Banco b = new Banco("BBVAB", null, true);
		cliente.setBanco(b);
		dao.crear(cliente);
		Assert.assertTrue(cliente.getNumeroCliente() > 0);
		
		Cliente clienteEncontrado = dao.buscarPorId(cliente.getNumeroCliente());
		
		
		Assert.assertNotNull(clienteEncontrado);
		Assert.assertEquals(cliente.getNumeroCliente(), clienteEncontrado.getNumeroCliente());
		
		String nombreInicial = cliente.getNombre();
		String nuevoNombre = nombreInicial + "MODIFICADO";
		cliente.setNombre(nuevoNombre);
		
		Assert.assertTrue(dao.actualizar(cliente));
		
		clienteEncontrado = dao.buscarPorId(cliente.getNumeroCliente());
		
		Assert.assertNotEquals(nombreInicial, clienteEncontrado.getNombre());
		
		Assert.assertEquals(nuevoNombre, clienteEncontrado.getNombre());
		
		Assert.assertTrue(dao.eliminar(cliente.getNumeroCliente()));
		
		Assert.assertNull(dao.buscarPorId(cliente.getNumeroCliente()));
	}
}
